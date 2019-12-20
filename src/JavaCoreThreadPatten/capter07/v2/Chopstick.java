package JavaCoreThreadPatten.capter07.v2;

/*
    筷子实体
 */
public class Chopstick {
    private final int id;
    private Status status = Status.PUT_DOWN;

    public Chopstick(int id) {
        this.id = id;
    }

    public void pickUp(){
        this.status = Status.PICKED_UP;
    }

    public void putDown(){
        this.status = Status.PUT_DOWN;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "id=" + id +
                '}';
    }

    private enum Status{
        PUT_DOWN,PICKED_UP;
    }
}
