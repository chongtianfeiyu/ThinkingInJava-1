package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: ViaPro
 * Date: 11/6/13
 * Time: 11:10 AM
 */
public class SettingDefaultHandler {
    public static void main(String[] args){
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
    }
}
