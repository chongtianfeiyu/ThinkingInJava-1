package concurrency;

/**
 * User: ViaPro
 * Date: 11/5/13
 * Time: 10:51 PM
 */
public class SelfManaged implements Runnable {
    private int countDown = 5;
    private Thread t = new Thread(this);

    public SelfManaged() {
        t.start();
    }

    public String toString() {
        return Thread.currentThread().getName() + "(" + countDown + "), \n";
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(this);
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new SelfManaged();
    }
}

/*
Thread-2(5),
Thread-1(5),
Thread-4(5),
Thread-0(5),
Thread-3(5),
Thread-4(4),
Thread-1(4),
Thread-2(4),
Thread-1(3),
Thread-4(3),
Thread-3(4),
Thread-0(4),
Thread-3(3),
Thread-4(2),
Thread-1(2),
Thread-2(3),
Thread-1(1),
Thread-4(1),
Thread-3(2),
Thread-0(3),
Thread-0(2),
Thread-3(1),
Thread-2(2),
Thread-0(1),
Thread-2(1),
 */
