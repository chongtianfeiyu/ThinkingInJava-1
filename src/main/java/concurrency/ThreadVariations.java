package concurrency;

import java.util.concurrent.TimeUnit;

import static util.Print.*;

/**
 * User: ViaPro
 * Date: 11/5/13
 * Time: 10:57 PM
 */
public class ThreadVariations {
    public static void main(String[] args) {
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();
    }
}

class InnerThread1 {
    private int countDown = 5;
    private Inner inner;

    public InnerThread1(String name) {
        inner = new Inner(name);
    }

    private class Inner extends Thread {

        Inner(String name) {
            super(name);
            start();  //在其构造方法中直接启动
        }

        public void run() {
            try {
                while (true) {
                    print(this);
                    if (--countDown == 0) return;
                    sleep(10);
                }
            } catch (InterruptedException e) {
                print("interrupted");
            }
        }

        public String toString() {
            return getName() + ": " + countDown;
        }
    }

}

class InnerThread2 {
    private int countDown = 5;

    //在其构造方法中创建了匿名内部类并直接启动
    public InnerThread2(String name) {
        new Thread(name) {
            public void run() {
                try {
                    while (true) {
                        print(this);
                        if (--countDown == 0) return;
                        sleep(10);
                    }
                } catch (InterruptedException e) {
                    print("sleep() interrupted");
                }
            }

            public String toString() {
                return getName() + ": " + countDown;
            }
        }.start();
    }

}

class InnerRunnable1 {
    private int countDown = 5;
    private Inner inner;

    private class Inner implements Runnable {
        Thread t;

        Inner(String name) {
            t = new Thread(this, name);
            t.start();
        }

        public void run() {
            try {
                while (true) {
                    print(this);
                    if (--countDown == 0) return;
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                print("sleep() interrupted");
            }
        }

        public String toString() {
            return t.getName() + ": " + countDown;
        }
    }

    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }
}

class InnerRunnable2 {
    private int countDown = 5;

    public InnerRunnable2(String name) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        print(this);
                        if (--countDown == 0) return;
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    print("sleep() interrupted");
                }
            }

            public String toString() {
                return Thread.currentThread().getName() + ": " + countDown;
            }
        }, name).start();
    }
}

class ThreadMethod {
    private int countDown = 5;
    private Thread t;
    private String name;

    public ThreadMethod(String name) {
        this.name = name;
    }

    //在一个普通方法内创建线程并启动。
    public void runTask() {
        if (t == null) {
            t = new Thread(name) {
                public void run() {
                    try {
                        while (true) {
                            print(this);
                            if (--countDown == 0) return;
                            sleep(10);
                        }
                    } catch (InterruptedException e) {
                        print("sleep() interrupted");
                    }
                }
                public String toString() {
                    return getName() + ": " + countDown;
                }
            };
            t.start();
        }
    }
}