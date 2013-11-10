package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: ViaPro
 * Date: 11/10/13
 * Time: 1:20 PM
 */
public class AtomicEvenGenerator extends IntGenerator {

    private AtomicInteger currentEvenValue = new AtomicInteger(0);
    @Override
    public int next() {
        return currentEvenValue.addAndGet(2);
    }
    public static void main(String[] args){
        EvenChecker.test(new AtomicEvenGenerator());
    }
}
