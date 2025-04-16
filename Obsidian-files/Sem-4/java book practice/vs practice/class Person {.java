class Person {
    String name ;
    int age ;
    int height ;

    // Default constructor
    Person() {
        this.name = "";
        this.age = 0;
        this.height = 0;
    }

    // Copy constructor
    Person(Person p2){
        this.age = p2.age ;
        this.height = p2.height ;
        this.name = p2.name ;
    }

    public void happy1(){
        System.out.println("I am happy");
    }
    
    public void happy2(){
        System.out.println(this.name + " is happy");
        System.out.println(this.name + " of age " + this.age + " of height " + this.height + " is happy");
    }
}

public class learnjava{
    public static void main(String args[])
    {
        Person person1 = new Person();
        person1.age= 78 ;
        person1.height = 45 ;
        person1.name = "Aditya";
        
        Person person2 = new Person(person1);

        person1.happy1();
        person1.happy2();
        
        person2.happy1();
        person2.happy2();
    }

public class Oops {
    bank.Account acc1 = new bank.Account() ;
    acc1.name = "Customer 1";
    
}
}
