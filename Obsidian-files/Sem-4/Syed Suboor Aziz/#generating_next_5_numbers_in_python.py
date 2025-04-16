# class Test:
#     def __init__(self):
#         self.__a=12
#         self.__b=13
#         self.__c=14

#     def m1(self):
#         return self.__a,self.__b,self.__c
    
#     t1 = Test()
#     print(t1.m1())
#     a,b,c=t1.m1()
#     print(a,b,c)

#     for i in t1.m1():
#         x= t1.m1()

    
#     def m1_a(self):
#         return self.__a




class Account:
    def __init__(self,aacount_number,account_holder,balance=0,minimum_balance=500):
        self.__account_number=aacount_number
        self.__account_holder=account_holder
        self.__balance=balance
        self._minimum_balance=minimum_balance


        def account_number(self):
            return self.account_number
        
        def account_holder(self):
            return self.account_holder
        
        def balance(self):
            return self.balance
        
        def Withdraw(self,amount):
            if( self.balance - amount < self.minimum_balance ):
                print("insufficient balance . Can't do Transactions")
            else:
                self.balance -=amount
                return self.balance
            
        def Deposit(self,amount):
            self.balance +=amount
            return self.balance
        
