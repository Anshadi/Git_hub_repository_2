package MultiThrerading;


//Sometimes even using synchronized keyword doesn't ensure that the result going to be  in a order.
//In such cases we should use wait and notify method

//An incorrect implementation of producer and consumer

// class Q{
//     int n;

//     synchronized int get(){
//         System.out.println("Got"+ n);
//         return n;
//     }

//     synchronized void put(int n){
//         this.n = n;
//         System.out.println("Put"+ n);
//     }
// }

// class Producer implements Runnable{
//     Q q;

//     Producer(Q q)                                   //inside constructor we have to assign value to parameter passed and create thread and start which calls to run()
//     {
//         this.q=q;
//         new Thread(this,"Producer").start();;
//     }

//     public void run(){                              //here we want to write what we want the thread to do
//         int i=0;
//         while(true)
//             q.put(i++);
//     }
// }

// class Consumer implements Runnable{
//     Q q;

//     Consumer(Q q)
//     {
//         this.q=q;
//         new Thread(this,"Consumer").start();
//     }

//     public void run(){
//         while(true)
//             q.get();
//     }
// }

// class PC {
//     public static void main(String[] args) {
//         Q q = new Q();
//         new Producer(q);
//         new Consumer(q);

//         System.out.println("Press Control-C to stop.");
//     }
// }



//correct implementation of consumer and producer


class Q{
    int n; 
    boolean valueset = false ;

    synchronized int get(){                   //getter  // it checks whether the valueset is true or not , if false , then it waits untill the method is occupied 
        while (!valueset){
            try{
                wait();
            }catch(InterruptedException e){
                System.out.println("Interrupted Exception occured");
            }

            System.out.println("Got : "+ n);            //once occupied then it prints the value of that thread . and return that value .
            valueset = false ;                          //again set the valueset to true , which means the thread has left the occupied method . so notify all the waiting thread. 
            notify();
            return n ;
            }
            return -1;
        }

    synchronized void put(int n){                   //setter
        while (valueset){                       //it checks whether the valueset  is false or true , it means the value is already been set , 
            try{                                //so now thread goes into the waiting state untill it wakes up , them value n is set to it. 
                    wait();                         
            }catch(InterruptedException e){
                System.out.println("Interrupted Exception occured");
            }
        }
        this.n = n ;
        valueset = true ;                       //and again set the value of valueset to true as new thread has occupied the method.
        notify();
        return ;
    }

    }


class Producer implements Runnable{
    Q q;

    Producer(Q q){
        this.q = q ;
        new Thread(this , "Producer").start();
    }

    public void run(){
        int i =0;

        while (true){
            q.put(i++);
        }
    }
}

class Consumer implements Runnable{
    Q q;

    Consumer(Q q){
        this.q=q;
        new Thread(this , "Consumer").start();
    }

    public void run(){
        while(true){
            q.get();
        }
    }
}

class PCFixed {
    public static void main(String[] args){
        Q q = new Q();
        new Producer(q);
        new Consumer(q);

        System.out.println("Press Control-C to stop");
    }
}