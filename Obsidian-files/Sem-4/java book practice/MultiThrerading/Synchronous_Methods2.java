
//Example 2

package MultiThrerading;

class MyClass {
    private int counter = 0;

    // Synchronized method
    synchronized void synchronizedMethod() {
        // Increment counter within synchronized block
        counter++;
        System.out.println("Thread " + Thread.currentThread().threadId() + " incremented counter to " + counter);

        // Sleep to simulate some processing
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Non-synchronized block
        // Increment counter outside synchronized block
        counter++;
        System.out.println("Thread " + Thread.currentThread().threadId() + " incremented counter again to " + counter);
    }

    // Non-synchronized method
    void nonSynchronizedMethod() {
        // Just printing a message
        System.out.println("Thread " + Thread.currentThread().threadId() + " is in the non-synchronized method");
    }
}

 class Example2 {
    public static void main(String[] args) {
        MyClass obj = new MyClass();

        // Create multiple threads to call synchronizedMethod() and nonSynchronizedMethod()
        Thread thread1 = new Thread(() -> {
            obj.synchronizedMethod();
            obj.nonSynchronizedMethod();
        });

        Thread thread2 = new Thread(() -> {
            obj.synchronizedMethod();
            obj.nonSynchronizedMethod();
        });

        thread1.start();
        thread2.start();
    }
}
