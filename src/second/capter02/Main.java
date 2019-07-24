package second.capter02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2415:48
 * @Version
 */
public class Main {
    public static void main(String[] args){
//        test1();
        test2();
    }

    public static void test1(){
        Person person = new Person("xcl","huaibei");
        new PrintPersonThread(person).start();
        new PrintPersonThread(person).start();
        new PrintPersonThread(person).start();
    }

    public static void test2(){
//        List<Integer> list = new ArrayList<>();
//        final List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        final List<Integer> list = new CopyOnWriteArrayList<>();
        new WriterThread(list).start();
        new ReadThread(list).start();
    }
}
