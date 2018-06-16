package capter06.com.snail.b1;

import java.util.concurrent.*;

public class Future {
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for(int i=0; i<1000000000; i++){
                    result+=i;
                }
                return result;
            }
        };
        FutureTask<Integer> futureTask = new FutureTask(callable);
        java.util.concurrent.Future<Integer> future = EXECUTOR_SERVICE.submit(callable);
        java.util.concurrent.Future future2 = EXECUTOR_SERVICE.submit(futureTask);
        System.out.println(future.get());
        System.out.println(future2.get());
        System.out.println(futureTask.get());
        while (true){
            if(EXECUTOR_SERVICE.isTerminated()){
                EXECUTOR_SERVICE.shutdown();
                break;
            }
        }
        System.out.println("11111111111111111");
    }
}
