package JavaCoreThreadPatten.capter02;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 猜测执行示例代码：处理器乱序执行可能导致在多线程情况下结果与预期不一致
 * 多线程情况下：
 */
public class SpeculativeLoadExample {
    private boolean ready = false;
    private int[] data = new int[]{1,2,3,4,5,6,7,8};
    public void writer(){
        int[] newData = new int[]{1,2,3,4,5,6,7,8};
        for(int i=0;i<newData.length;i++){
            //此处包含读内存的操作
            newData[i] = newData[i]-1;
        }
        data = newData;
        ready = true;
    }
    public int reader(){
        int sum = 0;
        int[] snapshot;
        if(ready){
            snapshot = data;
            for(int i=0;i<snapshot.length;i++){
                sum += snapshot[i];
            }
        }
        return sum;
    }
}
