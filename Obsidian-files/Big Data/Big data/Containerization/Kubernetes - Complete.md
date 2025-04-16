[[https://kubernetes.io/docs/reference/kubectl/quick-reference/]] commands cheat sheet --

Kubernetes is a Container Orchestration Platform . 
As we know that the containers are ephemeral . That is they work on die and revive scheme .
Suppose , if we are running many containers on our docker and one of the container started taking much memory , due to which another container can die as it doesn't had the req. resources it needed for working or if any container was schedule won't get started . As We have Only One Host .

Or If the Image is not getting pulled , the container will Immediately die . 

***Problem-1 -- Single Host***
So One Of the Problem is ***there is only a single host , So when the container is not getting enough resources they die . Here Containers is Impacting Each Other .***

***Problem -2 -- Auto-Healing***
If Someone kills the container , the application inside that , which was running now becomes inaccessible , So Until this container is started again  by user ., it can't be accessed . So this method is known as ***Auto-healing .*** Which is without the user intervention , our container starts with itself .
So this feature is not present in the docker or any of the containerization platform and a devops engg. can't monitor 100s of containers .


***Problem-3 Auto - Scaling***
Consider a container configured with a maximum of **4GB RAM and 4 CPU cores**, though it may not always receive the full resources from the host machine.

Now, suppose our system typically serves **10,000 users**, but during peak hours, the load spikes to **100,000 users**. To handle this surge, we have two options:

1. **Manually** create 10 instances of the container (`C1`).
2. **Automatically** scale by provisioning 10 instances of `C1` based on demand.
3. **Implement a Load Balancer** to distribute traffic efficiently.

However, **Docker alone does not provide built-in auto-scaling** capabilities. This is where **orchestration tools** like **Kubernetes or Docker Swarm** come into play, enabling **automatic scaling** based on system load.



***Problem-4  -- Enterprise***
***Docker is a very minimalistic*** . It doesn't support Enterprise level Support .
That is the Enterprise level Standard is -
- Load Balancer
- Firewall
- Auto-Scale
- Auto-Healing
- API Gateway 

These are not provided by docker .



These Problems are solved by the Kubernetes or other #Orchestration_Platform .

Kubernetes is a cluster that is group of nodes , though for development , we can install it on a single node but for production and By default , it is a cluster .
It follows #Master_Node Architecture . So One Master Node and multiple other Nodes .

***Solution-1 ---***
Now as there are multiple nodes present and if due to one container , any other container is getting affected in a node , Kubernetes can shift this container to another node So that it can work properly .


***Solution-2 ---***
Now Kubernetes has Replication Controller ( Replica Sets ) , so we don't even need to create new containers , 
In Kubernetes , everything is about yaml files .  So we can go to `deployment.yaml` file and can increase our replica set from 1 to 10 .

It Also supports HPA i.e. Horizontal Pod Auto Scaler  which automatic scales ,when load reaches a threshold , so for ex- whenever load reaches a 80% Threshold , It will create a new container . 



***Solution-3 ---***
So Whenever there is a damage , ***Kubernetes controls the damage or fix it*** .

Suppose our container is gone down - Kubernetes using its feature of Auto-Healing , even before the container goes down , it starts a new container .
How it does is , whenever Kubernetes API server receives the signal that a container is going down . So Kubernetes roll-out a new container and no Impact is not seen unless it is a very heavy application .



***Solution-4 ---***
Kubernetes is an **Enterprise-grade orchestration platform** .

It supports **load balancing** and various other features. While it may not offer advanced load balancing capabilities by default, it provides mechanisms to integrate external tools for enhanced functionality.

Using **Custom Resources (CRs) and Custom Resource Definitions (CRDs)**, Kubernetes allows tools like **NGINX** to create their own Kubernetes controllers. These controllers enable the use of external load balancers within Kubernetes, a concept known as the **Ingress Controller**.




![[Pasted image 20250306042250.png]]

![[Pasted image 20250306043009.png]]



There are two things in Kubernetes 
- Control Plane and Data Plane 
![[Pasted image 20250306043932.png]]
- Control Plane Contains 
	- Api Server , Etcd , Schedular , Controller Manager , Cloud Controller Manager 
- Data Plane
	- Kubelet , Kube Proxy , Container runtime .

In Docker , the simplest thing is Container . In Kubernetes , its pods .


Suppose , if we have docker and inside it using command `docker run` , we ran a container . So without a Container Runtime , it wouldn't run .
In Docker , we have docker runtime named #Dockershim .

![[Pasted image 20250306044419.png]]


In **Kubernetes (K8s)**, when we deploy a **Pod**, the request first goes through the **Control Plane (Master Node)**. From there, it is scheduled to a **Worker Node**, where the **Kubelet** ensures the Pod is running and manages its lifecycle.

Just like **Docker**, Kubernetes also requires a **container runtime** to run containers within Pods. However, unlike Docker, **Dockershim is not mandatory** in Kubernetes. Instead, Kubernetes supports multiple container runtimes, including:

- **Dockershim** (deprecated)
- **containerd** (default runtime in modern Kubernetes)
- **CRI-O** (lightweight runtime for Kubernetes)
- Any other runtime that implements the **Kubernetes Container Runtime Interface (CRI)**


In Docker , we had by default bridge networking i.e. docker0 , In kubernetes  , we have something named Kubeproxy .
This provides it networking . It uses IP Tables on Linux .
It provides 
- Networking
- Ip Addresses to the pod
- Load Balancing 


So the three components Available on the worker node( Data Plane ) are -
- Container Runtime 
- Kubelet
- Kube-Proxy


So this worker node or the data plane is responsible for running our application .


However, in an enterprise environment, we need a **Control Plane** (or **Master Node**) to enforce specific standards and make critical decisions, such as:

- Determining **which node** (e.g., Node 1 or Node 2) should run a particular workload.
- Issuing and managing various operational instructions.

At the heart of the Control Plane is a crucial component called the **API Server**. 
- The API Server acts as the **gateway** to Kubernetes, handling all incoming instructions from external sources, including users and automation tools.

For example, the API Server might determine that **Node 1 has available resources**, but to actually schedule a pod on that node, we need the **Scheduler**.

- The **API Server gathers and processes information**.
- The **Scheduler then acts on this information** by assigning pods or resources to the appropriate nodes.

This separation of responsibilities ensures efficient workload management and high availability in Kubernetes.

Now when we deploy something on our k8s clusters which should act as a backing store for all the information of all the cluster information , which is known as ***ETCD*** .
It is a key-value store and the entire info is stored as objects are key-value pair in ETCD .


Kubernetes supports **auto-scaling**, which means it needs components that can **detect changes** and **act accordingly**. This is achieved through various controllers, such as the **Replica Set Controller**, which ensures that the desired number of pods are always running.

It maintains the state of our kubernetes pods .

Since Kubernetes relies on multiple controllers to maintain the system’s state, there must be a higher-level component responsible for managing these controllers.
This is where the **Controller Manager** comes in—it oversees and ensures that all controllers are running and functioning correctly.



***Cloud Controller Manager --***

Kubernetes can run on cloud platforms like **EKS (AWS)** or **GKE (Google Cloud)**. When a user requests resources such as a **load balancer** or **storage** 
So if we directly send it to k8s , then it had to understand the underlying cloud provider .


So k8s has to translate this User request to the API request that the cloud provider understands .
To bridge this gap, the **Cloud Controller Manager (CCM)** is responsible for **translating Kubernetes requests into cloud-provider-specific API calls**.


The CCM is an **open-source utility** that allows cloud providers to define how their infrastructure integrates with Kubernetes. 
Essentially, it contains the logic that enables Kubernetes to work seamlessly with different cloud environments.


A **Hypervisor** (or Virtual Machine Monitor, VMM) is software or firmware that creates and manages **virtual machines (VMs)** on a physical machine. It allows multiple OS instances to run simultaneously on the same hardware. 



Now the end goal of both and the docker is to deploy the  application in container , now in docker , we deploy it as in container however in kubernetes , we deploy it as pod .

A pod is described as a definition on how to run a container . So in docker , we gave the commands to run in command Line like `docker run -it` etc. However in K8s , we give these commands in the Pods.yaml file .

The  pod can be a single container or could be a multiple containers .
In K8s , we write everything in yaml files .


Suppose if there is a single container in a pod  , and that pod have to read some config files , then we can create another container and we can put these container in a single pod so due to which they have -
- Shared Network
- Shared Storage 

Pods is like a wrapper used as K8 is a Enterprise level Application , so it uses it for standardization .

As if we are maintaining 100s of containers and we have a yaml file where we can know everything about these containers , it makes our work easy .

Here we access the containers using the cluster Ip Address given by Kube-Proxy .

Kubectl - It is the command line for Kubernetes . 

To get how many nodes are inside our kubernetes cluster - 
we can give  `kubectl get nodes` , `kubectl get pods`


Minikube good for learning otherwise use kind , which is kubernetes in docker , we can manages 100s of nodes in our personal computer . Here our all kubernetes setup is done as docker containers .

Minikube is a command line tool that allows us to create a kubernetes cluster .

How minikube creates a cluster is - 
First it will create a virtual machine and top of that virtual machine it will create a single node kubernetes cluster .

Since for learning , we can run minikube on docker , but for later on production use 
`minikube start --memory=4096 --driver=hyperkit`

```
C:\Users\adity>kubectl get nodes
NAME       STATUS   ROLES           AGE   VERSION
minikube   Ready    control-plane   26h   v1.30.0
```

Now when we are using the get nodes , it is showing this which is our single node kubernetes cluster .

Now for creation of pods , we have to write yaml file  and there is no adv.  in learning the yaml file , values will always be changed , only keys will remain same .


Now to create a pod , we have to write these commands in pod.yml .

```
apiVersion: v1
kind: Pod  # Corrected case
metadata:
  name: nginx
spec:
  containers:
    - name: nginx
      image: nginx:latest 
      ports:
        - containerPort: 80 


```

Its equivalent docker command is -
`docker run -d nginx --name nginx -p 80:80`

Now to create this pod -
`kubectl create -f pod.yml`

Equvivavlent 
docker ps  -----> Kubectl get nodes 

`Kubectl get pods -o wide`
The above command is used to  get the  complete description of pods .

To delete the pod 
`kubectl delete pod pod_name`

Now inside this pod.yml  file , we can add all the things like  volume and volume Mounts and etc.

Now To add **Auto healing and Auto Scaling** --
We have a wrapper called #deployment_yml file which is used to add these functionalities .

![[Pasted image 20250309010115.png]]

In real case scenarios , we don't deploy pods but we deploy these deployment file .


These two commands are used to debug the pods --

To get all the resources in a particular namespace  -
`kubectl get all`

and for all the namespaces , all the applications in our cluster -
`kubectl get all -A `

To get the logs of a pod -
`kubectl logs pod_name`

To get all the info about the pod -
`kubectl describe pod pod_name`


To see what is happening live with the pods - ( -w means watching )
`Kubectl get pods -w`


So here what we do in all these three to run the container or pods - 
In second , we mention the running specification of the container in our pod.yaml manifest file .
In pod , we can have single or multiple container , multiple container as there could be application which couldn't be ran without another application( sidecar containers ) .


The purpose of using the deployment is the abilities like -
- Auto Healing & Auto Scaling 
which isn't provided by the pods .


Now instead of deploying the pod , if we are deploying the deployment file , it will create a ***Replica Set*** which will again create the pod .
![[Pasted image 20250309012950.png]]
This Replica set is our Kubernetes Controller . It will roll out our pods .

The RS is a go lang. application written for ensuring a specific behavior is Implemented .

So inside the deployment , we can mention what is the no. of replicas we want .
Like in case of load , we can divide the traffic among them to access the same data and operations .

So now what auto healing does is , as we have mentioned that there is 2 pods suppose , so if accidently one of the pods gets deleted , The Replica Set ( RS ) Controller will create one automatically .
This RS will ensure the auto healing capabilities for our pods .

Anything that ensures that the actual state and the decided state on the kubernetes is same is known as controller .
There are custom as well as built in controllers .

![[Pasted image 20250309013704.png]]


Now we use the command -
`kubectl apply -f pod.yml`
![[Pasted image 20250309112819.png]]

Now to access this pod , we need to login to our kubernetes cluster , now in minikube we can do it easily by command -
`minikube ssh` 
but  in production case when using multiple nodes -
`ssh -i < our_identity_file > node_name_or_IpAddress_of_our_file`

Now if we somehow this pod was deleted due to network error or something , the user wont be able to access it , so for this we have to deploy the deployment file , 
whose code we can find through Online .


Deployment.yml
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
  labels:
    app: nginx
spec:
  replicas: 1
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx:1.14.2
        ports:
        - containerPort: 80

```
Now inside this file , i have mentioned the replicas as 1 . so when we deploy it , the deployment will be created as well as the pod will also be created . 

using command `kubectl apply -f deployment.yaml`

As Deployment -- > RS  --- > Pod 

So the deployment is an abstraction , so we just need to create and it will create all these resources .

So now when i watched the pod live and deleted it through another terminal , it automatically created itself again --
![[Pasted image 20250309114720.png]]
This copy is created before the actual one is deleted or parallelly as it is deleted .

Now if i changed ( RS:3 ) in the deployment file and again applied it , we can see that it created two more pods .
![[Pasted image 20250309115138.png]]


***So Due To These K8s does Zero Time Deployment --***




Now if i have to stop the pod without deleting its deployment file then we can ![[Pasted image 20250317180203.png]]




#### Kubernetes Service 
When we deploy  deployment file , we create a service for each deployment . 

***Problem 1)*** ------ So if there would be no service in the K8s , then what will happen is -
if we deploy the file then it will create a Replica Set , which  will deploy a pod , now suppose if there is 3 replicas , then it will create 3 pods .

Now before the three pods have Ip Address had a sequence i.e. like 172.16.2.3 , 2.4 , 2.5 and now when the new pod created then its Ip Address is changed that is maybe 172.16.2.6  , So if there is no Service concept in k8s then  ,
we knew that our 1st pod's Ip was 172.16.2.3 but it automatically changed to 2.6 , so it will create a problem .

So now any one accessing the  previous Ip , for him our application is down .
Now instead of giving them an Ip , we create a service file over our deployment file .

***Solution 1)*** --------  What this Service does is it acts as a ***Load Balancer*** . It uses the component in K8s known as Kube-Proxy . 

So now if the application name was Payment , then what service provides is a name - ***payment.default.SVC***  , So now everyone will try to access the app on this Ip Address and underlying it will act as a load balancer .


***Problem 2) -----*** The req. are gone to the service but it also should face the same problem that is how it identifies new Ip of that pod .


***Solution 2)----*** Now it is done with the help of ***Service Discovery*** . 
The Service does  the Service Discovery  by the help of  ***Labels and Selectors*** .  So unlike manually keeping the track of all Ip Addresses .

For every **Pod** created, a **label** is set on them, and this label is **common across all the Pods** that belong to the same application or service . Now this service uses this labels as auto-healing is applied , a new pod is created based on the yaml file where we have specified the label name inside its metadata . 



![[Pasted image 20250309154313.png]]


This concept is known as #Service_Discovery .
![[Pasted image 20250309150504.png]]


**Selectors ensure that only the right Pods are selected** for routing traffic, scaling, or replication.


Types of Selectors -- 
![[Pasted image 20250309154811.png]]



***Problem 3) -----*** 


***Solution 3) -----*** ***Exposing*** our application to the outer world . 

So before what happened was whenever , we were creating the deployment file , the pod was automatically gets created with a particular Ip Address . Whenever we were accessing it through ssh (whether to the master or any worker node ) what was happening was whoever has the access to the Kubernetes cluster can login to the pod and can hit our application . 

But In real world , the end-user directly gets access to the application , we don't need to login to the machine through ssh and do all these procedures .

So what service will do is it will expose our application i.e. allows our application to be accessed from outside .

How it does is whenever we are creating the service resource in yaml manifest , it provides us 3 options ( Main Default types )
- Type 1 - Cluster Ip Mode
- Type 2 - can create it as a Type NodePort
- Type 3- can create it as a Load Balancer

So if we create the service using the Cluster Ip Mode , the by default settings will be followed , that is our app will only be accessible inside the K8s cluster . The Only 2 benefits that we get are 
- Service Discovery
- Load Balancing 

But If we create it as a type of Node Port  , it will allows the access our app inside our organization , that is someone who doesn't have access to k8s cluster but have access to our worker node Ip Addresses can access our application . i.e. whoever has access to the Node Ip Addresses . So suppose these worker nodes are EC2 Instances , so whoever has access to these EC2 Instance Ip Address will able to access these apps .

In contrast, with Cluster IP mode, even if someone has the EC2 IP addresses, they can only access the application if they can log in to the Kubernetes cluster and have access to its container network.


If we create the service as the Load Balancer type , it will expose our app to the external world . Then for it we will create a #Elastic_Load_Balancer_Ip_Address on our cloud provider and whoever want to access can use this Public Ip Address . This Public Ip is generated by CCM .

But if we are creating this service as this ( Load Balancer ) on any cloud provider , it will only work on the cloud providers and not on our minikube or any k8s cluster by default . ( Its Soln. is through Ingresses ) .

![[Pasted image 20250317013432.png]]



#### I Questions - 1 -
![[Pasted image 20250317142850.png]]

![[Pasted image 20250317143549.png]]

Namespace helps in role based access control . ![[Pasted image 20250317143922.png]]

Kube-proxy updates the Ip Table dynamically .

![[Pasted image 20250317144232.png]]



![[Pasted image 20250317144542.png]]

![[Pasted image 20250317144617.png]]

![[Pasted image 20250317145033.png]]


![[Pasted image 20250317145335.png]]


#### Kube-Shark

1- Whenever we create any resource in kubernetes , we should always try to put labels in the file whether it is deployment or service .
![[Pasted image 20250322195901.png]]
or we can also use the policy - `ifNotPresent` 

then will try these command to 
```
kubectl apply -f MyspringImage_deployment.yaml
kubectl rollout restart deployment myspring-deployment (if prev any bad deploy)
kubectl get pods -w
```
Now if we have to see what really internally happened then we use 
`kubectl get pods -v=7`

The max verbosity level is `-v=9` where we get the max. info about what is happening inside the pods .

Can Use this with any command to get info about it .

Now this was the problem that after deletion the ip of the pods gets changed  .
![[Pasted image 20250322212037.png]]


So that's why we need service discovery mechanism . Using Labels And Selectors .
1) ***Exposing***

now to access these from the minikube , we do 
`minikube ssh`
`curl -L http://10.244.0.41:8080/api/hello`

here we mention the Ip address of the pod and then the port of our application and then the path .
Here `-L` denotes redirection .

The `-L` option tells `curl`:
> "If the server redirects this request to another URL, follow it automatically."=


However if we do the same command outside the k8 , we wont get anything , it is because 
A pod by default will have only the cluster Ip Address . i.e. it will only have the cluster network attached to it .
So as it is the cluster network , we have to login in the cluster and then access it .

Now for people within the organization (same laptop) or outside it , we can use the service concept .
So we have to expose it on the worker node Ip addresses for organization and public Ip for users outside of it  .
So for these , we have to use Node port Mode and Load Balancer Mode .

```
apiVersion: v1
kind: Service
metadata:
  name: my-spring-app-service
spec:
  type: NodePort
  selector:
    app: my-spring-app
  ports:
    - port: 8080
      targetPort: 8080
      nodePort: 30036  # Can be any port between 30000–32767

```
> For Node port Can be any port between 30000–32767

Here , we have to keep the selector similar to the pods we have created as the service will be directly looking at the pods using these selectors .
So if any pods have this label , it will be transferring the traffic to these pods only if it is  in the same namespace . 
***So this label has to be the same  as in the deployment in the template section in labels section in front of the app .*** 

Here the target port is on which our application is running .
On deploying 
```
C:\Users\adity>kubectl get svc
NAME                    TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
kubernetes              ClusterIP   10.96.0.1       <none>        443/TCP          16d
my-spring-app-service   NodePort    10.107.142.33   <none>        8080:30036/TCP   62s
```

Here it means that `10.107.142.33:8080` port is mapped to the node 30036 .
so it means that either i can access this app using minikube ssh and then using this Ip Address . Here `8080:mapped to 30036`
Or we can use the Node port Ip Address .

We can get the minikube Ip using command `minikube ip` 
then to access the app , we will use this minikube Ip with mapped port .

`curl http://192.168.49.2:30036/api/hello` - though maybe due to some firewall issue didn't worked in mine .





***Got the Reasons***

***Now there is a thing that 
I am using Docker driver with Minikube on Windows. When using Docker as the driver, the Minikube VM is _not_ directly accessible from your host via `minikube ip`. That's why `curl http://192.168.49.2:30035` doesn't work from Windows, but works inside Minikube.***

So to work the Node Port on windows what i have to do is 
I have to run the command - `minikube service my-spring-app-service` . I got the service name from command `kubectl get svc pod-name` . and let it run on a separate command terminal . 
So It will provide me a url and i will replace that with `192.168.49.2:30035` i.e. minikube Ip + Forwarded port . And then the endpoints .
![[Pasted image 20250324183021.png]]









Now to make it load balancer type 
`kubectl get svc`
`kubectl edit svc svc-name`

Now we have  to replace here the Node Port with the Load Balancer , however it wont work here , if this file would be on cloud provider then it would have worked .
```
my-spring-app-service   LoadBalancer   10.107.142.33   <pending>     8080:30036/TCP   147m
```
Now if  it was azure or something else we would have got Ip Address here instead of pending . Generated By CCM .


However there is a project named `MetalLB` using which we can expose our app on minikube also . But still a beta project .

So when we get the Ip address , we can give it to public .

- Instead of `kubectl edit ` , we can also use `service.yaml` file but whenever we edit we have to use `apply ` command ( preferred ) .


2) ***Service Discovery***-

Now suppose when we even change a single letter in the selector in the service the service discovery won't able to find it .
That is we wont be able to find it through curl or website .


3) ***Load Balancing*** - 
	For Understanding how the traffic is flowing and other things , we should download the kube shark . It is a Deep Network Observability tool .

