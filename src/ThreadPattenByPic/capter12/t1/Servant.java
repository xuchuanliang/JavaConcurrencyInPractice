package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: Servant
 * @projectName JavaConcurrencyInPractice
 * @description: 实际处理方法的类
 * @date 2019/8/1217:33
 * @Version
 */
public class Servant implements ActiveObject{
    @Override
    public Result<String> makeString(int count, char fillChar) {
        char[] chars = new char[count];
        for(int i=0;i<count;i++){
            chars[i] = fillChar;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        return new RealResult<>(String.valueOf(chars));
    }

    @Override
    public void displayString(String s) {
        System.out.println("display String :"+s);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
