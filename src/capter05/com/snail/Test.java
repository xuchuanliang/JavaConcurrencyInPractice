package capter05.com.snail;

import capter05.com.snail.b05.Preloader;
import capter05.com.snail.b05.TestHarness;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        testPreloader();
    }

    public static void testTime(){
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
    }

    public static void testHarness() throws InterruptedException {
        TestHarness testHarness = new TestHarness();
        System.out.println(testHarness.timeTasks(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("Test run");
            }
        }));
    }

    public static void testPreloader() throws InterruptedException {
        Preloader preloader = new Preloader();
        System.out.println(System.nanoTime());
        preloader.start();
        System.out.println(System.nanoTime());
        System.out.println(preloader.get());
        System.out.println(System.nanoTime());
    }
}
