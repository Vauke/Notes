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
1. remote has the needed branch, local not
```shell
# before execute the command below, you'd better execute "git pull" first to ensure your local repo has the "origin/<remote_branch>", use "git branch -a" to ckeck the remote branch list
git checkout -b <local_branch> origin/<remote_branch> # name can be different, then association has been builded

# i.e.
git checkout -b vauke origin/vauke # create local branch and associate with remote's vauke repo

# output:
Branch 'vauke' set up to track remote branch 'vauke' from 'origin'.
Switched to a new branch 'vauke'
```

2. the needed branch does not exists at remote
```shell
git checkout -b <local_branch> # create a branch at local
git push origin <local_branch>:<remote_branch> # push <local_branch> to remote's specified branch, if not exists at remote, it'll will be created automatically but do not build association

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

# then "git branch --set-upstream-to=origin/linux linux", output:
Branch 'linux' set up to track remote branch 'linux' from 'origin'.

# "git push", output:
fatal: The current branch linux has no upstream branch.
To push the current branch and set the remote as upstream, use

    git push --set-upstream origin linux

# then "git push --set-upstream origin spring", output:
Total 0 (delta 0), reused 0 (delta 0)
remote:
remote: Create a pull request for 'spring' on GitHub by visiting:
remote:      https://github.com/Vauke/Notes/pull/new/spring
remote:
To github.com:Vauke/Notes.git
 * [new branch]      spring -> spring
Branch 'spring' set up to track remote branch 'spring' from 'origin'.
```

```shell
# associate local and remote
# before first push do:
git push --set-upstream origin <local_branch>

# after at least one push do -- pull won't create branch at remote, push do
git branch --set-upstream-to=origin/<remote_branch> <local_branch>

# output:
Branch 'linux' set up to track remote branch 'linux' from 'origin'.
```

# delete branch

## delete remote branch

delete a remote branch at local

```shell
git push origin --delete <branch_name>
```

follow the hint to delete it, then use `git branch -a` to check

if continue working on the local branch which associated with the deleted one, you might see:

```shell
Switched to branch 'test'
Your branch is based on 'origin/test', but the upstream is gone.
  (use "git branch --unset-upstream" to fixup)
```

if you want to restore the remote branch, just use `git push`

```shell
Total 0 (delta 0), reused 0 (delta 0)
remote:
remote: Create a pull request for 'frontend' on GitHub by visiting:
remote:      https://github.com/Vauke/Notes/pull/new/frontend
remote:
To github.com:Vauke/Notes.git
 * [new branch]      frontend -> frontend
```

## delete local branch

```shell
git branch -d <branch_name>
```

if you are woring on the branch you want to delete, you might see:

```shell
error: Cannot delete branch 'test' checked out at '/media/Program/Notes'
```

if the branch working on is not merged with the deleting one, you might see:

```shell
error: The branch 'test' is not fully merged.
If you are sure you want to delete it, run 'git branch -D test'.
```
