package capter05.com.snail.b07;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
    }
    public static void test1() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(()->{
            Thread.sleep(10000);
            System.out.println("啦啦啦啦啦");
            return "我去";
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