Now after download , will give access to all the namespaces to it using the command 
`kubeshark tap -A` 
or edit the config file of kube shark .

So now when we run multiple times the curl command , the kube shark will show us on which node it transferred the traffic .

`Kubeshark proxy` This command is used to restart the connection .
It will show us the sending of the packet from the Source Ip Address to the Destination Ip Address . 
It shows us how the data packets are flowing .


```
To Delete Kubeshark 
kubectl delete -f https://raw.githubusercontent.com/kubeshark/kubeshark/v52.3.96/manifests/complete.yaml

To Install
kubectl apply -f https://raw.githubusercontent.com/kubeshark/kubeshark/v52.3.96/manifests/complete.yaml
```

![[Pasted image 20250323182914.png]]

It also shows the service map and other things .


#### Ingress 
Before 2015 dec. that is k8 v1.1 there was no ingress .

So before when people were using this k8 , they were installing the app in virtual env on their system and then use Load Balancers like Nginix .

***Problem 1)*** These were Enterprise level Load Balancer . Provides advanced capabilities like Ratio based Load Balancing  , stick sessions ( if one req. to pod one then all req. of that user to that pod ) , Path based , domain or host based Load Balancing . Supports Black Listing (don't allow specific customers like hackers or from a specific customers ) , White Listing ( Allow only specific customers ) . (100s of features )
So allowed them to define the traffic openly . 

So when they migrated to k8 - 
They observed that the service is providing the Load balancing however it was only the Round Robin Load Balancing . So it was not providing advanced capabalities . 


***Problem 2)*** Another problem was when we created 100's of services of Load balancer mode , the Cloud Provider Company was charging for each of the static and public Ip Address .

