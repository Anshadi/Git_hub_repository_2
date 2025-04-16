.
Never Commit on the main Branch - Because  our non-finalized code might contain error .
Always when solve a bug or something create a branch and pr.

the name of any folder that starts with the account name is going to be origin .

Upstream url - The url from where we have forked the project .

git status show the changes in all the branch and not only on the current branch .
We can't push to a upstream . but we can push to the origin url .

If a branch already has a pull request associated with it , it won't accept any further pull request .Any changes made after that and pr created will be merged into that . 

So if we have to create multiple pull request  for adding different features , we have to create different branches .
 
New PR ---> NEW Branch 


"If we can't change anything in the main branch, then how can it affect our branch? To keep our branch up-to-date with commits made on the main branch, we need to fetch updates from the upstream repository." 


git rebase -i <Commit_Id>  ---  if we want to merge all the commit in one single commit .
it gives us two options either to squash or to pick .
Squash means wherever it is written as push , merge it with its previous commit .






