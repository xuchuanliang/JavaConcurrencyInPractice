package second.capter01;

/**
 * @author xuchuanliangbt
 * @title: TestMain
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2414:22
 * @Version
 */
public class TestMain {
    public static void main(String[] args){
//        Thread thread = new MyThread();
//        thread.start();
//        test1();
        test2();
    }
    public static void test1(){
        for(int i=0;i<1000;i++){
            System.out.print("华为");
        }
    }

    public static void test2(){
        Bank bank = new Bank();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bank.deposit(10);
                        bank.withdraw(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bank.withdraw(10);
                        bank.deposit(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
