package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.Print.*;

/**
 * User: ViaPro
 * Date: 11/12/13
 * Time: 10:22 AM
 * 设计一个入口的人数计数器
 */
public class OrnamentalGarden {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Entrance(i + 1));
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        exec.shutdown();
        if (!exec.awaitTermination(150, TimeUnit.MICROSECONDS))
            print("Some tasks were not terminated!");
        print("Total: " + Entrance.getTotalValue());
        print("Sum of Entrance " + Entrance.sumEntrances());
    }
}

class Entrance implements Runnable {
    public static Count count = new Count();
    public static List<Entrance> entrances = new ArrayList<Entrance>();
    public static volatile boolean canceled = false;//为什么使用volatile来修饰？

    public static void cancel() {
        canceled = true;
    }

    private final int id;
    private int number;//记录每个入口的

    public Entrance(int idn) {
        id = idn;
        entrances.add(this);
    }

    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            print(this + " Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        print("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    @Override
    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalValue() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance e : entrances)
            sum += e.getValue();
        return sum;
    }
}

class Count {
    private int count = 0;
    private Random rand = new Random(47);

    public synchronized int increment() {
        int temp = count;
        if (rand.nextBoolean())
            Thread.yield();
        return count = ++temp;
    }

    public synchronized int value() {
        return count;
    }
}
/*
Entrance 4: 1 Total: 3
Entrance 1: 1 Total: 1
Entrance 5: 1 Total: 2
Entrance 2: 1 Total: 5
Entrance 3: 1 Total: 4
Entrance 2: 2 Total: 6
Entrance 1: 2 Total: 10
Entrance 5: 2 Total: 9
Entrance 4: 2 Total: 7
Entrance 3: 2 Total: 8
Entrance 4: 3 Total: 11
Entrance 5: 3 Total: 15
Entrance 2: 3 Total: 14
Entrance 1: 3 Total: 12
Entrance 3: 3 Total: 13
Entrance 3: 4 Total: 16
Entrance 5: 4 Total: 20
Entrance 1: 4 Total: 18
Entrance 2: 4 Total: 19
Entrance 4: 4 Total: 17
Entrance 4: 5 Total: 21
Entrance 2: 5 Total: 25
Entrance 5: 5 Total: 24
Entrance 3: 5 Total: 22
Entrance 1: 5 Total: 23
Entrance 3: 6 Total: 26
Entrance 1: 6 Total: 27
Entrance 2: 6 Total: 29
Entrance 5: 6 Total: 30
Entrance 4: 6 Total: 28
Entrance 2: 7 Total: 31
Entrance 1: 7 Total: 32
Entrance 5: 7 Total: 33
Entrance 3: 7 Total: 35
Entrance 4: 7 Total: 34
Entrance 3: 8 Total: 36
Entrance 5: 8 Total: 40
Entrance 1: 8 Total: 37
Entrance 4: 8 Total: 38
Entrance 2: 8 Total: 39
Entrance 1: 9 Total: 41
Entrance 3: 9 Total: 45
Entrance 2: 9 Total: 44
Entrance 5: 9 Total: 42
Entrance 4: 9 Total: 43
Entrance 4: 10 Total: 46
Entrance 5: 10 Total: 47
Entrance 2: 10 Total: 50
Entrance 3: 10 Total: 48
Entrance 1: 10 Total: 49
Entrance 1: 11 Total: 51
Entrance 2: 11 Total: 55
Entrance 5: 11 Total: 54
Entrance 3: 11 Total: 53
Entrance 4: 11 Total: 52
Entrance 1: 12 Total: 56
Entrance 4: 12 Total: 60
Entrance 3: 12 Total: 57
Entrance 5: 12 Total: 59
Entrance 2: 12 Total: 58
Entrance 4: 13 Total: 61
Entrance 3: 13 Total: 62
Entrance 2: 13 Total: 63
Entrance 5: 13 Total: 65
Entrance 1: 13 Total: 64
Entrance 2: 14 Total: 66
Entrance 4: 14 Total: 69
Entrance 5: 14 Total: 70
Entrance 1: 14 Total: 68
Entrance 3: 14 Total: 67
Entrance 3: 15 Total: 71
Entrance 5: 15 Total: 72
Entrance 4: 15 Total: 75
Entrance 2: 15 Total: 74
Entrance 1: 15 Total: 73
Entrance 4: 16 Total: 76
Entrance 3: 16 Total: 79
Entrance 2: 16 Total: 80
Entrance 1: 16 Total: 78
Entrance 5: 16 Total: 77
Entrance 2: 17 Total: 81
Entrance 4: 17 Total: 85
Entrance 5: 17 Total: 84
Entrance 3: 17 Total: 83
Entrance 1: 17 Total: 82
Entrance 3: 18 Total: 86
Entrance 5: 18 Total: 90
Entrance 4: 18 Total: 89
Entrance 1: 18 Total: 88
Entrance 2: 18 Total: 87
Entrance 1: 19 Total: 91
Entrance 3: 19 Total: 95
Entrance 2: 19 Total: 94
Entrance 4: 19 Total: 93
Entrance 5: 19 Total: 92
Entrance 3: 20 Total: 96
Entrance 2: 20 Total: 100
Entrance 4: 20 Total: 99
Entrance 1: 20 Total: 98
Entrance 5: 20 Total: 97
Entrance 4: 21 Total: 101
Entrance 1: 21 Total: 102
Entrance 5: 21 Total: 104
Entrance 2: 21 Total: 103
Entrance 3: 21 Total: 105
Entrance 3: 22 Total: 106
Entrance 4: 22 Total: 110
Entrance 1: 22 Total: 109
Entrance 2: 22 Total: 108
Entrance 5: 22 Total: 107
Entrance 1: 23 Total: 111
Entrance 3: 23 Total: 115
Entrance 4: 23 Total: 114
Entrance 2: 23 Total: 113
Entrance 5: 23 Total: 112
Entrance 4: 24 Total: 116
Entrance 5: 24 Total: 120
Entrance 1: 24 Total: 119
Entrance 2: 24 Total: 118
Entrance 3: 24 Total: 117
Entrance 1: 25 Total: 121
Entrance 3: 25 Total: 125
Entrance 5: 25 Total: 124
Entrance 4: 25 Total: 123
Entrance 2: 25 Total: 122
Entrance 4: 26 Total: 126
Entrance 5: 26 Total: 130
Entrance 2: 26 Total: 129
Entrance 3: 26 Total: 128
Entrance 1: 26 Total: 127
Entrance 1: 27 Total: 131
Entrance 4: 27 Total: 135
Entrance 5: 27 Total: 134
Entrance 2: 27 Total: 133
Entrance 3: 27 Total: 132
Entrance 4: 28 Total: 136
Entrance 3: 28 Total: 140
Entrance 5: 28 Total: 139
Entrance 2: 28 Total: 138
Entrance 1: 28 Total: 137
Entrance 2: 29 Total: 141
Entrance 4: 29 Total: 143
Entrance 5: 29 Total: 142
Entrance 1: 29 Total: 145
Entrance 3: 29 Total: 144
Entrance 1: 30 Total: 146
Entrance 3: 30 Total: 150
Entrance 4: 30 Total: 149
Entrance 2: 30 Total: 148
Entrance 5: 30 Total: 147
Some tasks were not terminated!
Total: 150
Sum of Entrance 150
Stopping Entrance 4: 30
Stopping Entrance 3: 30
Stopping Entrance 5: 30
Stopping Entrance 1: 30
Stopping Entrance 2: 30
*/