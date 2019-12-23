package JavaCoreThreadPatten.capter08;

import java.io.File;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程池
 */
public class TaskResultRetrievalDemo {
    private static final int processNum = Runtime.getRuntime().availableProcessors();
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(0,
            processNum, 5, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(100),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException {
        TaskResultRetrievalDemo taskResultRetrievalDemo = new TaskResultRetrievalDemo();
        Future<String> future = taskResultRetrievalDemo.recognizeImage("hshhshs");
        TimeUnit.SECONDS.sleep(2);

        try {
            future.get(10,TimeUnit.SECONDS);
        } catch (ExecutionException e) {
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public Future<String> recognizeImage(final String imageFile) {
        return THREAD_POOL_EXECUTOR.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return doSomeService(new File(imageFile));
            }
        });
    }

    protected String doSomeService(File imageFile) {
        //模拟耗时操作
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
        }
        return "success";
    }
}
