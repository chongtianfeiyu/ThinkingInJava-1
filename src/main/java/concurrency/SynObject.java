package concurrency;

import static util.Print.*;

/**
 * User: ViaPro
 * Date: 11/15/13
 * Time: 9:34 PM
 */
class DualSynch{
    private Object syncObject = new Object();
    public synchronized void f(){
        for(int i=0;i<5;i++){
            print("f()");
            Thread.yield();
        }
    }
    public synchronized void g(){
        synchronized (syncObject){
            for(int i=0;i<5;i++){
                print("g()");
                Thread.yield();
            }
        }
    }
}
public class SynObject {
    public static void main(String[] args){
        final DualSynch ds = new DualSynch();
        new Thread(){
            @Override
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();
    }

}
