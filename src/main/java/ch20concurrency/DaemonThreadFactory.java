package ch20concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 11/5/13
 * Time: 11:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class DaemonThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        System.out.println("DaemonThreadFactory newThread...");
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
