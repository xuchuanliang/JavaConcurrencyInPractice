package ThreadPattenByPic.capter09;

/**
 * @author xuchuanliangbt
 * @title: RealData
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/116:46
 * @Version
 */
public class RealData implements Data{

    private final String data;

    public RealData(int count,char c) {
        System.out.println("    making RealData("+count+","+c+") BEGIN");
        char[] buffer = new char[count];
        for(int i=0;i<count;i++){
            buffer[i] = c;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("    making RealData("+count+","+c+") END");
        this.data = new String(buffer);
    }

    @Override
    public String getContent() {
        return data;
    }
}
