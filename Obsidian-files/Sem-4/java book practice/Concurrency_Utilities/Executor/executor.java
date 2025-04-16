package Concurrency_Utilities.Executor;

public class executor {
    public static void main(String[] args) {

        for(int i=0;i<10;i++)
        {
            Thread t = new Thread(new Task());
            t.start();
        }
    }

static class Task implements Runnable{
public void run(){
    System.out.println("Thread "+Thread.currentThread().getName() + " is cuurently running");
}
}
}


