package concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: ViaPro
 * Date: 11/5/13
 * Time: 2:58 PM
 * (1) Repeat Exercise 1 using the different types of executors shown in this section.
 */
public class E03 {
    public static void main(String[] args) {
        //#1
        ExecutorService exec01 = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec01.execute(new MyTask());
        Thread.yield();
        exec01.shutdown();

        //#2
        ExecutorService exec02 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++)
            exec02.execute(new MyTask());
        Thread.yield();
        exec02.shutdown();

        //#3
        ExecutorService exec03 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++)
            exec03.execute(new MyTask());
        Thread.yield();
        exec03.shutdown();
    }
}

/*
Start Task Thread[main,5,main], Task's ID = 1
Start Task Thread[main,5,main], Task's ID = 2
Start Task Thread[main,5,main], Task's ID = 3
Task ID = 1 inside Run Method i = 3
Task ID = 1 inside Run Method i = 2
Start Task Thread[main,5,main], Task's ID = 4
Task ID = 1 inside Run Method i = 1
Task ID = 2 inside Run Method i = 3
Start Task Thread[main,5,main], Task's ID = 5
Task ID = 2 inside Run Method i = 2
Task ID = 3 inside Run Method i = 3
Terminate Task, ID = 1
Task ID = 2 inside Run Method i = 1
Task ID = 4 inside Run Method i = 3
Task ID = 3 inside Run Method i = 2
Terminate Task, ID = 2
Task ID = 4 inside Run Method i = 2
Task ID = 3 inside Run Method i = 1
Task ID = 5 inside Run Method i = 3
Task ID = 4 inside Run Method i = 1
Task ID = 5 inside Run Method i = 2
Terminate Task, ID = 3
Terminate Task, ID = 4
Task ID = 5 inside Run Method i = 1
Terminate Task, ID = 5
Start Task Thread[main,5,main], Task's ID = 6
Start Task Thread[main,5,main], Task's ID = 7
Start Task Thread[main,5,main], Task's ID = 8
Start Task Thread[main,5,main], Task's ID = 9
Start Task Thread[main,5,main], Task's ID = 10
Task ID = 6 inside Run Method i = 3
Task ID = 7 inside Run Method i = 3
Task ID = 8 inside Run Method i = 3
Task ID = 6 inside Run Method i = 2
Task ID = 7 inside Run Method i = 2
Start Task Thread[main,5,main], Task's ID = 11
Start Task Thread[main,5,main], Task's ID = 12
Start Task Thread[main,5,main], Task's ID = 13
Start Task Thread[main,5,main], Task's ID = 14
Start Task Thread[main,5,main], Task's ID = 15
Task ID = 8 inside Run Method i = 2
Task ID = 9 inside Run Method i = 3
Task ID = 6 inside Run Method i = 1
Task ID = 7 inside Run Method i = 1
Task ID = 10 inside Run Method i = 3
Task ID = 8 inside Run Method i = 1
Task ID = 9 inside Run Method i = 2
Terminate Task, ID = 7
Task ID = 9 inside Run Method i = 1
Task ID = 10 inside Run Method i = 2
Terminate Task, ID = 8
Terminate Task, ID = 6
Task ID = 11 inside Run Method i = 3
Task ID = 10 inside Run Method i = 1
Terminate Task, ID = 9
Terminate Task, ID = 10
Task ID = 11 inside Run Method i = 2
Task ID = 11 inside Run Method i = 1
Terminate Task, ID = 11
Task ID = 12 inside Run Method i = 3
Task ID = 12 inside Run Method i = 2
Task ID = 12 inside Run Method i = 1
Terminate Task, ID = 12
Task ID = 13 inside Run Method i = 3
Task ID = 13 inside Run Method i = 2
Task ID = 13 inside Run Method i = 1
Terminate Task, ID = 13
Task ID = 14 inside Run Method i = 3
Task ID = 14 inside Run Method i = 2
Task ID = 14 inside Run Method i = 1
Terminate Task, ID = 14
Task ID = 15 inside Run Method i = 3
Task ID = 15 inside Run Method i = 2
Task ID = 15 inside Run Method i = 1
Terminate Task, ID = 15

Process finished with exit code 0
 */
