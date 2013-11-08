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
        //在这个位置
        ++currentEvenValue;
//        Thread.yield();    //加速产生线程不安全
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