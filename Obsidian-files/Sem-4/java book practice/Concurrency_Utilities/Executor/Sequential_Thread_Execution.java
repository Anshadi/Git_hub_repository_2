package Concurrency_Utilities.Executor;




public class Sequential_Thread_Execution {
    public static void main(String[] args) {
        Thread previousThread = null;
        
        for (int i = 1; i <= 10; i++) {
            Thread currentThread = new Thread(new Task(i, previousThread)); // Pass thread number and the previous thread
            currentThread.start();
            previousThread = currentThread;
        }
    }
    
    static class Task implements Runnable {
        private final int threadNumber;
        private final Thread previousThread;
        
        public Task(int threadNumber, Thread previousThread) {
            this.threadNumber = threadNumber;
            this.previousThread = previousThread;
        }
        
        public void run() {
            try {
                if (previousThread != null) {
                    previousThread.join(); // Wait for the previous thread to finish
                }
                System.out.println("Thread " + threadNumber + " is currently running");
                Thread.sleep(100); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread " + threadNumber + " interrupted");
            }
        }
    }
}

// For sequential execution of threads  using join()