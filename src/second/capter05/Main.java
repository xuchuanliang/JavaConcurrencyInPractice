package second.capter05;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2515:55
 * @Version
 */
public class Main {
    public static void main(String[] args){
        Table table = new Table(3);
        new MakerThread("MakerThread-1",table,31415).start();
        new MakerThread("MakerThread-2",table,92653).start();
        new MakerThread("MakerThread-3",table,58979).start();
        new MakerThread("MakerThread-4",table,58979).start();
        new MakerThread("MakerThread-5",table,58979).start();
        new EaterThread("EaterThread-1",table,32384).start();
        new EaterThread("EaterThread-2",table,62643).start();
        new EaterThread("EaterThread-3",table,38327).start();
    }
}
