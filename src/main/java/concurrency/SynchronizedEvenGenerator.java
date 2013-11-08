package concurrency;

/**
 * User: ViaPro
 * Date: 11/8/13
 * Time: 11:05 AM
 * 添加synchronized关键词后，没有再出现并发问题
 */
public class SynchronizedEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    //默认是对this加锁，可以说synchronized把这个方法块变成原子性的吗？？？
    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args){
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
