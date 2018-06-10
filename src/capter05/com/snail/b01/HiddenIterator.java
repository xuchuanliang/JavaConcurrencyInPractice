package capter05.com.snail.b01;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 调用toString(),hashCoed(),equals(),containsAll(),removeAll(),retainAll()方法都会隐含的调用迭代方法，导致线程安全问题，详情可看源码
 */
public class HiddenIterator {
    private final Set<Integer> set = new HashSet<>();
    public synchronized  void add(Integer i){
        set.add(i);
    }
    public synchronized void remove(Integer i){
        set.remove(i);
    }
    public void addTenThing(){
        Random random = new Random();
        for(int i=0; i<10; i++){
            add(random.nextInt());
        }
        System.out.println(set);
    }
}
