package capter06.com.snail.b1;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(20);
    public static void main(String[] args){
        thread1();
        thread2();
    }
    public static void thread1(){
        Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for(int i=0;i<1000000000; i++){
                    System.out.println("1thread"+i);
                }
                return null;
            }
        };
        EXECUTOR.submit(callable);
    }
    public static void thread2(){
        Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for(int i=0;i<1000000000; i++){
                    System.out.println("2thread"+i);
                }
                return null;
            }
        };
        EXECUTOR.submit(callable);
    }
}
