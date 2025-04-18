//Synchronized Block
package MultiThrerading;

class Callme{
     void call (String msg){
        System.out.println("[" + msg);
        try {
            Thread.sleep(1000);     // here we have used sleep method and due to call() down , it switches from one thread to another
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}

class Caller implements Runnable{
    String msg;
    Callme target;      //here we have made object of Callme class
    Thread t;

    public Caller(Callme targ ,String s){
        target =targ;
        msg =s ;
        t= new Thread(this);
        t.start();
    }

    public void run(){
        synchronized(target){
        target.call(msg);
    }               
    }
}

class Synch {
    public static void main(String[] args) {
        Callme target = new Callme() ;
        Caller obj1 = new Caller(target, "Hello");
        Caller obj2 = new Caller(target, "Synchronized");
        Caller obj3 = new Caller(target, "World");

        try {
            obj1.t.join(); 
            obj2.t.join(); 
            obj3.t.join(); 
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}
//wait for thread to end


