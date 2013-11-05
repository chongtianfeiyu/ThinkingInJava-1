package concurrency.exercise;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 11/5/13
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 * Exercise 1: (2) Implement a Runnable. Inside run( ), print a message, and then call yield( ).
 * Repeat this three times, and then return from run( ). Put a startup message in the constructor and a shutdown message when the task terminates.
 * Create a number of these tasks and drive them using threads.
 */
public class E01 {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new Thread(new MyTask()).start();
    }
}

class MyTask implements Runnable {

    private static int taskCount = 0;
    private final int ID = ++taskCount;

    public MyTask() {
        System.out.println("Start Task " + Thread.currentThread() + ", Task's ID = " + ID);
    }

    @Override
    public void run() {
        int i = 3;
        while (true) {
            System.out.println("Task ID = " + ID + " inside Run Method i = " + i);
            Thread.yield();
            if (i-- <= 1) {
                System.out.println("Terminate Task, ID = "+ID);
                return;
            }
        }
    }
}

/* Output: (Sample)
Start Task Thread[main,5,main], Task's ID = 1
Start Task Thread[main,5,main], Task's ID = 2
Start Task Thread[main,5,main], Task's ID = 3
Start Task Thread[main,5,main], Task's ID = 4
Start Task Thread[main,5,main], Task's ID = 5
Task ID = 1 inside Run Method i = 3
Task ID = 2 inside Run Method i = 3
Task ID = 1 inside Run Method i = 2
Task ID = 4 inside Run Method i = 3
Task ID = 5 inside Run Method i = 3
Task ID = 2 inside Run Method i = 2
Task ID = 5 inside Run Method i = 2
Task ID = 4 inside Run Method i = 2
Task ID = 1 inside Run Method i = 1
Task ID = 3 inside Run Method i = 3
Terminate Task, ID = 1
Task ID = 4 inside Run Method i = 1
Task ID = 5 inside Run Method i = 1
Task ID = 2 inside Run Method i = 1
Terminate Task, ID = 5
Terminate Task, ID = 4
Task ID = 3 inside Run Method i = 2
Terminate Task, ID = 2
Task ID = 3 inside Run Method i = 1
Terminate Task, ID = 3
 */
