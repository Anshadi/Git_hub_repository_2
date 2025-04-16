//  By implementing Runnable Interface
package MultiThrerading;

class NewThread implements Runnable {
    Thread t;

NewThread(){
    t = new Thread(this,"My thread");     //creates a new thread
    System.out.println("Child Thread: " + t);
    t.start();                              //starts the thread , executes a call to run 

}
@Override                              //can use override or not better way to use 
    public void run() {                  //entry point for the thread created
        try {
            for (int i=5;i>0;i--) {
                System.out.println("child Thread " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted");
        }
        System.out.println("Exiting Child thread");
    }
}


class Demo{
    public static void main(String[] args) {
        new NewThread();        //creates a new thread

        try {
            for (int i=5;i>0;i--) {
                System.out.println("Main Thread " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Main interrupted");
        }
        System.out.println("Exiting Main thread");
    }
    
}