Before k8 the people were only making the path in the load balancer and were exposing this load balancer with the static Ip Address . 

So what k8 did was , it allowed the user to create the resource named **Ingress** and allowed the Load Balancing ( like NGINIX , HAProxy , Traefik , F5  ) company to create **Ingress Controller** . So what it does is if the user want the Nginix path based controller , they just have to mention it in the yaml file and deploy it and the Nginix company will write its logic in their Ingress Controller . So this Ingress Controller provide them path based routing .

![[Pasted image 20250323204425.png]]


***And Also with creating this Ingress resource depending on the capabilities needed , we also have to deploy the Ingress Controller of that company from their GitHub page . This Ingress Controller is just Load balancer and sometime also API Gateway .*** 




Creating Ingress resources and setting up Host Based Load Balancing - 

It instead of serving request on a Ip , it serves the request to the domain name . 
How Ingress works -![[Pasted image 20250324184250.png]]

***Ingress.yaml*** 
```
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: minimal-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  ingressClassName: nginx
  rules:
  -host:"adi.com"
  - http:
      paths:
      - path: /testpath
        pathType: Prefix
        backend:
          service:
            name: my-spring-app-service
            port:
              number: 8080

```
Here in service.name , we have to fill our service name .
This port no. should be same as what the port no. is mentioned in the service.yaml .

