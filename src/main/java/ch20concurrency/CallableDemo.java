package ch20concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 11/5/13
 * Time: 9:56 AM
 * To change this template use File | Settings | File Templates.
 */
class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public String call() {
        return "result of TaskWithResult" + id;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++)
            results.add(exec.submit(new TaskWithResult(i)));
        for (Future<String> fs : results)
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.print(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
    }
}

/*
result of TaskWithResult0
result of TaskWithResult1
result of TaskWithResult2
result of TaskWithResult3
result of TaskWithResult4
result of TaskWithResult5
result of TaskWithResult6
result of TaskWithResult7
result of TaskWithResult8
result of TaskWithResult9
*/
