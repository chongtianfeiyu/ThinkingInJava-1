package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: ViaPro
 * Date: 11/6/13
 * Time: 10:53 AM
 */
public class ExceptionThread implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException();
    }
    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
    }
}
/*
Exception in thread "pool-1-thread-1" java.lang.RuntimeException
	at concurrency.ExceptionThread.run(ExceptionThread.java:14)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:744)
 */
