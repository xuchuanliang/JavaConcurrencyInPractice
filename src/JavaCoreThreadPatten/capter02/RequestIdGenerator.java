package JavaCoreThreadPatten.capter02;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Request ID生成器：最后3位000-999循环递增生成
 */
public class RequestIdGenerator {
    private final static RequestIdGenerator INSTANCE = new RequestIdGenerator();
    private final static short SEQ_UPPER_LIMIT = 999;
    private short sequence = 1;

    private RequestIdGenerator(){
    }

    public short nextSequence(){
        if(sequence >=SEQ_UPPER_LIMIT){
            sequence = 0;
        }else {
            sequence++;
        }
        return sequence;
    }

    public String nextID(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String timestamp = simpleDateFormat.format(new Date());
        DecimalFormat decimalFormat = new DecimalFormat("000");
        //生成请求序列号
        short sequenceNo = nextSequence();
        return "0049"+timestamp+decimalFormat.format(sequenceNo);
    }

    public static RequestIdGenerator getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args){
        System.out.println(RequestIdGenerator.getInstance().nextID());
    }
}
