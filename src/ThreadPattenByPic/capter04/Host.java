package ThreadPattenByPic.capter04;

import java.util.concurrent.TimeoutException;

/**
 * @author xuchuanliangbt
 * @title: Host
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2515:09
 * @Version
 */
public class Host {
    private final long timeout;
    private boolean ready = false;

    public Host(long timeout) {
        this.timeout = timeout;
    }

    public synchronized void setExecutable(boolean on){
        ready = on;
        notifyAll();
    }

    public synchronized void execute() throws TimeoutException, InterruptedException {
        long start = System.currentTimeMillis();
        while (!ready){
            long now = System.currentTimeMillis();
            long rset = timeout-(now-start);
            if(rset<=0){
                throw new TimeoutException("hahahha");
            }
            wait(rset);
        }
        doExecute();
    }

    private void doExecute() {
        System.out.println(Thread.currentThread().getName()+" calls doExecute");
    }
}
