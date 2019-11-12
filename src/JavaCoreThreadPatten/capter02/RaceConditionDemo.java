package JavaCoreThreadPatten.capter02;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 模拟RequestIDGenerator在实际环境多线程使用
 */
public class RaceConditionDemo {
    public static void main(String[] args){
        //客户端线程数
        Thread[] threads = new Thread[Runtime.getRuntime().availableProcessors()*2];
        for(int i=0;i<threads.length;i++){
            threads[i] = new WorkThread(i,10);
        }
        /**
         * 启动所有线程
         */
        for(Thread t:threads){
            t.start();
        }
    }

    /**
     * 模拟业务线程
     */
    static class WorkThread extends Thread{
        private final int requestCount;

        public WorkThread(int id,int requestCount) {
            super("worker--"+id);
            this.requestCount = requestCount;
        }

        @Override
        public void run() {
            int i = requestCount;
            String requestID;
            RequestIdGenerator requestIdGenerator = RequestIdGenerator.getInstance();
            while (i-- > 0){
                //生成request ID
                requestID = requestIdGenerator.nextID();
                processRequest(requestID);
            }
        }
        //模拟请求处理
        private void processRequest(String requestId){
            //模拟请求处理耗时
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s get requestId:%s %n",Thread.currentThread().getName(),requestId);
        }
    }
}
