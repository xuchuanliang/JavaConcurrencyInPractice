package capter07.com.snail.b1;

public class InterTest {
    public static void main(String[] args){

        try {
            Thread thread = new Thread(new Inter());
            thread.start();
            Thread.sleep(10000);
            thread.interrupt();
            System.out.println("中断");
            Thread.sleep(5000);
            thread.interrupt();
            System.out.println("开始");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
class Inter implements Runnable{

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            System.out.print(1);
        }
    }
}