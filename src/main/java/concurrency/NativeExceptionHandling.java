package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: ViaPro
 * Date: 11/6/13
 * Time: 10:56 AM
 * 这种方式不能够捕获异常！！！
 */
public class NativeExceptionHandling {
    public static void main(String[] args){
         try{
             ExecutorService exec = Executors.newCachedThreadPool();
             exec.execute(new ExceptionThread());
         }    catch (RuntimeException e){
             System.out.println("Exception has been handled! ");
         }
    }
}
