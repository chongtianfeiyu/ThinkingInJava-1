package concurrency;

/**
 * User: ViaPro
 * Date: 11/6/13
 * Time: 11:27 AM
 */
public abstract class IntGenerator {

    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }

}