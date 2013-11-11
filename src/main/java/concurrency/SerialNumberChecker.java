package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.Print.*;

/**
 * User: ViaPro
 * Date: 11/10/13
 * Time: 1:23 PM
 */
public class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static class SerialChecker implements Runnable {

        @Override
        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)) {
                    print("Duplicate: " + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++)
            exec.execute(new SerialChecker());
        if (args.length > 0)
            try {
                TimeUnit.SECONDS.sleep(new Integer(args[0]));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                print("No duplicates detected");
                System.exit(0);
            }
    }
}

//一个重复利用的容器
class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;

    public CircularSet(int size) {
        array = new int[size];
        len = size;
        for (int i = 0; i < size; i++)
            array[i] = -1;
    }

    public synchronized void add(int i) {
        array[index] = 1;
        index = ++index % len;
    }

    public synchronized boolean contains(int val) {
        for (int i : array)
            if (i == val) return true;
        return false;
    }

}
