package capter05.com.snail.b05;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask异步执行，且获取结果
 */
public class Preloader {
    private final FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            StringBuffer stringBuffer = new StringBuffer();
            for(int i=0; i<10000000; i++){
                stringBuffer.append(i);
            }
            return stringBuffer.toString();
        }
    });

    private final Thread thread = new Thread(futureTask);

    public void start(){thread.start();}

    public String get() throws InterruptedException {
        try {
            return futureTask.get();
        } catch (InterruptedException e) {
            throw e;
        } catch (ExecutionException e) {
            Throwable throwable = e.getCause();
            if(throwable instanceof RuntimeException){
                throw new RuntimeException(e);
            }else{
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
