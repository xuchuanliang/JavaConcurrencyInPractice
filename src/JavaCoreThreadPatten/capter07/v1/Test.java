package JavaCoreThreadPatten.capter07.v1;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        //五个哲学家，五支筷子
        List<Chopstick> chopsticks = new ArrayList<>();
        for(int i=0;i<5;i++){
            chopsticks.add(new Chopstick(i));
        }
        List<Philosopher> philosophers = new ArrayList<>();
        for(int i=0;i<5;i++){
            if(i<4){
                philosophers.add(new Philosophers(i,chopsticks.get(i),chopsticks.get(i+1)))
            }else{
                philosophers.add(new Philosophers(i,chopsticks.get(i),chopsticks.get(0)));
            }
        }
        for(;;){

        }
    }
}
