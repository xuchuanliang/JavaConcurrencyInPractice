package JavaCoreThreadPatten.capter07.v1;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看线程dump
 * Java stack information for the threads listed above:
 * ===================================================
 * "Thread-4":
 *         at JavaCoreThreadPatten.capter07.v1.Philosophers.eat(Philosophers.java:26)
 *         - waiting to lock <0x000000076f7de890> (a JavaCoreThreadPatten.capter07.v1.Chopstick)
 *         - locked <0x000000076f7de028> (a JavaCoreThreadPatten.capter07.v1.Chopstick)
 *         at JavaCoreThreadPatten.capter07.v1.Philosophers.run(Philosophers.java:59)
 * "Thread-0":
 *         at JavaCoreThreadPatten.capter07.v1.Philosophers.eat(Philosophers.java:26)
 *         - waiting to lock <0x000000076f7e1fa0> (a JavaCoreThreadPatten.capter07.v1.Chopstick)
 *         - locked <0x000000076f7de890> (a JavaCoreThreadPatten.capter07.v1.Chopstick)
 *         at JavaCoreThreadPatten.capter07.v1.Philosophers.run(Philosophers.java:59)
 * "Thread-1":
 *         at JavaCoreThreadPatten.capter07.v1.Philosophers.eat(Philosophers.java:26)
 *         - waiting to lock <0x000000076f7e2040> (a JavaCoreThreadPatten.capter07.v1.Chopstick)
 *         - locked <0x000000076f7e1fa0> (a JavaCoreThreadPatten.capter07.v1.Chopstick)
 *         at JavaCoreThreadPatten.capter07.v1.Philosophers.run(Philosophers.java:59)
 * "Thread-2":
 *         at JavaCoreThreadPatten.capter07.v1.Philosophers.eat(Philosophers.java:26)
 *         - waiting to lock <0x000000076f7de010> (a JavaCoreThreadPatten.capter07.v1.Chopstick)
 *         - locked <0x000000076f7e2040> (a JavaCoreThreadPatten.capter07.v1.Chopstick)
 *         at JavaCoreThreadPatten.capter07.v1.Philosophers.run(Philosophers.java:59)
 * "Thread-3":
 *         at JavaCoreThreadPatten.capter07.v1.Philosophers.eat(Philosophers.java:26)
 *         - waiting to lock <0x000000076f7de028> (a JavaCoreThreadPatten.capter07.v1.Chopstick)
 *         - locked <0x000000076f7de010> (a JavaCoreThreadPatten.capter07.v1.Chopstick)
 *         at JavaCoreThreadPatten.capter07.v1.Philosophers.run(Philosophers.java:59)
 *
 *         可以看出来其实已经形成了一个环形锁：产生了死锁
 */
public class Test {
    public static void main(String[] args) {
        //五个哲学家，五支筷子
        List<Chopstick> chopsticks = new ArrayList<>();
        for(int i=0;i<5;i++){
            chopsticks.add(new Chopstick(i));
        }
        List<Philosophers> philosophers = new ArrayList<>();
        for(int i=0;i<5;i++){
            if(i<4){
                philosophers.add(new Philosophers(i,chopsticks.get(i),chopsticks.get(i+1)));
            }else{
                philosophers.add(new Philosophers(i,chopsticks.get(i),chopsticks.get(0)));
            }
        }
        philosophers.forEach(p->{
            p.start();
        });
    }
}
