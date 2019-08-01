package ThreadPattenByPic.capter09;

/**
 * @author xuchuanliangbt
 * @title: Host
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/116:46
 * @Version
 */
public class Host {
    public Data request(final int count,final char c){
        System.out.println("    request("+count+","+c+") BEGIN");
        final FutureData futureData = new FutureData();
        new Thread(()->{
            Data data = new RealData(count,c);
            futureData.setRealData(data);
        }).start();
        System.out.println("    request("+count+","+c+") END");
        return futureData;
    }
}
