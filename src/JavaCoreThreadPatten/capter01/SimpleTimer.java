package JavaCoreThreadPatten.capter01;

/**
 * 实现简单的计时器
 */
public class SimpleTimer {
    private static int i = 60;
    public static void main(String[] args){
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
    private static void countDown(){
        i--;
    }
}
