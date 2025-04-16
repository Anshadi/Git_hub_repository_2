
![[Pasted image 20241019132922.png]]

In the rate Limiter that is easily implemented through the Redis , Here the Bucket Token Algorithm is used , where in the configuration we mention the no. of tokens in the bucket that will be replenished , the no. of tokens we can take out of the bucket that is the number of the request we can make and each request costs how many tokens .
![[Pasted image 20241019141325.png]]


For Circuit breaker , we can use the resilliance4J  .  