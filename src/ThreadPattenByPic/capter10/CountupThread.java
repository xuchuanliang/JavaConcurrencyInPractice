package ThreadPattenByPic.capter10;

/**
 * @author xuchuanliangbt
 * @title: CountupThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/119:16
 * @Version
 */
public class CountupThread extends Thread{
    private long counter = 0;
    private volatile boolean shutdownRequest = false;

    public void shutdownRequest(){
        shutdownRequest = true;
        interrupt();
    }

    @Override
    public void run() {
        try {
            while (!shutdownRequest){
                doWork();
            }
        }catch (Exception e){
        }finally {
            doShutdown();
        }
    }
    private void doWork() throws InterruptedException {
        counter++;
        System.out.println("doWork:counter="+counter);
        Thread.sleep(500);
    }

    private void doShutdown(){
        System.out.println("doShutdown:counter="+counter);
    }
}
