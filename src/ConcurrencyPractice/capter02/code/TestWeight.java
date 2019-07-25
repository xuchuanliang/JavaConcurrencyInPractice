package ConcurrencyPractice.capter02.code;

/**
 * 主要为了测试，子类覆盖父类方法后，调用子类方法时，也获取父类的锁
 */
public class TestWeight {
    public static void main(String[] args) throws InterruptedException {

        final LoggingWidget widget = new LoggingWidget();
        Thread th1 = new Thread("th1") {
            @Override
            public void run() {
                System.out.println(super.getName() + ":start\r\n");
                widget.doSometing();
            }
        };

        Thread th2 = new Thread("th2") {
            @Override
            public void run() {
                System.out.println(super.getName() + ":start\r\n");
                /** 为了说明子类复写父类方法后，调用时也持有父类锁*/
                widget.doAnother();
                /**证明了内置锁对那些没有加synchronized修饰符的方法是不起作用的*/
                widget.doNother();
                /**为了说明子类复写父类方法后，调用时也持有父类锁，也持有自己本类的锁*/
                 widget.doMyLike();
                /**这是两个线程，这是需要等待的，并不是继承的关系，不是重入，重入是发生在一个线程中的*/
                widget.doSometing();
            }
        };

        th1.start();
        Thread.sleep(1000);
        th2.start();
    }

}

class Widget {
    public synchronized void doSometing() {
        System.out.println("widget ... do something...");
    }

    public synchronized void doAnother() {
        System.out.println("widget... do another thing...");
    }

    public void doNother() {
        System.out.println("widget... do Nothing...");
    }
}

class LoggingWidget extends Widget {

    @Override
    public synchronized void doSometing() {
        try {
            System.out.println("loggingwidget do something...");
            Thread.sleep(5000);
            System.out.println("end loggingwidget do something...");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.doSometing();
    }

    public synchronized void doMyLike() {
        System.out.println("loggingwidget do my like...");
    }
}
