package JavaCoreThreadPatten.capter01;

import java.util.Random;

public class ThreadCreationCmp {

    /**
     * 执行结果：
         CounterThread :10
         CounterThread :10
         CounterThread :10
         CounterThread :10
         CounterThread :10
         CounterThread :10
         CounterThread :10
         CounterThread :10
         CounterThread :10
         CounterThread :10
         CounterThread :10
         CounterThread :10
         CounterTask :1060
         CounterTask :1124
         CounterTask :1138
         CounterTask :1149
         CounterTask :1165
         CounterTask :1166
         CounterTask :1174
         CounterTask :1178
         CounterTask :1189
         CounterTask :1189
         CounterTask :1189
         CounterTask :1195
     * @param args
     */
    public static void main(String[] args){
        Thread t;
        CountingTask countingTask = new CountingTask();
        //获取处理器个数
        final int numberOfProceesors = Runtime.getRuntime().availableProcessors();

        for(int i=0;i<2*numberOfProceesors;i++){
            //直接创建一个线程，共用一个runnable实例，以runnable的形式启动线程
            t = new Thread(countingTask);
            t.start();
        }

        for(int i=0;i<2*numberOfProceesors;i++){
            //以子类的形式直接启动线程
            t = new CounterThread();
            t.start();
        }
    }

    static class Counter{

        private int count = 0;

        public void increace(){
            count++;
        }

        public int getCount(){
            return count;
        }
    }

    static class CountingTask implements Runnable{

        Counter counter = new Counter();

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                counter.increace();
                doSomething();
            }
            System.out.println("CounterTask :"+counter.getCount());
        }

        private void doSomething(){
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class CounterThread extends Thread{
        Counter counter = new Counter();
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                doSomething();
                counter.increace();
            }
            System.out.println("CounterThread :"+counter.getCount());
        }
        private void doSomething(){
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


