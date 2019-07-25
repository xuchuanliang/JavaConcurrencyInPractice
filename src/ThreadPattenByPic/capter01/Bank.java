package ThreadPattenByPic.capter01;

/**
 * @author xuchuanliangbt
 * @title: Bank
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2414:29
 * @Version
 */
public class Bank {
    private int money;
    private String name;

    /**
     * 存款
     * @param m
     */
    public synchronized void deposit(int m) throws InterruptedException {
        System.out.print("deposit-start");
        Thread.sleep(1000);
        System.out.print("deposit-end");
        System.out.println();
        money += m;
    }

    /**
     * 取款
     * @param m
     * @return
     */
    public synchronized boolean withdraw(int m) throws InterruptedException {
        System.out.print("withdraw-start");
        Thread.sleep(1000);
        System.out.print("withdraw-end");
        System.out.println();
        if(money>m){
            money -=m;
            return true;
        }else{
            return false;
        }
    }

    public String getName(){
        return name;
    }
}
