package ConcurrencyPractice.capter03.code;

import ConcurrencyPractice.capter03.anno.NotThreadSafe;

@NotThreadSafe
public class MutableInteger {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
