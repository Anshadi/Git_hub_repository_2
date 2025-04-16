[https://github.com/krishnaik06/Machine-Learning-Algorithms-Materials/blob/main/1-Simple%20Linear%20Regression.pdf](  Krish Naik)

[[https://www.youtube.com/watch?v=WjLjjx8wSz0&list=PLTDARY42LDV7WGmlzZtY-w9pemyPrKNUZ&index=27]]


### Intro --
DL -- Mimics human Brain

ML  --- 2 ---> supervised and unsupervised  


In supervised --  2  types -- Classification and regression .

Dataset --> dep. features and Indep. features . Ex- Age and Weight 

So can train a ml algo which can give weight on basis of new age .

So In supervised , there is always output Feature .
![[Pasted image 20240606181353.png]]


In classification -- there is always limited dependent or output feature .

However In Regression , there is continuous output . Like House price based on the independent feature Location and House Size .

![[Pasted image 20240606181716.png]]

Algos used in them are --
![[Pasted image 20240606181836.png]]


Now In Unsupervised ML  --- 
Here we solve Clustering Problem . There is no Output .
Ex-- Salary and age given , we can make different groups based on that and can use different algo for them .

Ex-- K-Means clustering , DBSCAN , GMM ,SOM , Autoencoders 


These Takes Dataset ---> Train --> Model --> Hypothesis

New Data Input ---> Hypothesis  ---> output

### Supervised ML --
#### Linear Regression ML Algos-

Here , We make a straight line  **( #Best_Fit_Line )** on basis of given data and then decide the  value of new data given .

#Residual_Error --  It is the distance of the actual value provided initially and their predicted point on the line . Its Summation is called Residual Error .

We try to make the residual error least . That is find best fit line with minimal error .
[[Pasted image 20240606183749.png]]


`C --> Intercept --> Value of y when x is 0.`
`y --> Predicted Output`

can also be written in form of theta -- where x is Input features .
[[Pasted image 20240606184355.png]] When passing through origin theta0 =0 . 

First we define the cost function , then we make a line from the point and then adjust it to make best fit line .
Predicted point -->shown by H theta of (x)
Actual Output - y of i

so   `( predicted point - actual output ) ^ 2 ` -- It  gives the cost function  .
We square it because sometimes it can give +ve and some times negative .

Here divided by m to take average and  divided by 2 because we have to take differentiation to decide slope .

So m is the no. of points given .

![[Pasted image 20240606190646.png]]it is called #Cost_Function. Also known as #squared_error_function .

We can minimize the cost function by minimizing theta 0 and 1 .


Now during plotting , when we made a best fit line and got another line then , to plot it once we will calculate diff. using 1st and 2nd point co-ordination and then 1st and points on x axis.

On Plotting , we will get a curve from these  points .Called #Gradient_Descent also known as #Optimizer .
Now from the points plotted , the one which is most close to the axis is called #Global_Minimum . It tells the point till which we have to train our model .

![[Pasted image 20240606200332.png]]

Now suppose we plot the point Theta 1 somewhere and we have to reach Global Minumum . So for it we use #Repeat_Convergence_Algorithm .
we will use this algo untill we reach the Global Minimum .

As we are here plotting the points from ourselves but in real scenario can't do that .

So to do that , we will find the slope along that point and will see if +ve or -ve .

Here Alpha Known as #Learning_Rate .
We should keep our learning rate less so that it can reach to the point , if kept large then it will jump from one point to another and may never reach the point .
![[Pasted image 20240606201333.png]]


Outline --
![[Pasted image 20240606201608.png]]



Overviefw --
![[Pasted image 20240606211655.png]]


#### Mean Squared Error , Mean Absolute Error And Root MSE--- Different Cost Functions 

```
MSE ==> (1/n)* Summation( i=1 to n) * (Yi-cap of Yi)^2
```

It is differentiable that is we can find the slope of any point.
It should have 1 local minima and global minima .
Not Robust to Outliers .
it changes in unit .

RMSE -- Root of MSE 

##### Mean Absolute Error -
```
MSE ==> (1/n)* Summation( i=1 to n) * (Yi - cap of Yi)
```

It is robust to outliers . as less effect on line as not doing sq. of result here .
However Convergence usually takes more time .

Complex optimization Process.


#### Ridge And Lasso Regression Math Intution --

It prevents Overfitting In linear regression .



When we create a training model , we have 2 dataset i.e training dataset and test dataset .

#Overfitting -- when  our model gives high accuracy when used training dataset i.e the predicted line and points almost overlap each other and when used test dataset , it gives high difference between actual point and predicted point . It is Low Bias and High Variance .
Ex-- 100% Accuracy Overfitting 

#Underfitting -- It happens when both the training dataset and test dataset accuracy is low . It is High Bias and High variance .

We have to make a generalized model of Low Bias and Low Variance .
![[Pasted image 20240606204641.png]]



To prevent Overfitting -- Ridge Regression 
##### Ridge Regression -- Also Known As L2 Regularization .

Here we use a small optimizer i.e.  Cost Function .
Here if we applied in this case , its value will be zero as the points are almost overlapping , So we have to keep it a bit above zero . 

So ridge regression add  ` K = Lambda*(slope)^2`   value to the cost function .
` ( K Assumed ) . It is known as Penalizing Parameter .

If many slopes  ` K = Lambda*(  (slope1)^2 + (slope2)^2 + ... ) `
`
Now again we will get the new line which will have some residual error in this case . as reduced accuracy .

Now again add with  k , Now we have to reduce this error .But not to the extent of 0 .
Now Overfitting Removed .
![[Pasted image 20240606210643.png]]


**As Lambda Inc. Slope dec.** -- Here it can never reach zero .

Overview --
![[Pasted image 20240607131923.png]]
##### Lasso Regression - Also Known As L1 Regularization 

Here we will add  ` Lambda * |mod of slope| `.
 Now it helps in 
- Overfitting and  Feature selection .
In case we have many slopes 
`` Lambda * |mod of slope1 + slope2 + ...| 
As some slopes value are very small which aren't contributing to Best line fitting are neglected through these .


Here it restricts the value of variables which are not highly Co-related 
to 0 with simultaneous values  of Lambda. So removes that Variable.

Overview --
![[Pasted image 20240607132254.png]]

#### ElasticNet Regression ---

It does two things -- 
- Reduce Overfitting 
- Feature Selection 

It's just combination of above 2
![[Pasted image 20240607132839.png]]


#### Logistic Regression -- Used for Classification 

Now the problem with the linear regression was that if any outliers came , it will change the Best Fit Line . So it may fail observation which was before passing due to that .

Now if we take a Binary Classification in which the output is 0 or 1 , due to outliers the output can be more than 1 or less than 0 .

So we have to squash it , which we can only do in logistic regression and not in linear .

Here we use #Best_Line_Fit_Equation and #Sigmoid_Activation_Function.
![[Pasted image 20240607135352.png]]

Sigmoid Activation Function Used to Squash . Denoted By G.

If z>=0 then G>= 0.5 always 
if z<=0 then G<= 0.5 always

Z can also be  written as theta Transpose of x .
![[Pasted image 20240607140439.png]]


Local Minima - The point at which the slope becomes 0  on the line . The points gets stuck there .
It comes in case of Non-Convex Function with global minima.

However in case of Convex Function - It gives no local Minima . Only global Minima .


![[Screenshot (121).png]]
So when we replace the H Theta Of x of Linear Regression  with the above equation . It becomes Convex Function . 
However when we replace it with the H theta of x of Logistic Regression , It becomes the Non-Convex Function .

![[Pasted image 20240607141425.png]]


Now we have issue of Non-Convex Curve which we will convert to Convex - Function .
![[Pasted image 20240607142858.png]]
Now , Using these new cost functions we will get the same curve as the convex function .
![[Pasted image 20240607143640.png]]


Same Cost Function can be represented as 
```
J(θ) = −1/m ​∑ i=1m ​[ yi ​log( hθ​(xi​)) + (1−yi​) log( 1−hθ ​(xi​) ) ]
```
![[Pasted image 20240607144244.png]]

Now if we substitute the cost function to the final Cost Function -- we will get the above function .
We have to repeat it till we get the Global Minima Point .

We have to write a Convergence Algorithm , which will tell us when we will reach to the Global Minima Point .
![[Pasted image 20240607144829.png]]



#### Naive-Baye's ML Algorithm -- Used to solve Classification Problems 

Here we use Baye's theorem -

 #Independent_Events -- Where One event doesn't affect the other event  .

 #Dependent_Events -- Where One event probability affects others prob.
Lets say two Event A and B , then if first A happens and then B 
Then the probability of this sequence happening is Prob. of A + Prob. of (B/A) ;

Prob. of (B/A)  <-- This term is known as conditional Probability . It means Probability of B given A has already happened .
![[Pasted image 20240607153710.png]]

Here  We Consider the  input feature that is x1,x2,x3 as A and the Output Feature y as B .
![[Pasted image 20240607154017.png]]
Application--
![[Pasted image 20240607155942.png]]

Used a table data converting into training dataset --
![[Pasted image 20240607160544.png]]


![[Pasted image 20240607160801.png]]

![[Pasted image 20240607160931.png]]



#### KNN-- K-Nearest Algorithm
Used for both Classification and Regression Problems - 

In case of classification problem -
We plot all the points of the training dataset on the graph. And then 

Now we plot the new test data , lets say it is a Binary Classification i.e. Only 1 & 0.  
Now if we have to check in which category does our new data fall . 
Using KNN , we calculate the K value **( also known as HyperParameter )** .

Lets say k=5 , then it will find 5 nearest data points to it . Then it puts it in that category which is most in that set of 5 .

Nearest Decided --> On basis of Distance --> Either using `Euclidian Distance` and `Manhattan Distance .`
![[Pasted image 20240607163159.png]]
![[Pasted image 20240607163230.png]]


Now in case of Regression Problem --
Ex-  Price On basis of House Size and location .

Here , Also we calculate the K value 
Then take the closest K points and take its average and that will be the output .
![[Pasted image 20240607175211.png]]


KNN -- - Can't Use with huge data set
		- Outliers Sensitive 
		- Sensitive to missing points



#### Bias And Variance --

Training Data related to Bias and Variance with Test Data

Now if our test data is close to our best fit line - it is low variance
![[Pasted image 20240607181348.png]]
When Training Error is High (T.E ) -- Underfitting -- High Bias  -- Not good Model 

T.E low -- low Bias -- low variance -- Generalized Model

Overfitting -- T.E approx. 0 --> Low Bias --High Variance 



In classification problem we use Confusion Matrix  --


#### Model Verification - Performance Matrix

y -->  Actual Output
Y hat --> Predicted value 

In case of Confusion Matrix , we make matrix of predicted and actual output and increases the one in what block both point lies .
![[Pasted image 20240607183726.png]]
When Both 1 --- i.e Correct Prediction -- True Positive
When Both 0 --- True Negative 
When (1,0) --- False positive -- Kuch Na ho raha ho aur hum predict kar de ki hoga
when (0,1)  --- False Negative -- Kuch Ho raha ho aur hum predict kar de ki nahi hoga

Accuracy Of Model is determined by TP + TN / ( TP + TN + FP + FN )
![[Pasted image 20240607183907.png]]


**However We can't directly Apply Accuracy to Imbalance dataset** (EX- 0 Output : 900 Inputs and 1 Output : 100 Inputs ).

So For Imbalance dataset , we use precision and Recall

Precision --->  TP / TP+FP  --- It says of all the predicted result how many are correctly predicted .

Recall ---> TP / TP + FN -- It is the ratio of correctly predicted positive observations to all the actual positives (i.e., the sum of true positives and false negatives).

Now to determine when we use precision and when recall , we need domain expertise .
![[Pasted image 20240607185432.png]]

For Ex - In case of spam Clarification 

If a spam mail comes and our model predicts it 

spam --> spam ( Our Model ) --> good
spam --> not spam ( Our Model ) --> alright
Not spam ---> spam ( Our Model )  --> Blunder 

 So here we have to use the Precision Matrix . Here we have to lessen the FN . 


Now consider predicting whether the person has cancer or not .  Here we will use Recall Method 

Cancer --> Cancer ( Our Model ) --> good
Not Cancer --> Cancer --> Alright
Cancer --> Not Cancer --> Blunder 

Here we have to lessen the FP .

So if tomorrow stock market going to crash --> Here we will focus on both precision and recall because we have to think about the company as well as for the client too .



F-Beta Score -- Performance Matrix . Here we focus on both . 
```
Beta ^ 2 * ( Precision + Recall )
```

1) If B=1 , then It is equal to  Harmonic Mean , FP and FN both important
```
(1+1) * ( P * R ) /( P+R )
```

2)  If B= 0.5 , FP >> FN , FP more imp. than FN 
```
(1 + 0.25) * (P * R) / (0.25)*( P+R )
```

3) If B=2 , FN >> FP , FN is more imp. than FP 
```
(1 + 4) * (P * R) / (4)*( P+R )
```


#### Support Vector Machine -- SVM 

Its 2 types - 
1) Support Vector Classifier -- used for classification problem -- based on logistic regression .
2) Support Vector Regression


