package MultiThrerading;

class NewThread implements Runnable{
    String name;
    Thread t;                       //here only thread space is generated

    NewThread(String threadname)                // Newthread class constructor
    {
        name = threadname;
        t = new Thread(this,name);      //here thread is allocated its data
        System.out.println("New thread:"+ t);
        t.start();
    }

    //this is entry point for thread
    public void run(){
        try{
            for(int i=3 ;i>0;i--){
                System.out.println(name+":"+i);
                Thread.sleep(1000);
            }
        }
         catch(InterruptedException e){
            System.out.println(name+"interrupted");
        }
        System.out.println(name+"exiting");
    }
}




class isAlive_and_join {
public static void main(String[] args) {
    NewThread ob1 = new NewThread("One");
    NewThread ob2 = new NewThread("Two");
    NewThread ob3 = new NewThread("Three");

    System.out.println("Thread One is Alive "+ ob1.t.isAlive());
    System.out.println("Thread Two is Alive "+ ob2.t.isAlive());
    System.out.println("Thread Three is Alive "+ ob3.t.isAlive());
    
    try {
        System.out.println("Waiting for Threads to Finish");
        ob1.t.join();
        ob2.t.join();
        ob3.t.join();
    } catch (Exception e) {
        System.out.println("Main thread Interrupted");
    }
    
    System.out.println("Thread One is Alive "+ ob1.t.isAlive());
    System.out.println("Thread Two is Alive "+ ob2.t.isAlive());
    System.out.println("Thread Three is Alive "+ ob3.t.isAlive());
    
    System.out.println("Main thread Exiting");
}
}