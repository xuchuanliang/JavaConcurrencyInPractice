package JavaCoreThreadPatten.capter03;

import java.util.concurrent.atomic.AtomicLong;

public class Indicator {
    private volatile static Indicator INDICATOR = null;

    /**
     * 请求数量
     */
    private final AtomicLong requestCount = new AtomicLong(0);

    /**
     * 请求成功数量
     */
    private final AtomicLong successCount = new AtomicLong(0);

    /**
     * 请求失败数量
     */
    private final AtomicLong failureCount = new AtomicLong(0);

    private Indicator(){}

    public static Indicator getInstance(){
        if(INDICATOR == null){
            synchronized (Indicator.class){
                if(INDICATOR == null){
                    INDICATOR = new Indicator();
                }
            }
        }
        return INDICATOR;
    }

    public void newRequestReceived(){
        requestCount.incrementAndGet();
    }

    public void newRequestProcessed(){
        successCount.incrementAndGet();
    }

    public void requestProcessFiled(){
        failureCount.incrementAndGet();
    }

    public long getRequestCount(){
        return requestCount.get();
    }

    public long getRequestProcessed(){
        return successCount.get();
    }

    public long getProcessFiled(){
        return failureCount.get();
    }
}
