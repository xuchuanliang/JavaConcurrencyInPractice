package JavaCoreThreadPatten.capter07.v1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 哲学家：一会思考，一会吃饭，先拿起左手筷子再拿起右手筷子，最终再吃饭，
 */
public class Philosophers extends Thread implements Philosopher{
    private final int id;
    private final Chopstick left;
    private final Chopstick right;

    public Philosophers(int id, Chopstick left, Chopstick right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }


    @Override
    public void eat() {
        synchronized (left){
            left.pickUp();
            System.out.println("哲学家"+id+"拿起了左手边的筷子"+left.getId()+"，开始准备拿起右手边的筷子。。。"+right.getId());
            synchronized (right){
                right.pickUp();
                System.out.println("哲学家"+id+"拿起了右手边的筷子"+right.getId()+"，开始吃饭。。。");
                doEat();
                //放下筷子，思考
                right.putDown();
            }
            left.putDown();
        }
    }

    /**
     * 吃饭动作，模拟随机停顿5秒钟
     */
    private void doEat(){
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
        }
    }

    /**
     * 哲学家思考
     */
    @Override
    public void think(){
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void run() {
        this.eat();
        this.think();
    }
}
