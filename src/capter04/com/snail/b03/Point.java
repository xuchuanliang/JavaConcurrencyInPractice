package capter04.com.snail.b03;

import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public class Point {

    private final int x,y;

    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