##### **Support Vector Classifier** -- It works same as logistic regression . However here 2 more lines are drawn on either side from the point where the nearest point is passing on each side .

These two points are known as support vectors . 
Here the middle line are known as Hyper Plane and line on each side as Marginal Plane . These planes are equvi-distance .
![[Pasted image 20240607200823.png]]

If there is no error -- Hard Marginal Plane  and if there is error , that some points of one side lie beyond its line -- Soft Marginal Line .

![[Pasted image 20240607201324.png]]Here Both Y reprs. the same that is the equation of the straight line .

here W^t x -- means w transpose of x . -- just the way of reps.

**Margin -- The difference between data points and Hyperplane .**

We always chose the Hyperplane with Higher margin . As it has less probability of miscalculation of points .
However there could be a scenario where SVM chooses hyperplane which correctly classifies the classes than maximizing the margin .
![[Screenshot (146).png]]
Here though we have written +1 and -1 as binary classification but convention to write K . 
![[Pasted image 20240608110726.png]]

Now to change transpose of w to vector w, We divide both sides with the magnitude of w .

Now we have to maximize the value ` 2/ |w|` to max. the margin . or to min ` |w| / 2`

The constraints for this are ![[Pasted image 20240608111725.png]]

**HyperParameter - The no. of points we can avoid for miscalculation . Here denoted by C .
Eta -- It is the summation of the distance of misclassified points from the Marginal plane .**


