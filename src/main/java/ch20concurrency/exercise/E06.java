package ch20concurrency.exercise;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * User: ViaPro
 * Date: 11/5/13
 * Time: 4:52 PM
 * (2) Create a task that sleeps for a random amount of time between 1 and 10 seconds,
 * then displays its sleep time and exits. Create and run a quantity (given on the command line) of these tasks.
 */
public class E06 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please provide the quantity of tasks to run");
            return;
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < Integer.parseInt(args[0]); i++)
            exec.execute(new SleepRamdonTime());
        exec.shutdown();
    }
}

class SleepRamdonTime implements Runnable {

    private static Random rand = new Random(47);

    private int taskCount = 1;
    private final int taskID = taskCount++;
    private long costTime;

    @Override
    public void run() {
        costTime = System.nanoTime();
        try {
            TimeUnit.SECONDS.sleep(rand.nextInt(10) + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        costTime = System.nanoTime() - costTime;
        System.out.println(Thread.currentThread() + ":ID=" + taskID + "; costTime = " + costTime + " NanoSecond");
    }
}
/*
Thread[pool-1-thread-4,5,main]:ID=1; costTime = 1999444760 NanoSecond
Thread[pool-1-thread-5,5,main]:ID=1; costTime = 1999431071 NanoSecond
Thread[pool-1-thread-2,5,main]:ID=1; costTime = 4000124908 NanoSecond
Thread[pool-1-thread-3,5,main]:ID=1; costTime = 5999854988 NanoSecond
Thread[pool-1-thread-1,5,main]:ID=1; costTime = 8999651798 NanoSecond
 */
