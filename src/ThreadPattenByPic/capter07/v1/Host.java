package ThreadPattenByPic.capter07.v1;

/**
 * @author xuchuanliangbt
 * @title: Host
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/3018:11
 * @Version
 */
public class Host {
    private final Helper helper = new Helper();
    public void request(final int count,final char c){
        System.out.println("request ("+count+","+c+")BEGIN");
        new Thread(){
            @Override
            public void run() {
                helper.handle(count,c);
            }
        }.start();
        System.out.println("request ("+count+","+c+")END");
    }
}