So the total cost function is ![[Pasted image 20240608113341.png]]


##### Support vector Classifier --

Here first we create the Best Fit Line . Then create the  Marginal plane , lets say with distance epsilon from fit line .

Now if the constraint is below epsilon then the predicted planes are good . 
![[Pasted image 20240608114626.png]]
Now the new cost function acc. to  all the points lying outside the plane and the constraints are 
![[Pasted image 20240608114727.png]]

Here both C and Eta is the hyper parameter and also known as Hinge Loss.

We have to ensure that the new points follow the constraints and has to  min. the constraints .


##### **SVM Kernels --**

When we have datasets such that the classes are too much overlapped where our best fit line can not distinguish them clearly which leads to dec. in accuracy .

Here we use SVM kernels . Here we do Transformation . Using Mathematical formula . Converting Lower Dimensional Input space to Higher Dimensional . To make it a Linear Separable data .
![[Pasted image 20240608115709.png]]
![[Pasted image 20240608115742.png]]
![[Pasted image 20240608115839.png]]

Different Kernel tricks are -
![[Pasted image 20240608125547.jpg]]
![[Pasted image 20240608125324.png]]



#### Decision Tree  - Used for both Classification and Regression

##### Decision tree Classifier --
2 Types - ID3 , Cart 

In cart - Whatever Decision Tree we get , it gets divided in Binary form.

