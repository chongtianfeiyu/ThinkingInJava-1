package concurrency;
import static util.Print.*;

/**
 * User: ViaPro
 * Date: 11/8/13
 * Time: 4:10 PM
 */
public class NotifyVsNotifyAll {
}

class Blocker{
    synchronized void waitingCall(){
        try{
            while(!Thread.interrupted()){
                wait();
                print(Thread.currentThread()+" ");
            }
        } catch (InterruptedException e){
            //OK to exit this way?
        }
    }
    synchronized void prod(){notify();}
    synchronized void prodAll(){notifyAll();}
}

class Task implements Runnable{
    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}

class Task2 implements Runnable{
    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}
