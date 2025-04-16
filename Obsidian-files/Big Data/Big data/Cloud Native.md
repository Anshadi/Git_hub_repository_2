
Cloud Native Application Architecture
![[Pasted image 20240515222445.png]]
It is mainly used for speed and agility .

A Container is a unit of deployment . It contains everything needed to run .
i.e Code , Runtimes , system tools and system libraries .
Some external dependencies - caches and databases

![[Pasted image 20240515223553.png]]

Docker uses a local cache to store the layers and if the layers is already there , it won't be installed again .
We can write only on the top layer , lower ones are read only .


Orchestrator - which manages the containers -- Kubernetes .