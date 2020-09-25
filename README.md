Team Names

Lodermeier, Ryan

Davis, Britt

Njuguna, Jesse W

Steps....
First Time:
1)git config -global user.name "username"
2)git config -global user.email "email"

After inital setup, get the repo going and walk through basic work flow
1) git init (eother in directory of planned repo or give it the path)
2a) If first time with Git Hub create your keys in Linux then add to Git Hub. 
2) git clone with ssh key
3) git pull origin master
4) git checkout -b <branch> (do not need -b if branch already exists)
5) git add <files>
6) git commit -m <comment>
7) git push origin <branch name>
8) Handle to pull request on GitHub
9) Git pull origin master 

Options
1) git reset --hard -> reset to origin master
2) git reset -> soft reset
3) update .gitignore if you would like to not include diretories or files.  It is recursive
4) setup keys from command line: ssh-keygen -t rsa -b 4096 -C "your email".  Then go to /home/.ssh/id_rsa.pub.  Copy that text and enter it into GitHub or BitBucket as a new key.  This will allow both git and GitHub/BitBucket to talk to each other
