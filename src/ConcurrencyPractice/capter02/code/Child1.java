package ConcurrencyPractice.capter02.code;

/**
 * 锁的可重入性
 */
public class Child1 extends Father{

    public static void main(String[] args){
        new Child1().doSomething();
    }

    @Override
    public synchronized void doSomething(){
        System.out.println("child");
        super.doSomething();
    }
}
class Father{
    public synchronized void doSomething(){
        System.out.println("father");
    }
}
