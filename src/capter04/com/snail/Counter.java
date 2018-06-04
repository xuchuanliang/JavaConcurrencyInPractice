package capter04.com.snail;

public class Counter {
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized void setValue(long value) {
        this.value = value;
    }
}
