package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.Print.*;

/**
 * User: ViaPro
 * Date: 11/8/13
 * Time: 3:24 PM
 * Output：
 Wax On!Wax Off! Wax On!Wax Off! Wax On!Wax Off! Wax On!Wax Off! Wax On!Wax Off! Wax On!Wax Off! Wax On!Wax Off! Wax On!Wax Off! Wax On!Wax Off! Wax On!Wax Off! Wax On!Wax Off! Wax On!Wax Off! Wax On!
 Ending Wax On task
 Ending Wax On task
 Exiting via interrupt
 Ending Wax Off task
 */
public class WaxOMatic {
    public static void main(String[] args) throws Exception{
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5); //让任务运行5s
        exec.shutdownNow();  //中断所有任务
    }
}

class Car {
    private boolean waxOn = false;//上蜡状态

    public synchronized void waxed() {    //上蜡
        waxOn = true;
        notifyAll();//在上蜡后唤醒其他所有挂起线程？
    }

    public synchronized void buffed() {   //抛光
        waxOn = false;
        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn)//如果未上蜡，等待上蜡
            wait();
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn)  //如果已经上蜡，等待抛光
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car c) {
        car = c;
    }

    @Override
    public void run() {
        try {
            // 只要任务没有被中断时，那么执行上蜡操作，并改变上蜡状态，并使得汽车进入等待抛光的挂起
            while (!Thread.interrupted()) {
                printnb("Wax On!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            print("Ending Wax On task");
        }
        print("Ending Wax On task");
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car c) {
        car = c;
    }

    @Override
    public void run() {
        try {
            // 当任务没有被中断时，使得汽车进入等待上蜡状态并对其抛光
            while (!Thread.interrupted()) {
                printnb("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
                car.waitForWaxing();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt ");
        }
        print("Ending Wax Off task");
    }
}