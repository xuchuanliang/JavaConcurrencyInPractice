package ConcurrencyPractice.capter03.code;

public class ThreadLoadHolder {
    private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();
    public void test(){
        integerThreadLocal.set(12);
        integerThreadLocal.get();
    }
}
