package ch20concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 11/2/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new Thread(new LiftOff()).start();
        System.out.println("Waiting for LiftOff~");
    }
}
