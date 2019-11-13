package JavaCoreThreadPatten;

import java.util.concurrent.TimeUnit;

public class VisibilityDemo {
    public static void main(String[] args){
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();
        Thread thread = new Thread(timeConsumingTask);
        thread.start();
//        try {
//            TimeUnit.SECONDS.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        timeConsumingTask.cancel();
    }
}
class TimeConsumingTask implements Runnable{

    private boolean toCancel = false;

    @Override
    public void run() {
        while (!toCancel){
            System.out.println(1);
            if(doExecute()){
                break;
            }
        }
        if(toCancel){
            System.out.println("Task was canceled..");
        }else {
            System.out.println("Task done");
        }
    }

    private boolean doExecute(){
        System.out.println("executing...");
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void cancel(){
        toCancel = true;
        System.out.println(this + "cencel() method");
    }
}