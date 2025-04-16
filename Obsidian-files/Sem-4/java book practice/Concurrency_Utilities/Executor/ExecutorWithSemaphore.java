// trying to more sequentially exceute thread using semaphore


package Concurrency_Utilities.Executor;
import java.util.concurrent.Semaphore;



public class ExecutorWithSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1); // Only allow one thread to acquire the semaphore at a time

        for (int i = 1; i <= 10; i++) {
            Thread t = new Thread(new Task(semaphore, i)); // Pass semaphore and thread number
            t.start();
        }
    }

    static class Task implements Runnable {
        private final Semaphore semaphore;
        private final int threadNumber;

        public Task(Semaphore semaphore, int threadNumber) {
            this.semaphore = semaphore;
            this.threadNumber = threadNumber;
        }

        public void run() {
            try {
                if (threadNumber != 1) {
                    semaphore.acquire(); // Acquire the semaphore if not the first thread
                }
                System.out.println("Thread " + threadNumber + " is currently running");
                Thread.sleep(100); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread " + threadNumber + " interrupted");
            } finally {
                if (threadNumber != 10) {
                    semaphore.release(); // Release the semaphore if not the last thread
                }
            }
        }
    }
}




