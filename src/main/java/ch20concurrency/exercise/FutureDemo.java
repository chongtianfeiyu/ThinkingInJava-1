package ch20concurrency.exercise;

import java.util.concurrent.*;

/**
 * User: ViaPro
 * Date: 11/5/13
 * Time: 3:55 PM

 在Java中，如果需要设定代码执行的最长时间，即超时，可以用Java线程池ExecutorService类配合Future接口来实现。
 Future接口是Java标准API的一部分，在java.util.concurrent包中。Future接口是Java线程Future模式的实现，可以来进行异步计算。
 Future模式可以这样来描述：我有一个任务，提交给了Future，Future替我完成这个任务。期间我自己可以去做任何想做的事情。
 一段时间之后，我就便可以从Future那儿取出结果。就相当于下了一张订货单，一段时间后可以拿着提订单来提货，这期间可以干别的任何事情。
 其中Future 接口就是订货单，真正处理订单的是Executor类，它根据Future接口的要求来生产产品。
 Future接口提供方法来检测任务是否被执行完，等待任务执行完获得结果，也可以设置任务执行的超时时间。这个设置超时的方法就是实现Java程序执行超时的关键。
 Future接口是一个泛型接口，严格的格式应该是Future<V>，其中V代表了Future执行的任务返回值的类型。 Future接口的方法介绍如下：
 boolean cancel (boolean mayInterruptIfRunning) 取消任务的执行。参数指定是否立即中断任务执行，或者等等任务结束
 boolean isCancelled () 任务是否已经取消，任务正常完成前将其取消，则返回 true
 boolean isDone () 任务是否已经完成。需要注意的是如果任务正常终止、异常或取消，都将返回true
 V get () throws InterruptedException, ExecutionException  等待任务执行结束，然后获得V类型的结果。
 InterruptedException 线程被中断异常， ExecutionException任务执行异常，如果任务被取消，还会抛出CancellationException
 V get (long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException 同上面的get功能一样，
 多了设置超时时间。参数timeout指定超时时间，uint指定时间的单位，在枚举类TimeUnit中有相关的定义。如果计算超时，将抛出TimeoutException
 Future的实现类有java.util.concurrent.FutureTask<V>即 javax.swing.SwingWorker<T,V>。通常使用FutureTask来处理我们的任务

 */
public class FutureDemo {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    ArchiveSearcher searcher = null;

    void showSearch(final String target) throws InterruptedException {
        Future<String> future = executor.submit(new Callable<String>() {
            public String call() {
                return searcher.search(target);
            }
        });
        displayOtherThings(); // do other things while searching
        try {
            displayText(future.get()); // use future
        } catch (ExecutionException ex) {
//            cleanup();
            return;
        }
    }

    private void displayText(String s) {
        System.out.println(s);
    }

    private void displayOtherThings() {
        for (int i = 10; i <= 0; i--) {
            System.out.println("在等待时的输出："+i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

interface ArchiveSearcher {
    String search(String target);
}