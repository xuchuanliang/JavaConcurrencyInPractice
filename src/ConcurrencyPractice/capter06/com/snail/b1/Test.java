package ConcurrencyPractice.capter06.com.snail.b1;

import java.util.concurrent.*;

public class Test {
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(20);
    public static void main(String[] args){
//        thread1();
//        thread2();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0; i<10; i++){
            executorService.submit(()->{
                System.out.println("哈哈，这是线程："+Thread.currentThread().getName());
            });
        }

    }
//    public static void thread1(){
//        Callable<Void> callable = new Callable<Void>() {
//            @Override
//            public Void call() throws Exception {
//                for(int i=0;i<1000000000; i++){
//                    System.out.println("1thread"+i);
//                }
//                return null;
//            }
//        };
//        EXECUTOR.submit(callable);
//    }
//    public static void thread2(){
//        Callable<Void> callable = new Callable<Void>() {
//            @Override
//            public Void call() throws Exception {
//                for(int i=0;i<1000000000; i++){
//                    System.out.println("2thread"+i);
//                }
//                return null;
//            }
//        };
//        EXECUTOR.submit(callable);
//    }
}
