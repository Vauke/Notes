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
# output:
Total 0 (delta 0), reused 0 (delta 0)
remote:
remote: Create a pull request for 'linux' on GitHub by visiting:
remote:      https://github.com/Vauke/Notes/pull/new/linux
remote:
To github.com:Vauke/Notes.git
 * [new branch]      linux -> linux

# then "git pull", output:
There is no tracking information for the current branch.
Please specify which branch you want to merge with.
See git-pull(1) for details.

    git pull <remote> <branch>

If you wish to set tracking information for this branch you can do so with:

    git branch --set-upstream-to=origin/<branch> linux

# associate local and remote
git push --set-upstream origin <local_branch>

git branch --set-upstream-to=origin/<remote_branch> <local_branch>
```
