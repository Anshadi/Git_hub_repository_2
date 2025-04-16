package MultiThrerading;
import java.util.concurrent.atomic.*;

class AtomicDemo {
    public static void main(String[] args) {
        new AtomThread("A");
        new AtomThread("B");
        new AtomThread("C");
    }
}

class Shared {
    static AtomicInteger ai = new AtomicInteger(0);
}

class AtomThread implements Runnable {
    String name;

    AtomThread(String n) {
        name = n;
        new Thread(this).start();
    }

    public void run() {
        System.out.println("Starting " + name);

        for (int i = 0; i < 3; i++) {
            int oldValue = Shared.ai.getAndIncrement(); // Get the old value and increment
            System.out.println(name + " got oldvalue: " + oldValue);
        }
    }
}



//the output you see is a result of the non-deterministic interleaved execution of multiple threads, which is typical behavior in concurrent programming.