Then apply this file .
To chk the Ingress -
`kubectl get ingress` 
However the address field would be empty .

 We still have to create the ingress controller . `search nginix ingress controller minikube ` .
 
To Install Nginix Ing. Cont. , the command is `minikube addons enable ingress` 
The Nginix controller is nothing but a pod , so can find it using get all pod .
`>kubectl get pods -A | findstr nginx`

To see the logs of this ingress controller -
`kubectl logs ingress-controller-name -n namespace_of_Ingress_Controller ` 
Here we can see that it automatically detects the Ingress resource that we made and synced .
Now we can see the address port in the Ingress Pod .


In our production env. it is enough but if we are doing this in local machine , we also need to update the `/etc/hosts` configuration .

Now the thing currently we are accessing our app on http but with the help of TLS based Ingress , we can make it secure and let it only accessible on Https .




#### Config Maps And Secrets --
![[Pasted image 20250325150236.png]]

Now Normally when we create a app , the things that are req. by the app from the db is DB port , Db Username , password , connection type and the Env file where we keep the port no. and other info . We read this info in 


In Kubernetes, when dealing with configurations such as database ports or connection types, we avoid hardcoding values directly inside containers. Instead, as DevOps engineers, we use **ConfigMaps** to externalize such environment-specific configuration data.

