# Branch.md
Tuesday, December 11th 2018, 21:14

# create new branch
```shell
git branch <new_branch> # create new branch named <new_branch>
git checkout <new_branch> # switch to it

# combine of upper two
git checkout -b <new_branch> # create and switch to the new branch named <new_branch>
```
# associate branch with remote ones
1. remote has the need branch, local not
```shell
git checkout -b <local_branch> origin/<remote_branch> # name can be different

# i.e.
git checkout -b vauke origin/vauke # create local branch and associate with remote's vauke repo
```
2. the needed branch not exists at remote
```shell
git checkout -b <local_branch> # create a branch at local
git push origin <local_branch>:<remote_branch> # push <local_branch> to remote's specified branch, if not exists at remote, it'll will be created automatically and build association

#i.e.
git checkout -b dev_local
git push origin dev_local:dev_remote

# associate local and remote
git push --set-upstream origin <local_branch>

git branch --set-upstream-to=origin/<remote_branch> <local_branch>
```
