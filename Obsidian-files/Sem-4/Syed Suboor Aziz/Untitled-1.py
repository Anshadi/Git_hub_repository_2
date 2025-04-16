# class c1:
#     def __init__(self,a,b):
#         self.a=a
#         self.b=b
#     def area(self):
#         print(self.a*self.b)
        
# class c2(c1):
#     def __init__(self,a,b,c):
#         self.a=a
#         self.b=b
#         self.c=c
#     def vol(self):
#         print(self.a*self.b*self.c)
#         super(c2,self).__init__(self.a,self.b)

# o = c2(2,3,4)
# # o.vol()
# o.area()



# class emp :
#     count =0 
#     def __init__(self,name):
#         emp.count+=1
#     def show(self):
#         print(emp.count)
    
# e1 = new  emp() 
# e2 = emp()

# e2.show()




class Players:
    def _init(self,name):
        self.name=name
class team:
    def __init_(self):
        self.player=[]
    
    def addplayers(self,player):
        self.Players.append(player)
P=Players("sachin")

class Players:
    def _init(self,name):
        self.name=name
class team:
    def __init_(self):
        self.player=[]
    
    def addplayers(self,player):
        self.Players.append(player)
P=Players("sachin")
t=team()
t.addplayer(P)
print(t.players[0].name)
P1=Players('Dhoni')
t.addplayer(P1)
print(t.player[1].name)
del t
# print(t.player[1].name)
print(P.name)
        