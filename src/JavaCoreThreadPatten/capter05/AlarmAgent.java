package JavaCoreThreadPatten.capter05;

import java.util.Random;

/**
 * 告警代理
 */
public class AlarmAgent {

    private static volatile AlarmAgent INSTANCE = null;
    //是否已经连接上服务器
    private volatile boolean connectToServer = false;

    private AlarmAgent(){}

    public static AlarmAgent getINSTANCE(){
        if(null==INSTANCE){
            synchronized (AlarmAgent.class){
                if(null==INSTANCE){
                    INSTANCE = new AlarmAgent();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化操作
     */
    public void init(){
        doConnectionToServer();
        Thread thread = new Thread(new HeartbeartThread());
        thread.setDaemon(true);
        thread.setName("心跳检测后台守护线程");
        thread.start();
    }

    private void doConnectionToServer() {
        //连接操作
        synchronized (this){
            //连接建立成功
            connectToServer = true;
            //唤醒所有在等待的线程
            notifyAll();
        }
    }

    public void sendAlarm(String message){
        synchronized (this){
            while (!connectToServer){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //发送消息
            doSendMessage(message);
        }
    }

    public void doSendMessage(String message){
        //发送消息逻辑
    }


    /**
     * 心跳检测线程
     */
    class HeartbeartThread implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                while (true){
                    if(checkConnection()){
                        connectToServer = true;
                    }else{
                        connectToServer = false;
                    }
                }
            } catch (InterruptedException e) {
            }
        }
        public boolean checkConnection(){
            //检测连接是否正常
            return new Random().nextBoolean();
        }
    }
}
