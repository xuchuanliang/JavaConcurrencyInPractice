package JavaCoreThreadPatten.capter02;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 线程启动和可见性：父线程在启动子线程之前对共享变量的更新对于子线程来说是可见的
 */
public class ThreadStartVisibility {
    static int data = 0;
    public static void main(String[] args){
        Thread thread = new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //读取data的值
            System.out.println(data);
        });
        //在子线程启动之前更改data的值
        data = 1;
        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //在子线程启动后修改data的值
        data = 2;
    }
}
