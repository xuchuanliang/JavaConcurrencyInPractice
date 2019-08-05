package ThreadPattenByPic.capter10;

/**
 * @author xuchuanliangbt
 * @title: Main2
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/58:14
 * @Version
 */
public class Main2 {
    public static void main(String[] args){
        System.out.println("main:BEGIN");
        /**
         * 设置未捕获的异常的处理器
         * 正常情况下，假设程序抛出异常时，我们并没有编写捕获该异常的catch语句块，通常情况下，这么做会导致
         * 程序在输出线程的调用堆栈信息后终止
         * 如果使用Thread类的setDefaultUncaughtExceptionHandler静态方法，可以设置未捕获的异常的处理器。
         * 该处理器是Thread.UncaughtExceptionHandler接口类型的喜爱对象，实际的处理编写在uncaughtException方法中。
         * 设置未捕获的异常的处理器后，程序将不会输出调用堆栈而是直接终止。
         *
         */
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("****");
                System.out.println("UncaughtExceptionHandler:BEGIN");
                System.out.println("Thread:"+t);
                System.out.println("exception:"+e);
                System.out.println("uncaughtExceptionHandler:END");
            }
        });
        /**
         * 设置退出钩子
         * 退出钩子是指在java虚拟机退出时启动的线程。java虚拟机退出时指的是System.exit()被调用或是全部非守护线程终止时
         */
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("*****");
                System.out.println("shutdown hook:BEGIN");
                System.out.println("currentThread = "+Thread.currentThread());
                System.out.println("shutdown hool:END");
            }
        });
        new Thread("myThread"){
            @Override
            public void run() {
                System.out.println("MyThread:BEGIN");
                System.out.println("MyThread:SLEEP...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("MyThread:DIVIDE");
                int x = 1/0;
                System.out.println("MyThread:END");
            }
        }.start();
        System.out.println("main:END");
    }
}
