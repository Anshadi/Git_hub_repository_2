
![[Pasted image 20240915140821.png]]
![[Screenshot (776).png]]

![[Screenshot (775).png]]

![[Screenshot (774).png]]

![[Pasted image 20240924211034.png]]


![[Screenshot (773).png]]

![[Pasted image 20240915141617.png]]![[Pasted image 20240915141648.png]]

The structure of the payment notification and payment consumer must be same otherwise error will occur .
![[Pasted image 20240915144102.png]]

![[Pasted image 20240915144328.png]]
![[Pasted image 20240915144417.png]]![[Pasted image 20240915144626.png]]

We enabled async because we don't want to block the whole process when we send the mail .![[Pasted image 20240915144751.png]]

- `JavaMailSender` is used for sending emails, and `MimeMessageHelper` helps construct MIME messages.
- `Context` from Thymeleaf holds the variables for the email template.

Here 1st we create the object of mimemessage , and then pass two things to helper that is the object we created and MIME_MESSAGE_HELPER.MULTIPART_MODE_RELATED and the encoding that is ` StandardCharsets.UTF_8.nameO`

Then we will set from where we are sending the email 
![[Pasted image 20240915145659.png]]
![[Pasted image 20240924143048.png]]

Now we will create the enum email template ![[Pasted image 20240915145532.png]]
Here template is what name will be shown and what will be its subject .


Since we are working with thymleaf we will need to create context .

Context is the container that will hold all the variables .

So now inside the try block we will  create a template object which will process the template Engine which will contain two things template name and the context 


The template name is html template that we want to process

![[Pasted image 20240915153210.png]]
log contains two things destination email , template name .


Now will do the same for the order confirmation email 
![[Pasted image 20240915153354.png]]![[Pasted image 20240915153429.png]]![[Pasted image 20240915155059.png]]![[Pasted image 20240915155151.png]]![[Pasted image 20240915155856.png]]![[Pasted image 20240915161026.png]]In json type mapping there is two things Orderconfirmation and ProductConfirmation 
![[Pasted image 20240915161051.png]]

Now will do the same in the notification-service.yml file but remove the application name and will add the server.port number there .

Now in the application properties 
![[Pasted image 20240915161337.png]]

Now in the resources folder, we will create a folder named template where we will create the template we were using above .![[Pasted image 20240915161515.png]]
Generated with the help of chatgpt with context ![[Pasted image 20240915161611.png]]
same will do for the order configuration 
![[Pasted image 20240915161730.png]]![[Pasted image 20240915161745.png]]

