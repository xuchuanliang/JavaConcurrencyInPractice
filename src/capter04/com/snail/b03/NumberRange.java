package capter04.com.snail.b03;

import capter03.anno.NotThreadSafe;

import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.concurrent.atomic.AtomicInteger;

@NotThreadSafe
public class NumberRange {
    //不变性条件：lower<=upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i){
        //注意：不安全的先检查后操作，需要加锁
        if(i>upper.get()){
            throw new IllegalArgumentException("can not set lower to "+i + " >upper");
        }
        lower.set(i);
    }

    public void setUpper(int i){
        //注意：不安全的先检查后操作，需要加锁
        if(i<lower.get()){
            throw new IllegalArgumentException("can not set upper to "+i+" <lower");
        }
        upper.set(i);
    }
}