A **ConfigMap** can store key-value pairs like the database port, host, or any other necessary metadata. Once the ConfigMap is created within the cluster, we can **inject its values into a Pod** in two ways:
1. **As environment variables**, which the application inside the container can read.
2. **As mounted configuration files**, which the application can load from the filesystem.
    
This setup allows for cleaner separation between code and configuration. When a Pod is running, we can log into it and **access the configuration values**, which are effectively being sourced from the ConfigMap.
This approach enhances maintainability, security, and portability of applications across environments.

Inside the configmap , we can either use the Env file or Volume mount .


Config Map is used to store data which can be accessed later . 

Now ***Secret***  in K8 solves the same problem however it works with Sensitive data .
Now suppose , if we were saving the Db password and username with Db port in the config map , there is an issue in k8 , that whenever we create a resource it gets saved in 
**Etcd .** 
In Etcd , the data is saved as object , so anyone who has the access of the etcd can access our Db pass. and username .

***So That's why , we use config map for Non-Sensitive data and Secrets for Sensitive data .***

In Secret , any data that is stored is encrypted before it is stored in Etcd . 
Here , it provides a basic encryption , but , we can use custom encryption . So if someone tries to access it , they won't be able to as they wont have the decryption key .


So the process is when we write the yaml file and apply that then two things happen concurrently 
- The config map is created 
- The Info in the config map is saved in the ETCD 


