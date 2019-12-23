package JavaCoreThreadPatten.capter08;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 通过自定义线程的Thread.UncaughtExceptionHandler，当线程发生异常退出后运行业务逻辑，保障我们的业务流程
 */
public class ThreadMonitorDemo {
    volatile boolean inited = false;
    private static int threadIndex = 0;
    final BlockingQueue<String> channel = new ArrayBlockingQueue<String>(1000);

    public static void main(String[] args) throws InterruptedException {
        ThreadMonitorDemo threadMonitorDemo = new ThreadMonitorDemo();
        threadMonitorDemo.init();
        for(int i=0;i<1000;i++){
            threadMonitorDemo.service("test:"+i);
        }
    }

    public synchronized void init(){
        if(inited){
            return;
        }
        WorkThread workThread = new WorkThread();
        workThread.setName("workThread："+threadIndex++);
        workThread.setUncaughtExceptionHandler(new ThreadMonitor());
        workThread.start();
        inited=true;
    }

    public void service(String message){
        try {
            channel.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测到线程异常结束，则进行后续处理
     */
    private class ThreadMonitor implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            String threadInfo = t.getName();
            System.err.println("拦截到线程："+threadInfo+"抛出异常");
            //重新执行
            System.err.println("不能影响，继续运行");
            WorkThread workThread = new WorkThread();
            workThread.setName("workThread："+threadIndex++);
            workThread.setUncaughtExceptionHandler(new ThreadMonitor());
            workThread.start();
        }
    }

    class WorkThread extends Thread{
        @Override
        public void run() {
            for(;;){
                try {
                    String msg = channel.take();
                    process(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void process(String message){
            double s = Math.random()*100;
            System.out.println(s);
            if(s<15){
                throw new RuntimeException("test");
            }
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
