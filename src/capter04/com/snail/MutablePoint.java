package capter04.com.snail;

/**
 * 线程安全的内置锁实例
 */
public class MutablePoint {
    public int x,y;

    public MutablePoint(){
        x = 0;
        y = 0;
    }
    public MutablePoint(MutablePoint p){
        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public String toString() {
        return "MutablePoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
