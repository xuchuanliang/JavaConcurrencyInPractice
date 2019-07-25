package ConcurrencyPractice.capter10.com.snail.b1;

/**
 * 会发生死锁，通过制定锁顺序，并在整个应用程序中按照这个顺序来获取锁，解决死锁问题
 */
public class TransferMoney {

    /**
     *
     * @param fromAccount  从哪个账户转
     * @param toAccount 转至哪个账户
     * @param amount 转账额度
     */
    public void transferMoney(Account fromAccount,Account toAccount,int amount){
        synchronized(fromAccount){
            synchronized (toAccount){
                fromAccount.setBanlance(fromAccount.getBanlance()-amount);
                toAccount.setBanlance(toAccount.getBanlance()+amount);
            }
        }
    }

    /**
     * 本方法主要使用hashCode作为比较，首先对hashCode小的对象进行加锁，若两个账户的hashCode相同，那么则使用tieLock（加时赛锁）来
     */
    private static final Object tieLock = new Object();

    public void transferMoney2(Account fromAccount,Account toAccount,int amount){
        //System.identityHashCode(Object) 是根据内存地址生成的hashCode
        int fromHash = System.identityHashCode(fromAccount);
        int toHash = System.identityHashCode(toAccount);
        if(fromHash<toHash){
            synchronized (fromAccount){
                synchronized (toAccount){
                    transferMoney(fromAccount,toAccount,amount);
                }
            }
        }else if(fromHash>toHash){
            synchronized (toAccount){
                synchronized (fromAccount){
                    transferMoney(fromAccount,toAccount,amount);
                }
            }
        }else{
            //两个内存hash相同，概率极低
            synchronized (tieLock){
                synchronized (fromAccount){
                    synchronized (toAccount){
                        transferMoney(fromAccount,toAccount,amount);
                    }
                }
            }
        }
    }
}
