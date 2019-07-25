package ThreadPattenByPic.capter01;

/**
 * @author xuchuanliangbt
 * @title: Gate
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2414:52
 * @Version
 */
public class Gate {
    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";
    public synchronized void pass(String name,String address){
        this.counter++;
        this.name = name;
        this.address = address;
        check();
    }

    private void check(){
        if(name.charAt(0)!=address.charAt(0)){
            System.out.println("*****BROKEN******"+toString());
        }
    }

    @Override
    public String toString() {
        return "Gate{" +
                "counter=" + counter +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
