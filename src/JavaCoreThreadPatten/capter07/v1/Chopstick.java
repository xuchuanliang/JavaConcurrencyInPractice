package JavaCoreThreadPatten.capter07.v1;

/**
 * 模拟多线程：哲学家吃饭问题
 * 筷子类
 */
public class Chopstick {
    public final int id;
    public Status status = Status.DOWN;

    public Chopstick(int id){
        this.id = id;
    }

    /**
     * 拿起筷子
     */
    public void pickUp(){
        this.status = Status.UP;
    }

    /**
     * 放下筷子
     */
    public void putDown(){
        this.status = Status.DOWN;
    }

    public int getId() {
        return id;
    }

    public enum Status{
        UP,DOWN;
    }
}
