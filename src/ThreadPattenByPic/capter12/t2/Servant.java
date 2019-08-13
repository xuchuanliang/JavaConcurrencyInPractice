package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: Servant
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1316:12
 * @Version
 */
public class Servant implements ActiveObject{

    @Override
    public Result<String> makeString(int count, char c) {
        char[] chars = new char[count];
        for(int i=0;i<count;i++){
            chars[i] = c;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        return new RealResult<>(String.valueOf(chars));
    }

    @Override
    public void displayString(String s) {
        System.out.println(s);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
