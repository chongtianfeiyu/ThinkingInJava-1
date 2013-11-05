package concurrency.exercise;

import util.Generator;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * User: ViaPro
 * Date: 11/5/13
 * Time: 3:17 PM
 * (2) Modify Exercise 2 so that the task is a Callable that sums the values of all the Fibonacci numbers. Create several tasks and display the results.
 * About runnable interface:
 * A task that returns a result and may throw an exception. Implementors define a single method with no arguments called call.
 * The Callable interface is similar to Runnable, in that both are designed for classes whose instances are potentially executed by another thread. A Runnable, however, does not return a result and cannot throw a checked exception.
 */
public class E05 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Long>> results = new ArrayList<Future<Long>>();
        for (int i = 0; i < 3; i++)
            results.add(exec.submit(new CallableFib(40 + i)));//在这相当于下个运算的订单！
        displayOtherThings();
        for (Future<Long> f : results)
            try {
                System.out.println("==========" + f.toString() + "=========");
                System.out.println("get:" + f.get());
                System.out.println("isDone:" + f.isDone());
                System.out.println("isCancelled:" + f.isCancelled());
                System.out.println("==========END=========");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

    }

    private static void displayOtherThings() {
        for (int i = 10; i >= 0; i--) {
            System.out.println("在等待的同时进行倒计时输出：" + i);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class CallableFib implements Callable<Long>, Generator<Long> {

    long getCostTime() {
        return costTime;
    }

    private long costTime;

    private static int taskCount = 0;
    private final int ID = ++taskCount;

    private long count = 0;
    private final int n;

    public CallableFib(int n) {
        this.n = n;
    }

    @Override
    public Long call() throws Exception {
        costTime = System.nanoTime();
        long sum = 0l;
        for (int i = 0; i < n; i++)
            sum += next();
        costTime = System.nanoTime() - costTime;
        System.out.println("ID: " + ID + "======> costTime = " + costTime);
        return sum;
    }

    @Override
    public Long next() {
        return fib(count++);
    }

    private long fib(long n) {
        if (n < 2) return 1L;
        return fib(n - 1) + fib(n - 2);
    }

}

/*
可以从结果中看出：确实是多线程运行，main依旧顺序输出。
Output:
在等待的同时进行倒计时输出：10
在等待的同时进行倒计时输出：9
在等待的同时进行倒计时输出：8
在等待的同时进行倒计时输出：7
ID: 1======> costTime = 1787272760
在等待的同时进行倒计时输出：6
在等待的同时进行倒计时输出：5
ID: 2======> costTime = 2745225488
在等待的同时进行倒计时输出：4
在等待的同时进行倒计时输出：3
ID: 3======> costTime = 3806073937
在等待的同时进行倒计时输出：2
在等待的同时进行倒计时输出：1
在等待的同时进行倒计时输出：0
==========java.util.concurrent.FutureTask@b53b098=========
get:267914295
isDone:true
isCancelled:false
==========END=========
==========java.util.concurrent.FutureTask@236ed0f7=========
get:433494436
isDone:true
isCancelled:false
==========END=========
==========java.util.concurrent.FutureTask@40591a4d=========
get:701408732
isDone:true
isCancelled:false
==========END=========
*/