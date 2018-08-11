package capter08.com.snail.b2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 自定义线程工厂
 */
public class MyThread extends Thread {

    public static final String DEFAULT_NAME = "myThread";
    private static volatile boolean debugLifecycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    public MyThread(Runnable runnable){
        this(runnable,DEFAULT_NAME);
    }

    public MyThread(Runnable runnable, String name){
        super(runnable,name+"-"+created.incrementAndGet());
    }

    @Override
    public void run(){
        //复制debug的值确保一致性
        boolean debug = debugLifecycle;
        if(debug) LOGGER.log(Level.FINE,"created:"+getName());
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if(debug) LOGGER.log(Level.FINE,"exist:"+getName());
        }
    }





}
