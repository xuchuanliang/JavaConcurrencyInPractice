package ConcurrencyPractice.capter11.com.snail.b2;

import java.util.HashSet;
import java.util.Set;

/**
 * 该demo多个方法共用同一个对象锁，保护了不同的状态变量，导致对锁的竞争程度较大
 * 保护两个不同的状态变量 users和queries 此处使用同一个对象锁，会提高锁的竞争性，建议降低锁的粒度
 */
public class ServerStatus {

    private final Set<String> users = new HashSet<>();
    private final Set<String> queries = new HashSet<>();

    public synchronized void addUser(String u){users.add(u);}
    public synchronized void removeUser(String u){users.remove(u);}

    public synchronized void addQueries(String q){queries.add(q);}
    public synchronized void removeQueries(String q){queries.remove(q);}
}