Now if we are storing the data in config map , then there are 2 points where the hacker can access our data -
- From cluster access - using kubectl edit or kubectl describe 
- From Etcd 

Now the 2nd problem is solved using Encryption but the 1st problem still exists as the hacker can still use kubectl describe in the secrets .

So to solve this problem , It is recommended to use strong ***RBAC*** . It uses the concept of #Least_Previllage ,  

Config Map .yaml
```
apiVersion: v1
kind: ConfigMap
metadata:
  name: my-spring-cm
data:
  db-port: "3306"

```


Now to chk config map -
`kubectl get cm`

Now to chk the data in  it 
`kubectl describe cm my-spring-cm`

Now we are going to take the field from here and going to make it as env. variables in our pods .

Now to get inside a pod 
`kubectl exec -it podName -- /bin/bash`

Now to get env the command is `env` and to get db in that the command is 
`env | findstr db`

But now suppose , if we want the info about the mysql that i am using in my application , so for that we have to update our deployment file and add these lines to it 
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-spring-app
spec:
  replicas: 2
  selector:
    matchLabels:
      app: my-spring-app
  template:
    metadata:
      labels:
        app: my-spring-app
    spec:
      containers:
        - name: my-spring-app
          image: my-spring-app
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8080
          env:
            - name: DB_PORT
              valueFrom:
                configMapKeyRef:
                  name: my-spring-cm
                  key: db-port

```
Inside containers , we have to add the field env in it . where we give a name to that var , from where we are taking the reference of that var , from which config map and what is the key that value we are taking .

This allows us to inject configuration values into the container at runtime from external Config Maps, promoting separation of config from code.

Now before on this command it was showing nothing but now 
```
root@my-spring-app-7b8cff4bd5-d8qd8:/app# env |grep DB
DB_PORT=3306
```

Now the dev can just use `os.env("DB_PORT")` to retrieve this , however now there is an issue - 

But now suppose this port is busy and we changed the port no. to 3307 in config Map then still when we check the env , it will still be using the old port no. that is 3306, the pod won't know about the change  . So now to solve this issue . 

So what k8s says that , if we this type of info that keeps changing then changing inside container is not possible , that is inside env is not possible . K8 don't allows this .
We have to recreate the container that is restart it  , but it could lead to some traffic loss. 

So instead of this approach , k8 provides ***Volume Mounts*** .
So here instead of using env vars , we use file that is the info of the map gets saved in the file and we mount this file .

So we remove the env part from the deployment file , and we use the volumes field at the level of the  containers .
This volume reads the info from the config Map . 
Now use the volume Mounts field inside the container .Inside we give name and the path on which we want to mount it .
```
 spec:
      containers:
        - name: my-spring-app
          image: my-spring-app
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8080
          volumeMounts:
            - name: db-connection
              mountPath: /opt
      volumes:
        - name: db-connection
          configMap:
            name: my-spring-cm
