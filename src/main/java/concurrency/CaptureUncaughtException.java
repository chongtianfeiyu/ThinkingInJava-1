package concurrency;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static util.Print.*;

/**
 * User: ViaPro
 * Date: 11/6/13
 * Time: 11:00 AM
 */

class ExceptionThread2 implements Runnable{
    public void run(){
        Thread t = Thread.currentThread();
        print("run() by "+t);
        print("eh = "+t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

//构建了一个异常处理器
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        print("caught "+e);
    }
}

class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        print(this +"creating new Thread");
        Thread t = new Thread(r);
        print("created "+ t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        return t;
    }
}

public class CaptureUncaughtException {
    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread2());
    }
}
