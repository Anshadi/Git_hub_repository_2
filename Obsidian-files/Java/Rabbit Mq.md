![[Pasted image 20241213142811.png]]

![[Pasted image 20241210175635.png]]
![[Pasted image 20241210175735.png]]
![[Pasted image 20241210175809.png]]![[Pasted image 20241210175944.png]]![[Pasted image 20241210180045.png]]![[Pasted image 20241210180239.png]]![[Pasted image 20241210180346.png]]![[Pasted image 20241210180441.png]]![[Pasted image 20241210180739.png]]![[Pasted image 20241210180951.png]]![[Pasted image 20241210181019.png]]![[Pasted image 20241210181037.png]]![[Pasted image 20241210202538.png]]![[Pasted image 20241210203456.png]]![[Pasted image 20241210204319.png]]![[Pasted image 20241210205023.png]]![[Pasted image 20241210205036.png]]![[Pasted image 20241210205439.png]]![[Pasted image 20241210205714.png]]![[Pasted image 20241210210244.png]]![[Pasted image 20241211152559.png]]![[Pasted image 20241211153553.png]]![[Pasted image 20241211153917.png]]![[Pasted image 20241211154448.png]]

Here all the imports will be of AMQP type -


Now creating RestApi To send Json Object .
![[Pasted image 20241211205249.png]]

In connections tab , we can see all the tcp and Ip connections .

Though the rabbit config also asks for configuration of 3 more things 
- Connection Factory
- Rabbit Admin
- Rabbit Template
but spring boot by default provides there implementation .

`ConvertandSend()` Takes 3 parameter - Exchange , Routing Key and the Object that is to be sent .

`docker run -d --name rabbitmq-container -p 5672:5672 -p 15672:15672 rabbitmq:management`
