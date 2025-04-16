package com.asthana;

public class Alien {
    private int age;
    // private Laptop laptop;

    private computer com;

    // public Alien(int age) {
    //     this.age = age;
    // }



    public Alien() {
        System.out.println("Object Created");
    }
    
    
    
    public int getAge() {
        return age;
    }
    
    
    public void setAge(int age) {
        System.out.println("Age Assigned");
        this.age = age;
    }
    
    
    // public Laptop getLaptop() {
    //     return laptop;
    // }
    
    
    // public void setLaptop(Laptop laptop) {
    //     this.laptop = laptop;
    // }

    
    
    
    
    public void code() {
        System.out.println("Alien is coding");
        // laptop.compile(); 
        com.compile();       
    }



    public computer getCom() {
        return com;
    }



    public void setCom(computer com) {
        this.com = com;
    }
    
    
    
}
