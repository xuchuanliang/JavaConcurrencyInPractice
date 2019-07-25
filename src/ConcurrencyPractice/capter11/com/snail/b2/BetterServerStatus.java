package ConcurrencyPractice.capter11.com.snail.b2;

import java.util.HashSet;
import java.util.Set;

/**
 * 锁分解
 * 降低锁的粒度，提高可伸缩性，降低锁的可竞争性
 * 将一个锁分为两个锁
 */
public class BetterServerStatus {

    public static final Set<String> users = new HashSet<>();
    public static final Set<String> queries = new HashSet<>();

    public void addUser(String u){
        synchronized (users){
            users.add(u);
        }
    }

    public void removeUser(String u){
        synchronized (users){
            users.remove(u);
        }
    }

    public void addQueries(String q){
        synchronized (queries){
            queries.add(q);
        }
    }

    public void removeQueries(String q){
        synchronized (queries){
            queries.remove(q);
        }
    }
}
