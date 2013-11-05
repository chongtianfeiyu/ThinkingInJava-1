package concurrency;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 11/2/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class LiftOff implements Runnable {

    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + ")\n";
    }

    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            Thread.yield();
        }
    }

}