```

In k8 we can create diff types of volume like internal , external , configMap etc.

Now as we removed this env var so 
```
C:\Users\adity>kubectl exec -it pod/my-spring-app-5fc9668b76-h9f8z -- /bin/bash
root@my-spring-app-5fc9668b76-h9f8z:/app# env | grep DB
root@my-spring-app-5fc9668b76-h9f8z:/app# ls /opt
db-port
root@my-spring-app-5fc9668b76-h9f8z:/app# cat /opt/db-port more
3307cat: more: No such file or directory
root@my-spring-app-5fc9668b76-h9f8z:/app# cat /opt/db-port | more
3307
root@my-spring-app-5fc9668b76-h9f8z:/app#
```

Now inside the opt directory , we have db-port file where the port no. is stored .
Now when we change the port in the config map and apply it , in the db-port this value will be changed ( after few seconds ).

Now same way through secret.yaml , we can create a secret file , it is of many types like - generic , TLS etc. 

Another way of creating secret is -
 `kubectl create secret generic my-spring-secret --from-literal=db-port="3306"`


Now to see this secret 
`kubectl describe secret my-spring-secret`
 it will just give this output -
 ```
Data
====
db-port:  4 bytes
```

Now if we want to edit using `kubectl edit secret my-spring-secret ` , it will give me the encrypted value stored in it . 
```
apiVersion: v1
data:
  db-port: MzMwNg==
```

So if we want to see the real val , we use the command -
`echo MzMwNg== | base64 --decode` 

However cmd may not have base64 installed , so can run this command from cmd to run on powershell 

```
C:\Users\adity>powershell -Command "[System.Text.Encoding]::UTF8.GetString([System.Convert]::FromBase64String('MzMwNg=='))"

3306
```

Now to create secret through deployment file , we can use #secret in place of configMap for volumes and can use #secretKeyReference in case of env .


#### Kubernetes RBAC --

Its essentials can be divided in 2 parts  -
- User Management
- Managing the Access of the services running on the cluster .


So when we are working in minikube or kind , we get out of box (by-default) Admin control of the cluster , however , when we work in org. , the first thing that the dev decides is the level of access each have ( i.e. devs , qe etc. )
So one part of RBAC is how we are going to manage the users and other access and the other thing is how we are going to deal with the services . i.e. if we have created a pod then what access does this pod needs to have on the cluster . i.e. have to access to config map or not , to secrets , can it have the access to delete the content related to the Api Server . So Similar to the user management , we can control the access for the Services or apps running using rbac .

There are 3 major things to create the rbac - 
1) Service Accounts / Users
2) Roles / Cluster Roles 
3) Role Binding / Cluster Role Binding (CRB)


 *User Management*  -
 
1) In Linux , we can create the user and can give its username and pass to other system and they can make perform actions on it . But in k8 , It doesn't deal with user management . It offloads ***User Management*** work to #Identity_providers . 

It is the same way the application instead of creating a account in them , allows the user to login with google or other platforms . (which is done with the help of google oAuth) .

***In K8 , the Api Server acts as the oAuth server .***

There are many external identity provider . - LDAP ,  OKTA  , SSO . we can also use some identity brokers like key cloak . 

K8 natively supports these .

So if suppose , we are using Eks , and we want to login then we can use IAM Users , So for it we have to create somethings like IAM provider or IAM auth provider , and using this IAM the user can login to the cluster . So there are groups in the IAM , so if we are logging with this , users inherit the same **username and group permissions** in the cluster, effectively offloading user management to IAM.


So key cloak works is , suppose we are creating a cluster on amazon Eks , so we can integrate this keycloak to the Eks and using this , we can connect it to our git Hub . and 

In GitHub, groups such as **Contributors, Developers, and Admins** can be created.

- Keycloak maps these groups to corresponding **Kubernetes RBAC roles**, allowing GitHub users to authenticate and access EKS with appropriate permissions.


*Service Management-*
Service Accounts Is similar to how yaml file is created for pods .

`By-Default when we are using a pod , A default service account gets created automatically if we aren't creating a custom one .` And using this our app talks to the Api server or for connecting to any resource in k8s .



Now to define access after the login , k8 supports 2 things -
- Role / Cluster Roles(if defining access for the cluster)
- Role Bindings 

A Role is a yaml file , where we write every thing related to access like to config map , to secrets etc. 
So to however , we attach this role , it will get the access of all the resources mentioned in this . 

Now the connection of Users , Service Accounts and the Roles are done by Role Bindings .
It will bind permissions to users .

![[Pasted image 20250331230503.png]]

