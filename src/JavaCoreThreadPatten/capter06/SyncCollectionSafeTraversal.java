package JavaCoreThreadPatten.capter06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SyncCollectionSafeTraversal {
    final List<String> syncList = Collections.synchronizedList(new ArrayList<>());
    public void test(){
        Iterator<String> iterator = syncList.iterator();
        //需要对该对象进行加锁，因为返回的Iterator是非线程安全的，降低了并发性能
        synchronized (syncList){
            while (iterator.hasNext()){
                System.err.println(iterator.next());
            }
        }
    }
}
