package capter04.com.snail.b5;

public class SafePoint {
    private int x,y;

    public SafePoint(int[] location){
        this.x = location[0];
        this.y = location[1];
    }

    public SafePoint(SafePoint s){
        this(s.get());
    }

    public synchronized int[] get(){
        return new int[]{x,y};
    }

    public synchronized void set(int x,int y){
        this.x = x;
        this.y = y;
    }
}
