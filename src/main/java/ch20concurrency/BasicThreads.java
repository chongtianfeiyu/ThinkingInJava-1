package ch20concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 11/2/13
 * Time: 7:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class BasicThreads {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff~");
    }
}
