package capter06.com.snail.b1;

import java.util.Timer;
import java.util.TimerTask;

public class OutOfTime {
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(),1);
        Thread.sleep(1000);
        timer.schedule(new ThrowTask(),1);
        Thread.sleep(1000);
    }

}
class ThrowTask extends TimerTask{

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
