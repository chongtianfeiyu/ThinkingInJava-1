package concurrency.exercise;

/**
 * User: ViaPro
 * Date: 11/14/13
 * Time: 3:11 PM
 * 探索Runnable和Thread的本质区别：
 * 1.
 */
class MyThread extends Thread{

    private int ticket = 10;

    public MyThread(String name) {
        super(name);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void run(){
        while(ticket>0)
            System.out.println(Thread.currentThread().getName()+"卖票---->"+(ticket--));
    }

}

class MyTask1 implements Runnable{

    private int ticket =10;

    @Override
    public void run() {
        while(ticket>0)
            System.out.println(Thread.currentThread().getName()+"卖票---->"+(ticket--));
    }
}


public class RunnableAndThreadTest {
    public static void main(String[] args) throws Exception{
        Runnable task = new MyTask1();
        for (int i=0;i<3;i++){
            Thread t = new Thread(task);
            t.setName("window "+(i+1));
            t.start();
        }


//            new Thread(task).start();
//            new MyThread1("window "+(i+1)).start();

    }
}