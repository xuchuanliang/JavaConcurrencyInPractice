package second.capter02;

/**
 * @author xuchuanliangbt
 * @title: PrintPersonThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2415:47
 * @Version
 */
public class PrintPersonThread extends Thread{
    private Person person;

    public PrintPersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+" prints "+person);
        }
    }
}