1) Entropy and Gini Index --Purity Split
2) Information Gain -- Helps us to decide the feature with which we will split the D.T

Leaf Node - Either all are yes or all are no's . It is known as Pure Split .

In case of Impure Split -  we will again split it based on features till get leaf node .

Now to check whether any split is pure split , we use two methods -
- Entropy
- Gini Index - also Known as Gini Impurity 

P+   <-- Means Probability of yes 
P-  <--- Means Probability of no

Entropy --

If H(S) == 0 --> Pure Split 
When H(S) == 1 --> Complete Impure Split --> means half P+ and Half P- 
Where H(S)  < -- Entropy 

![[Pasted image 20240608132717.png]]



Gini Impurity --
When we have impure split the max value is 0.5 .
Pure Split --> 0
![[Pasted image 20240608132935.png]]

Can use both But
In case of Huge Dataset , we should use Gini Impurity and otherwise Entropy as log in its formula will take more time for execution .


Information Gain  -- It says from which feature we will start splitting and will be splited into which features .

Sv <-- means How much samples are left
S <-- means how much total samples were there 
H(Sv)  <--- Here it is the impure split 

![[Pasted image 20240608134237.png]]
After Calculating the information gain of the samples whichever has the better info. gain we will split from that feature .


##### Post-Pruning And Pre-Pruning --

