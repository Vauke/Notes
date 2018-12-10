# HelloRedis.md
Monday, December 10th 2018, 21:52

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
