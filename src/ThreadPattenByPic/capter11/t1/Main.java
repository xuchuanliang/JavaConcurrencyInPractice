package ThreadPattenByPic.capter11.t1;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1211:55
 * @Version
 */
public class Main {
    public static void main(String[] args){
        System.out.println("BEGIN log");
        for(int i=0;i<10;i++){
            Log.log(i);
        }
        Log.logClose();
        System.out.println("END log");
    }
}