When  we make a decision tree from a feature , it will reach to node leaf . It leads to the condition of Overfitting - Can prevent it by

- Post-Pruning -- Allow tree to grow entirely then prune some branches .
- Pre- Pruning -- Stop Growing tree before it reaches the perfection .

These are some pruning Techniques which we apply on it to prevent to reach leaf node .

Post-Pruning - In this case , first we make the tree then decide 
- what its depth should be 
- how much max. feature we should take etc .

![[Pasted image 20240608135649.png]]
Here there is no need to go on level 4 as most ans on level 3 is already yes so we can return yes from there . So we can set the level of max depth = 3 i.e. Hyper Parameter .


Pre-Pruning - Here as we construct the tree , we apply the hyper-parameters i.e.
- we define the max depth in some range of values 
- we define max . features beforehand 

Now it will selects values from that range and will se max. accuraccy coming in which feature and will choose that . 



We can apply Post-Pruning with smaller Dataset as we can guess the max depth whereas use Post-Pruning in case of large Dataset .
Some parameters used are ![[Pasted image 20240608140706.png]]



##### Decision Tree Regression --

Here our output is continuous output .

Lets say if we have 2 Continuous input features -- Exp . and age 
1 Continuous output feature -- Salary 

Now if we are splitting from any feature - 
- The first thing it will do is sort the value of that feature .
- Then divide from first and write the output 
![[Pasted image 20240608142651.png]]

Variance Reduction - Here we use it to decide from which feature we should split .
- First we will capture the variance of all root node and child nodes 
- Then calculate Variance Reduction .
- Now which feature will have more variance reduction will be chosen .

Here Y-hat ---> Average Output 
Here Variance == MSE 
![[Pasted image 20240608143349.png]]


Here Wi  <-- How much sample taken / total samples 
![[Pasted image 20240608145204.png]]

In regression average output is calculated . 
![[Pasted image 20240608145547.png]]
That is if any test data is less than 2.5 , then it will come to the right side then its output will be the average of all the leaf nodes of that side .



#### Training Data , Test Data , Validation Data ---

Using Dataset for our model we do ,

1) Data Pre-processing --
2) Feature Engineering --
3) Feature Selection -- Select which features are important 
4)  Model Training --
5) Hyper Parameter Tuning - 
6) Model Deployment -- 




Dataset --  3 Types

- Train Data - Used to train our model 
- Test Data - Used to check the performance of our model -- Performance Matrix
- Validation Data - used for Hyper Parameter Tuning of our model  . Here we take subset of data from the Train data and keep sending it to the Validation data .

![[Pasted image 20240608152032.png]]



One way of Hyper Parameter tuning is  Cross Validation .

In this Lets Say C.V =5 ,  which means we are going to conduct 5 experiments .

Now We will divide the total Training data set by 5 which will be equal to Validation data . then
New training data set = training dataset - Validation data 

Now will calculate the accuracy in each exp. and will take the mean of those exp. accuracies .
![[Pasted image 20240608153409.png]]


#### Types Of Cross Validation --
![[Pasted image 20240608154000.png]]


##### Leave One Out --
Here we if 1000 records are there ,  so each time it will take each record and rest will be the training dataset . 
![[Pasted image 20240608155701.png]]
It will cause most time 
- Time Taking Task
- Overfitting 


##### Hold Out Cross Validation --

Here we will decide a percentage , let's say 70% : 30% and we will keep that much data for training dataset and validation dataset . Training Dataset will always be greater than Validation Dataset .

Here we split in random state , that is 1st time it could be 70:30 then could be 84:16 and same way accuracy can become high or low too .
![[Pasted image 20240608160417.png]]
It Won't Be Able To Work With Imbalanced Dataset .



##### K-fold Validation Dataset --
![[Pasted image 20240608160742.png]]
Disadvantage -
- Won't Work With Imbalanced Dataset 


##### Stratified  K-Fold Validation dataset --

When it will become training and Validation Dataset , it will ensure that the category in it are almost in equal Proportion .
For Ex-  if 900:100 category dataset , then it may take 50 from first category and 50 from another .

