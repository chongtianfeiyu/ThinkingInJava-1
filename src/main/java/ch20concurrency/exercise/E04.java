package ch20concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: ViaPro
 * Date: 11/5/13
 * Time: 3:11 PM
 * (1) Repeat Exercise 2 using the different types of executors shown in this section.
 */
public class E04 {
    public static void main(String[] args) {
        //#1
        ExecutorService exec01 = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec01.execute(new Fibonacci(i));
        Thread.yield();
        exec01.shutdown();

        //#2
        ExecutorService exec02 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++)
            exec02.execute(new Fibonacci(i));
        Thread.yield();
        exec02.shutdown();

        //#3
        ExecutorService exec03 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++)
            exec03.execute(new Fibonacci(i));
        Thread.yield();
        exec03.shutdown();
    }
}

/*
Thread[pool-1-thread-1,5,main], ID = 1, Line0: []
Thread[pool-1-thread-3,5,main], ID = 3, Line2: [1, 1]
Thread[pool-1-thread-5,5,main], ID = 5, Line4: [1, 1, 2, 3]
Thread[pool-1-thread-4,5,main], ID = 4, Line3: [1, 1, 2]
Thread[pool-1-thread-2,5,main], ID = 2, Line1: [1]
Thread[pool-2-thread-1,5,main], ID = 6, Line0: []
Thread[pool-2-thread-2,5,main], ID = 7, Line1: [1]
Thread[pool-2-thread-4,5,main], ID = 9, Line3: [1, 1, 2]
Thread[pool-2-thread-5,5,main], ID = 10, Line4: [1, 1, 2, 3]
Thread[pool-2-thread-3,5,main], ID = 8, Line2: [1, 1]
Thread[pool-3-thread-1,5,main], ID = 11, Line0: []
Thread[pool-3-thread-1,5,main], ID = 12, Line1: [1]
Thread[pool-3-thread-1,5,main], ID = 13, Line2: [1, 1]
Thread[pool-3-thread-1,5,main], ID = 14, Line3: [1, 1, 2]
Thread[pool-3-thread-1,5,main], ID = 15, Line4: [1, 1, 2, 3]
*/