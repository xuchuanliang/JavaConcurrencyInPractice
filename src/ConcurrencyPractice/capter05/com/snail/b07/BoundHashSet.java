package ConcurrencyPractice.capter05.com.snail.b07;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundHashSet(Set<T> set, int capacity) {
        this.set = set;
        this.semaphore = new Semaphore(capacity);
    }
    public void add(T t) throws InterruptedException {
        semaphore.acquire();
        boolean success = set.add(t);
        if(!success){
            semaphore.release();
        }
    }
    public Set<T> get(){
        return Collections.unmodifiableSet(set);
    }
    public void remove(T t){
        boolean success = set.remove(t);
        if(success){
            semaphore.release();
        }
    }
}