In Here , Some Records Can repeat . If the dataset is complete Imbalanced dataset , then it will be impossible to to do it even with the help of this .




##### Time-Series Cross Validation --

Here , We can't take random data , we have to take sequential data based on time . where we put recent data in validation and old data in training .

Lets say we have data for 3 days , then we can put data for 1st two days in training and 3rd in validation . Then when we got data for 8 days then 1st 6 days data in training and last two in validation .
Ex - Usage in Weather Forecast .



#### Ensemble Techniques --

Mainly 2 types --
1) Bagging - Random Forest 
2)  Boosting -- AdaBoost , XGBoost , GradientBoost , CATBoost 

Internally Both in bagging and boosting , decision tree is used . And both are used to solve Both classification and regression problem .

##### Bagging -
Here , all models are parallelly trained .
![[Pasted image 20240608180059.png]]
Here we take the dataset and divide it into parts and may or may not send it to different models based on different techniques and then take the average of their accuracy to get the model,s accuracy . ( For training Dataset )


Now let's say our test data comes , where most model gave output 0 , some gave output 1 , then we will follow 

In Case of Classification Problem -
**Maximum Voting Classifier -** If it is classification problem , as most of the output is 0 , the output of the test data will be 0 .

In case of Regression - Our Output is the Average of the Output of the model combined .



##### Boosting -
It is an Iterative Technique . Decreases the Biasing Error . Works on the Weak Learners Model .


![[Pasted image 20240608181724.png]]

Here the models are connected sequentially .
When training dataset used in 1st model , there will be some wrongly predicted data . Only those will move forward for next model . and same wise .



#### Random Forest --  used to solve both classification and regression 
**Also Known as Bootstrap Aggregation .**


It is a Bagging technique .

Let's say the size of our dataset is D and the no. of features present in them is m . then

Here Only multiple decision tree models are used and these are known as Base Models .

	Here we take the sample data using two techniques -
	1) Row Sampling - not all rows are used
	2) Feature Sampling - not all features are send to the model


Here Row sampling and feature sampling denoted by d' and m' and there size should be less than d and m .

Now in next model some data points and feature can repeat . That's why we call it Row sampling and feature sampling with replacement .


![[Pasted image 20240608184140.png]]

These Base leaners are independently trained .


So In case of 
Classification --  Majority Voting Classifier -- Output 
Regression -- Average -- Output  

Here in case we bring some new data lets say 200 , it still won't effect much our result because this new data will also will be splited to different models .



Q-- We use Random forest though we have decision tree ?

**Ans ---**
Because in decision tree when it splits the data into maximum depth -- overfitting happens -- Training acc. high and Test acc. low --- i.e. --- Low Bias and High Variance . 

Now this high variance can be converted to low variance using Random Forest . As its model too will split the data to max. depth ,  but here we will use sample of rows and samples of features .

![[Pasted image 20240608185421.png]]




#### OOB ( Out Of Bag ) Evaluation of Random Forest --

There are some datapoints in the Dataset which are never selected in the decision tree and hence are never used .
We can use this data for validation purpose . These data are known as out of bag data.

If in random forest , we make the prop. of OOB = true ; then it will automatically take it as validation data.

It is statistically proven that 1/3rd of n data goes to Out-Of-Bag in case of decision tree . Where n is the total training dataset .


Now acc. to  this we can calculate a out of bag score which the accuracy w.r.t to validation dataset or oob dataset .

![[Pasted image 20240608201328.png]]



### Unsupervised ML -- 

These are Clustering Algorithms  Here we have input features but we don’t have output feature . We don’t have to predict something .

Ex- If we have a dataset of salary and spending amount of people , when our new product will be launched , we will provide the discount percentage and email to that group acc. to that .

![[Pasted image 20240609103803.png]]

1) K - Means Clustering -
2) Hierarchical Clustering- 
3) DBScan Clustering -


##### K-Means Clustering –
It just groups same type of datapoints in a set .

1) Here first we have to initialize some K centroids .
2) Then we have to group the points that are nearest to that centroid .
3) Now move the centroid by calculating the mean of the all datapoints for that centroid .
4) Now again step 2 and step 3 .
![[Pasted image 20240609104638.png]]
Until there is no major movement of centroid . 
![[Pasted image 20240609104713.png]]


