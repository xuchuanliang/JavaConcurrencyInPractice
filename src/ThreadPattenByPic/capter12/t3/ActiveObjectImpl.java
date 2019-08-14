package ThreadPattenByPic.capter12.t3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xuchuanliangbt
 * @title: ActiveObjectImpl
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/148:20
 * @Version
 */
public class ActiveObjectImpl extends ActiveObject{

    private final ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    public Future<String> makeString(int count, char fillChar) {
        return service.submit(() -> {
            char[] chars = new char[count];
            for(int i=0;i<count;i++){
                chars[i] = fillChar;
                Thread.sleep(100);
            }
            return String.valueOf(chars);
        });
    }

    @Override
    public void displayString(String s) {
        service.submit(()->{
            System.out.println(s);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void shutdown() {
        service.shutdown();
    }
}
