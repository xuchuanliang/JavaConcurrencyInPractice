package ConcurrencyPractice.capter07.com.snail.b2;

import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Callable<String> callable1 = ()->{
            for(int i=0; i<10; i++){
                System.out.println("thread22");
            }
            return "haha";
        };
        Callable<String> callable2 = ()->{
            for(int i=0; i<10; i++){
                System.out.println("thread333");
            }
            return "haha";
        };
        try {
            throw new RuntimeException();
        }catch (Exception e){
            System.out.println("catch exception");
        }
        for(int i=0; i<1000; i++){
            System.out.println("thread1");
        }
        Future<String> future1 = executorService.submit(callable1);
        Future<String> future2 = executorService.submit(callable2);
        System.out.println("1111111111111111");
    }
}