**Now to select the K value , we have to use the WCSS ( within cluster sum of squares ) method**.  

Let’s say we will initialize k=1 to 20 .

Now using wscc , we will calculate distance from every data points and we will square it .and plot it on wscc and K graph .\

Here we either use Euclidian or Manhattan formula to calculate the distance . 

Now we will take k=2 centroids and do the same with it . Now the wscc will decrease . 
And as we increase the k value , the wscc will decrease but after some time , it will become stable .
![[Pasted image 20240609110201.png]]
This graph is known as **Elbow Method** . So the value after which the graph becomes stable will be the value of our k .
![[Pasted image 20240609110323.png]]


##### Random Initialization Trap – (K- Means ++) – 
\When our centroid is very nearly initialized . 

Ex - Here we wanted the first output but we got the second output 

![[Pasted image 20240609110621.png]]

So here we use **k-Means ++ Initialization technique** , which ensures that when our centroid points are initialized , they are far from each other .



##### Hierarchical Clustering -

It is of 2 types –
1) Agglomerative – 
2) Divisive -


Lets say there are some points in the dataset .
1) Here we will consider each point as a separate cluster .
2) Now find the nearest point and create a new cluster .
3) Now repeat the above steps until we get a single cluster .

So the process we follow from up to down, we call it as Agglomerative . and from down to up , we call it as divisive .

Now we have to find how many clusters –
Here we use the concept of **Dendrogram** 
![[Pasted image 20240609113745.png]]
In Agglomerative Clustering , we make this type of diagram called dendrogram as per or clusters are joining .

Here we consider a threshold value . Here if we consider the threshold =5 then we will se how many datapoints are passing from it and that will be the answer i.e. 2 .
In case our threshold value =3 , then 4 .

![[Pasted image 20240609114349.png]]
To find the threshold , we should try to find the biggest vertical line . such that no horizontal line passes through it . Here that will be when threshold = 5 , and then k = 2 centroids .



Now Between K-Means and Hierarchical –

If our dataset is huge , we should use K-Means Clustering as dendrogram won’t be good for that as it will check on every point .

If our dataset is small , we should use Hierarchical Clustering .


In case of types of data –
If we have numeric data –> we can apply K-Means Clustering or hierarchical Clustering .
In case of Variety of data –> we should use Hierarchical Clustering . due to cosine similarity . used in NLP .


##### DBScan Clustering – 

These techniques are helpful in Non-Linear Clustering and not in straight line  -
1) Core Points -
2) Border Points - 
3) Outliers -



![[Pasted image 20240609115941.png]]

Here we draw the core point and make the circle acc. to the radius that is epsilon . Now if inside the circle , if there are minimum no. of datapoints available , we call the point as the core points .
Here we consider the core point as also a data point .

![[Pasted image 20240609120340.png]]

Now in case of Border Point – 

It is the case when no. of datapoints inside the radius is less than the min no. of points .

![[Pasted image 20240609120643.png]]


Outliers – DBScan is Robust to Outliers .

If there is no data points inside the Circle , we call it as the outliers .
![[Pasted image 20240609120846.png]]



Ex – ![[Pasted image 20240609121147.png]]


##### Silhouette Clustering –

Silhouette Clustering or Silhouette score is a technique through which we can validate clustering .

It has 3 major steps –

1) Whenever we create a cluster , we find the distance of a cluster from all other clusters and find the mean of it by dividing by all the points -1 , not considering the point we have taken .

	Through this we will get a[i] ;
	![[Pasted image 20240609122130.png]]

2)  Now suppose we have two clusters , one in which we have already calculated the mean  from    a point of all others point inside of it .
	Now in that cluster , we will find the point which will be the closest from the another cluster and will find the mean of the distance of all the other points of that cluster from this point .
	Here C[ j ] means no. of data points in cluster j . From here we will get B[ i ].
	It is done for all the clusters present .
	![[Pasted image 20240609123748.png]]


3)  Now we will calculate Silhouette Score -- s[i]
		We know that 
		If a[i] > b[i] , then that is  a good clustering because the internal clustering distance is less than the other cluster distance  .
		If a[i] < b[i] , then it is a bad clustering .
		The more s[i] is close to +1 , the better it is . The more it is close to -1 , the worse it is .
		S[i] value ranges from -1 to +1 .![[Pasted image 20240609123846.png]]
	