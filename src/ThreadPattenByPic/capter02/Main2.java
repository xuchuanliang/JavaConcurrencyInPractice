package ThreadPattenByPic.capter02;

/**
 * @author xuchuanliangbt
 * @title: Main2
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2417:21
 * @Version
 */
public class Main2 {
    private static final long CALL_COUNT = 100000000L;

    /**加锁与不加锁的性能
     * 十万级别都是1704618/2679805=1.57倍
     * 百万级别都是3637245/20098372=5.52倍
     * 千万级别都是6060027/174974144=28.87倍
     * 亿级别都是29730791/1741935495=58.59倍
     * @param args
     */
    public static void main(String[] args){
        trial("NotSynch",CALL_COUNT,new NotSynch());
        trial("Synch",CALL_COUNT,new Synch());
    }

    private static void trial(String msg,long count,Object obj){
        System.out.println(msg + ": BEGIN ");
        long start = System.nanoTime();
        for(int i=0;i<count;i++){
            obj.toString();
        }
        System.out.println(msg + "END");
        System.out.println("Elapsed time = "+(System.nanoTime()-start)+" msec.");
    }
}
class NotSynch{
    private final String name = "NotSynch";
    @Override
    public String toString(){
        return "["+name+"]";
    }
}
class Synch{
    private final String name = "Synch";
    @Override
    public synchronized String toString(){
        return "["+name+"]";
    }
}
