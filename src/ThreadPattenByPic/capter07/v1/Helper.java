package ThreadPattenByPic.capter07.v1;

/**
 * @author xuchuanliangbt
 * @title: Handler
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/3018:11
 * @Version
 */
public class Helper {

    public void handle(int count,char c){
        System.out.println("handle ("+count+","+c+") begin");
        for(int i=0;i<count;i++){
            slowly();
            System.out.println(c);
        }
        System.out.println("");
        System.out.println("handle ("+count+","+c+") end");
    }

    private void slowly(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
