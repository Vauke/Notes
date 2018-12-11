# HelloRedis.md
Monday, December 10th 2018, 21:52

## CAP
> C: consistency 强一致性 分布式系统中, 多个数据备份中的数据在同一时刻须是一致的 <br/>
> A: availability 可用性  集群中某些节点故障后, 集群整体须还能保障正常功能 <br/>
> P: partition tolerance 分区容错性 如果系统未在同一时限内(i.e.网络传输)完成操作, 此时分布式系统中的其他数据就可能存在不一致的情况, 此时就必须在`一致性`和`可用性`之间进行选择, 因为数据的传输都是有时延的, 因此`分区容错性`是`必须要`保证的 <br/>

三个中**最多**能同时满足两个 <br/>

CA: *传统RDBMS oracle...* 单点集群, 满足一致性, 可用性的系统, 通常不好扩展 <br/>
AP: *常用* 满足可用性, 分区容错性, 用于对一致性要求不高的系统 <br/>
CP: *大多数web应用对强一致性的需求并不高, Redis, MongoDB* 满足一致性,分区容错性 通常性能不高 <br/>


## BASE
为了解决关系型数据库的数据强一致性问题引起的可用性降低而提出的解决方案 <br/>
其主要思想就是通过减轻系统对某一时刻数据的强一致性的要求, 来换取系统的容错和可扩展性
> BA: Basically Availability 基本可用 <br/>
> S: Soft state 软状态 <br/>
> E: Eventually consistency 最终一致性 <br/>

## Ready to go
install redis on ubuntu with docker image:
```shell
docker search redis  #search redis images in repo

docker pull redis # pull redis offical images to local

docker run -p 6379:6379 -v $PWD/data:/data -d redis:latest redis-server --apeendonly yes
```
> `-p 6379:6379`: mapping the container's port 6379 to host's port 6379 <br/>
> `-v $PWD/data:/data`: mount the `/data` dir in the current path to container's `/data` dir <br/>
> `-d`: runs the container in backgroud <br/>
> `redis-server`: run the redis within container <br/>
> `--appendonly yes`: set persistence option true

now you have a well-installed redis running on your machine!
to stop or start redis server:
```shell
docker ps # get the running redis container's CONTAINER ID ${ID}
docker stop ${ID} # stop redis server or just use SHUTDOWN in redis commandline to shutdown the server

docker start ${ID} # to start redis server
```
to get into redis commandline:
```shell
docker exec -it ${ID} redis-cli
```
> `exec`: execute commands in container <br/>
> `-it`: `-i`: interact with STDIN(aka: standard input) `-t`: assign a terminal in container to user

note: the `docker run ...`can just be execute once to create a redis container with specified image and runs it, then you can manipulate the redis server just with `docker start/stop ...` & `docker exec -it ...`


## basic commands
#### database-related
`select <database_index>`: switch between databases(0...15) <br/>
select 2 : switch to database3 <br/>
`dbsize`: to indicate count of keys in `current` database <br/>
`flushdb`: flush all keys in `current` database <br/>
`flushall`: flush keys in all databases <br/>

#### key-related
`keys *`: show all keys in `current` database <br/>
`keys k?`: show all keys start with `k`, i.e. k1, k2..., not k12 <br/>
`keys k*`: show all keys start with `k`, i.e. k1, k12... <br/>

#### key control
`exists <key_name>`: check whether the key `<key_name>` is exist(return 1) or not(return 0) <br/>
`move <key_name> <database_index>`: cut key `<key_name>` in current database to specified database(via database's index, 0...15) <br/>

`expire <key_name> <xxx_seconds>`: set the specified key expires in xxx seconds <br/>
`ttl(time to live) <key_name>`: indicate the key `key_name`'s expire time(in seconds) <br/>
 note: the expired key is no longer in database
| 返回值 |  -1   |       -2        |               xxx               |
| ------ | ----- | --------------- | ------------------------------- |
|  描述  | never | already expired | expiring in another xxx seconds |
