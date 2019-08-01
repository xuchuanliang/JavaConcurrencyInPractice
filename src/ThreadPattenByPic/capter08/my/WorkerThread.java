package ThreadPattenByPic.capter08.my;

/**
 * @author xuchuanliangbt
 * @title: WorkerThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/3117:53
 * @Version
 */
public class WorkerThread extends Thread{

    private final Channel channel;

    public WorkerThread(String name,Channel channel){
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true){
            try {
                Request request = channel.takeRequest();
                request.execute();
            } catch (InterruptedException e) {
            }
        }
    }
}
