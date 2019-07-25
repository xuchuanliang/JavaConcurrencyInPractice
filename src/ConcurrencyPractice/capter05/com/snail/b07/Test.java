package ConcurrencyPractice.capter05.com.snail.b07;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {

    static final BlockingQueue<String> testQueue = new ArrayBlockingQueue<>(100);

    public static void main(String[] args){
        test2();
    }
    public static void test1(){
        Collections.synchronizedList(new ArrayList<>());
        Collections.synchronizedMap(new HashMap<>());
        Collections.synchronizedSet(new HashSet<>());
        Collections.synchronizedCollection(new ArrayList<>());
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.addIfAbsent("haha");
    }

    public static void test2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<1000; i++){
                    try {
                        testQueue.put("hahah"+i);
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(()-> {
            try {
                for(int i=0; i<1000;i++){
                    String t = testQueue.take();
                    System.out.println(t);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();



    }
}
