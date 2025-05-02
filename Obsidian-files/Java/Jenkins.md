The Build and Deployment Process is --
![[Pasted image 20241111173645.png]]

Manually , it is very difficult .

Dev-Ops -- Development + Operation -- Project Development + Build And Deploy .

Problems in doing this manually is -- 
![[Pasted image 20241111174158.png]]

Now to automate this process , we use Jenkins . 
Here we will already write the pipeline for the Build , test and deployment . So as the code gets committed , the Jenkins will automatically perform all these operations .
![[Pasted image 20241112013754.png]]
It is a continuous process .


**While the whole development of the application , there are various environment present .**

1 - Dev Env . - Project Development , Unit Testing , Integration Testing 
2 - Q.A. Env -  Quality Insurance Environment ,  It is also a Testing Environment . 
3 - UAT - User Acceptance Testing , whether the user accepts our product or not .
4 - Pilot Env (Pre - Production Env ) - Performance Testing 
5 - Production Env - Here our project becomes live .


CI/CD Pipelines - 
Build , test and deploy as soon as commit happens . So it helps in daily basis code integration . ![[Pasted image 20241112015152.png]]
![[Pasted image 20241112015204.png]]



##### Implementation --- 

So Now what we are gonna do is -
![[Pasted image 20241112033809.png]]

When  we create a web-app maven project using eclipse , there could be a chance of  error on index.jsp page which can be corrected by adding the tomcat server in the runtimes in the project properties .

Here generally the error comes while building the project as the maven war plugin is not present in the pom.xml file . 
![[Pasted image 20241112093925.png]]

Now once build , this war file will be generated wherever our file is in the workbench .

Now we can close our tomcat , we have to deploy this war file on our tomcat .

 Now we have to go to the  tomcat bin file and then to cmd , where we will put the command to start tomcat -
 `startup.bat`
 Here the port no. on which it is started will be mentioned .
 Now we would be able to see the homepage on this port .

Now inside it , we can manually deploy our app by going in the `manager app` .

Or we can also do this that we can copy the url of the war file from our workbench and then paste it inside the webapp of our Apache tomcat file .

Or we can do it through the Manger App , but to access it we have to create username and password which we will set in the `tomcat-users.xml` file in `conf` .

Now there will be a option to deploy the war file , here we will deploy that file .

Now , we did it manually , now we are going to do this with Jenkins .

However there will be a small problem that both the tomcat and jenkins runs on the same server that is `8800` , so we will change the server of the tomcat by changing the server port in `server.xml` file in the conf in tomcat .

We have to put `java -jar .\jenkins.war --enable-future-java`
My `Username - root` and `password - root` .  Now we can start using jenkins .

Inside Jenkins , we will download the `maven Integration` plugin . and also `deploy to container`.
Here while building projects , we can provide steps according to which the project file will be executed .

We can also if that project has a java program , can pull it , compile it and see the output .

Now while configuring , when we reach the `Build Environment` , we can use execute shell for ubuntu but for windows , we will use the `execute windows batch Command` . 

While using the Git , we will put the Url of the  repository in the field in which we have our file which we want to execute . 
And if the repository is private , then we will use the below field of credentials .

Now once we build this file , then now when we change something and commit it  --
The Jenkins automatically fetches that change and again produces the output file with new changes ..


Now if we have made a war file using jenkins then if we have to deploy it , we have to use the option deploy war to container in the `post Build Options`.

So Now to really Automate , i have to do these configuration in the jenkins to deploy the app through tomcat and we will use git here , so whenever we post any commit or changes to git , it automatically will start to build new .

![[Pasted image 20241122183437.png]]
![[Pasted image 20241122183457.png]]![[Pasted image 20241122183511.png]]![[Pasted image 20241122183518.png]] ..

So now instead of deploying it directly through cmd , we are deploying it through tomcat .


***Now Some commands to run the jenkins and the tomcat on the ec2 instance .***
-  ssh key that we get from the instance .
- sudo apt update
-  sudo su
- sudo apt install jenkins
- /opt/tomcat/bin/startup.sh
-  java -jar /home/ubuntu/jenkins.war --httpPort=8081
-  export JRE_HOME=$JAVA_HOME
- wget https://get.jenkins.io/war-stable/latest/jenkins.war
- find / -name "apache-tomcat*" 2>/dev/null
- tar -xvzf apache-tomcat-9.0.97.tar.gz -C /home/ubuntu/
- cd /home/ubuntu/apache-tomcat-9.0.97/bin
- ./shutdown.sh
- cd /home/ubuntu
-  update-alternatives --config java



- In the browser whenever connect , connect through the http and not https .




~ When we are starting the jenkins with java -jar jenkins , it means that we are starting the jenkins with the jenkins.war file and have not done the installation , so we wont get any conf or any other file .

Now if i am executing jenkins from the ubuntu , i will use execute from shell instead of the windows batch commands .

