
Two parts of Kubernetes
- Master (Control Panel) 
- Worker Nodes

In Master the Kubernetes has Api server through which it is connected to multiple worker nodes each having agent  named Kubelet . 
Master is a separate node but doesn't mean we have separate server , we can setup it on a worker node too.



[[Pasted image 20240516095148.png]]


Various Components --  7 -- [[Pasted image 20240516094753.png]]
- ECTD
-  Kube Proxy
-  Kube Controller*
- Kubelet
-  Scheduler
- Container Runtime
-  Api Server 


##### Components Of Master - [[Pasted image 20240516095711.png]]
Pod  -- 
Single Instance of running process in a cluster
Runs one or more Containers and Shares same resources
i.e Container Based Application runs inside pod

Api Server -
Provides Command-Line Interface

Schedular -
assigns node to newly  created Pods

ECTD -
Key-value store having all the cluster data(i.e nodes,pods,containers info)

Control Manager -
Manages the state of the cluster


##### Components of Worker Nodes [[Pasted image 20240516095646.png]]

Kubelet -
Agent , make sure containers running in pods

We use docker , we directly run app in container but if we use pods then the kubelet has a control over it .

Kube Proxy -
Maintains Network rules for Communication with pods

Container-Runtime -
Tool responsible for running Containers


Kubernetes Use Cases -
No Downtime 
Rollouts and Rollbacks
Load Balancing


Minikube - Makes a single Node Cluster in which both worker and master node works


Minikube dashboard - Opens a dashboard on our local host .
Minikube status - to see info of the cluster 
minikube Delete - to completly remove the cluster


We can also run it using driver of our own too like Hyper-V , docker .

To create Pods -
kubectl create deployment Pod_Name --image= Image_Name

Whenever a pod is created , a ip is assigned to it , can get by pod describe

Ex - kubectl create deployment my-nginx --image=nginx
It will make a pod too .

To know about status of deployments and pods
Kubectl get deployments
Kubectl get pods

To see logs
Kubectl logs pod_name

For more info
kubectl describe pods


We can't directly access nginx because it is running inside pod that is inside container .
So we have to use Service object.


 kubectl expose deployment my-nginx --port=80 --type=LoadBalancer
 We have to bind the port(by default 80 ) and give type (most common Load balancer)

Can check the services running
kubectl get services

Now have to tell minikube about that service 
minikube service my-nginx



[[Pasted image 20240517141116.png]]
[[Pasted image 20240517141135.png]]

When create a react app for kubernetes remove node modules file and create DockerFile .
```
FROM node:21

  

WORKDIR /app

  

COPY . .

  

RUN npm install

  

EXPOSE 3000

  

CMD ["npm","start"]
```

Now the base image can't be any local image , we have to use a image from the hub directory.
Now we will push this image to our own repository on hub using

 docker build -t aadity/adis_repo_001:tagname .
then docker login

Now To push image  to docker hub 
docker push image_name 


Now we will do deployment

kubectl create deployment deployment-name --image = image_name

Ex- 
kubectl create deployment my-kuber-web-app --image=aadity/adis_repo_001:001  


To Delete any Deployment 
Kubectl delete deployment name


 To expose the port as on the port running is inside the pod
 kubectl expose deployment my-kuber-web-app --type=LoadBalancer --port=3000


minikube service  my-kuber-web-app


##### Rollout Updates in Our App
Now make changes in our app .
As we have made changes , we have to make a new image of our app using
docker build -t

Then push that image .

Now we already made a deployment but it is pointing to the old image , so we will point it to the new image .
Kubectl set image deployment Deployment_Name Container_Name=Our_New_Image

Can get Container Name through DashBoard

Now kubernetes has a functionality that our while our new app is being build , our old app will  run .  It reduces our downtime .

Now as our new pod started running , the old one will get terminated .

Using set image , we can go back even to our old image .

We can also get the status of the rollout using 
kubectl rollout status deployment Deployment_Name 



Now during set image if we give an invalid image -
it will show us as image updated but during process of finding image , it will get stuck
So in such case we will do RollBack
kubectl rollout undo deployment Deployment_Name


