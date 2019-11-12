package JavaCoreThreadPatten.capter01;

public class JoinTest {
    private static int i = 60;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        Task t = new Task();
        t.start();
        System.out.println("main end");
    }
    static class Task extends Thread{
        @Override
        public void run() {
            while (true){
                if (i==0){
                    break;
                }else {
                    System.out.println("wait me :"+ i +" second");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDown();
            }
        }
        private void countDown(){
            i--;
        }
    }
}
