package concurrency;

/**
 * User: ViaPro
 * Date: 11/8/13
 * Time: 10:00 AM
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    @Override
    public int next() {
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}

/*
Press Control-C to exit
10253 not even!
10253 not even!
*/