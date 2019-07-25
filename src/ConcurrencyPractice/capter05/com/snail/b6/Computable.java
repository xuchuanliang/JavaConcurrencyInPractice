package ConcurrencyPractice.capter05.com.snail.b6;

/**
 * 构建可伸缩性的缓存
 */
public interface Computable<A,V> {
    V compute(A arg) throws InterruptedException;
}
