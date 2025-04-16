package MultiThrerading;

class NewThread implements Runnable {
    String name;
    Thread t;

    NewThread(String threadname) {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New Thread: " + t);
        t.start();
    }

    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(name + ":" + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted");
        }
        System.out.println("Exiting " + name);
    }
}

public class Creating_Multiple_therad {
    public static void main(String[] args) {
        new NewThread("One");           //starts thread
        new NewThread("Two");
        new NewThread("Three");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Main Thread Interrupted");
        }
        System.out.println("Main Thread Exiting");
    }
}
