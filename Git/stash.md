# stash.md
Wednesday, December 12th 2018, 00:32

if you want to switch branches and don't want to lose changes you have done(often not finished yet, if finished you can commit first) in current branch, then you may need `git stash` to stash your changes, and switch to anther branch without lose anything important, when you switch back, just use `git stash pop` to continue.

`git stash list`: look over all stashes in stack <br/>
`git stash apply`: apply(restore) your latest stashed changes <br/>
`git stash pop`: same as `git statsh apply` but will remove stash from stack <br/>
`git stash apply stash@{xxx}`: restore the specified stashed changes, i.e. `git stash apply stash@{0}` equals to `git stash pop` <br/>
`git stash drop stash@{xxx}`: drop specified stash <br/>
