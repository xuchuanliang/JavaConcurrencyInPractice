package ThreadPattenByPic.capter10;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/119:16
 * @Version
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        try {
            System.out.println("main:BEGIN");
            CountupThread countupThread = new CountupThread();
            countupThread.start();
//            Thread.sleep(5000);
            System.out.println("main:shutdownRequest");
            countupThread.shutdownRequest();
            System.out.println("main:join");
            countupThread.join();
        } catch (InterruptedException e) {
        }
        System.out.println("main:End");
//        Thread thread = new Te();
//        thread.start();
//        Thread.sleep(2000);
//        thread.interrupt();
    }

}

class Te extends Thread {
    @Override
    public void run() {
        try{
            for (int i = 0; i < 50000; i++) {
                if (super.isInterrupted()) {
                    System.out.println("bababa");
                }
                Thread.sleep(100);
                System.out.println(i);
            }
        }catch (InterruptedException i){
            i.printStackTrace();
        }
        System.out.println("last");
    }
}
