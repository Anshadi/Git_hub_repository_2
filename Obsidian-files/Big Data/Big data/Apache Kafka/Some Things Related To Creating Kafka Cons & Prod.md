
![[Pasted image 20250224164408.png]]
![[Pasted image 20250224164506.png]]
![[Pasted image 20250224164653.png]]



In Producer Factory , we first create a custom Producer Factory , and then put it in a default Kafka Template and then return it .

In Consumer Factory , we first create a custom Consumer Factory , and then we put it in  or set it in Concurrent Kafka Listener Container Factory  , and then we return it .

![[Pasted image 20250224175053.png]]


The Use of the property in the Kafka -
` spring.kafka.consumer.enable-auto-commit = true`

![[Pasted image 20250224184546.png]]

what this line does is ` container.setConcurrency(3); // Adjust concurrency as needed`
![[Pasted image 20250225033536.png]]
The dependencies of the functions inside it are ![[Pasted image 20250225034737.png]]

Now these are the equvivalent of the things that i code 
![[Pasted image 20250225054737.png]]
![[Pasted image 20250225055013.png

related to the types of the things defined in the code
![[Pasted image 20250225055503.png]]

Same string , string for concurrent message listener , due to the same reason .

![[Pasted image 20250225061209.png]]

This is in general but i have put it there so i can understand how did i have to write in the mongo repository and how it is in general 
![[Pasted image 20250225062430.png]]

