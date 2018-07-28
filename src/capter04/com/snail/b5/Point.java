package capter04.com.snail.b5;

/**
 * 线程安全的点位
 */
public class Point {
    private final int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
