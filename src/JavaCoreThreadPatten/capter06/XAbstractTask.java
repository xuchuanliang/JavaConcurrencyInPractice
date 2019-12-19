package JavaCoreThreadPatten.capter06;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal中的数据在使用前需要及时清理，因为一个线程可能有多个任务
 */
public abstract class XAbstractTask implements Runnable{

    protected static final ThreadLocal<Map<String,String>> THREAD_LOCAL = new ThreadLocal<Map<String,String>>(){
        @Override
        protected Map<String, String> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public void run() {

    }
}