------
Can get free open shift shared cluster for 30 days .
provides open shift sandbox using red shift 
Now it also provides token which on executing from the cli , we can access it through our cmd and use commands like kubectl .

------


#### Kubernetes Monitoring --
Prometheus and grafana are monitoring tools used for this purpose .

If we have a cluster , multiple teams can work on this and they may have diff. problems and there could be many clusters , so for this , we use these tools .

Prometheus developed by sound cloud , now completely open source . Grafana is used for visualization .

Grafana can use multiple data sources and Prometheus is one of the data sources .

***Architecture of Prometheus --***
![[Pasted image 20250331232208.png]]

So when we install Prometheus , it has prometheus server which has http server . In k8 Api server exposes metrics to Prometheus server our of box . Prometheus stores this info in an time-series database .
We can configure prometheus with alert manager and can send the notification to different platforms like slack , email . So if we set up alert manager , prometheus can push the alert to alert manager which can be configured to send the alert to diff. places .
We can also go to Prometheus UI and execute promql that is prometheus queries to get results .



Now we will install these using helm or operators , these offer tool lifecycle management of our k8 controllers , like for automatic update .

So 
```
For Installing Prometheus using helm 
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update
helm install prometheus prometheus-community/prometheus
```

Now on running get pods , we will see the prometheus node exporter pods .

![[Pasted image 20250401155901.png]]

The K8 Api server exposes few metrics of our k8 default installations and others but as we are monitoring the k8 cluster , we may need more info like replica count so for that #Kube_state_metrics controller is created and so we can create a service for this and use this at the metrics endpoint . 
Now this controller is downloaded by default in helm . 

Now if we see the svc, so we can see that its service uses the Cluster Ip node , now we have to expose it so that we can see its UI .

Command to expose is 
`kubectl expose service prometheus-server --name=prometheus-service --target-port=7070 --type=NodePort --name=prometheus-server-ext`


Now though normally we use the minikube Ip + forwarded port but as running on windows , i am starting a tunnel on prometheus server .

`minikube service prometheus-server`

Now to install grafana -
`helm repo add grafana https://grafana.github.io/helm-charts`
`helm repo update`
`helm install grafana grafana/grafana`


Now the it says that the `admin` is the user and we will get the password using command 
run this in cmd and copy output
`kubectl get secret --namespace default grafana -o jsonpath="{.data.admin-password}"` 
Paste the output  in this command in powershell to get the password 
` powershell -Command "[System.Text.Encoding]::UTF8.GetString([System.Convert]::FromBase64String('<Enter String Here>'))"`

Now we will expose this grafana -  through tunneling
In production env , we don't have to do this as we would have ingress and we will just route using it to grafana .

Now we have to create prometheus as a datasource to grafana .

Now here we can't give the link on which our prometheus is working , as i have made it work using tunneling on host windows , but the grafana is working inside the minikube cluster so i can use local Ip for making them connect .

**use 
`http://prometheus-server.default.svc.cluster.local:80`**
instead of
`127.0.0.1:53173`.


Now in grafana , we can create a dashboard but however , we can also import a dashboard using the dashboard id .
Grafana itself have made some simple dashboard using promql , which we can use . 

`3662` in Grafana is a standard template .
So what is doing is , it is connected to prom. and it is executing the queries on that and getting the result in json format there . It is taking the result from there and showing it on the grafana dashboard .

Now if we want more info than the default , we are going to use #Kube_State_metrics .
We can tunnel it also . 

In that , on clicking on metrics , we get more info .
Now whatever info is present in this , we can use this query inside grafana or we can query this . 

Now to get this info inside the prometheus , we can use 
`kubectl get cm`
`kubectl edit cm prometheus-server`

It has all the info that the prometheus is scraping .

In Scrape config , it would mention , what is the localhost from where it is getting the info , now we want to add kube metrics so we will add a new entry in this .

We can also write metric server in case it is fetching metrics from multiple sources , or the app is unable to expose the metric to the prometheus .


#### Custom Resources --

There are functionalities provided by the k8 out of the box like pods , services , secret etc. but if we want to introduce new resource to the k8 i.e. want to extend the Api Server of the K8 maybe for the advanced securities or for using Istio , Argo CD , keycloak .
To Extend the capabilities -  we need 
- CRD ,
- CR ,
- Custom Controller .


CRD - Custom resource definition , it means the company that is providing this additional resource is defining an API to the kubernetes .
It is an yaml file , in which it is mentioned what all things , we can create . Like when we create a deployment.yaml file , there is a template where it is mentioned what all fields i can include this deployment.yaml file .

So this yaml file will have all the possible options support .
And now whatever user is submitting is known as custom resource .

Resource Definition validates whether the resource created is valid or not .
Now we have created a CR in K8 cluster and validated against CRD , but we still need controller for maintaining replica set and other things. like we have k8 controller for maintaining out of box services .

For this , we have Custom Controller . which is already deployed in the k8 cluster .
Now this custom Controller can be created across the cluster or it can be created for a particular namespaces .
Now this will perform actions .

K8 Initially was written in Golang . so the preferred way to write the controller is in golang .
So for this , we also have to create resources like watchers and reflectors .




