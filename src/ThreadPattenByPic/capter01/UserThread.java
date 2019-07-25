package ThreadPattenByPic.capter01;

/**
 * @author xuchuanliangbt
 * @title: UserThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2414:56
 * @Version
 */
public class UserThread extends Thread{
    private final Gate gate;
    private final String myName;
    private final String myAddress;
    public UserThread(Gate gate,String myName,String myAddress){
        this.gate = gate;
        this.myAddress = myAddress;
        this.myName = myName;
    }
    @Override
    public void run(){
        System.out.println(myName+" BEGIN");
        while (true){
            gate.pass(myName,myAddress);
        }
    }
}
