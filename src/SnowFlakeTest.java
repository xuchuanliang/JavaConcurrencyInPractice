public class SnowFlakeTest {
    public static void main(String[] args){
        long nextId = SnowFlake.getInstance(1, 1).nextId();
        System.out.println(nextId);
    }
}
