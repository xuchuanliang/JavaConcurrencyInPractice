package capter11.com.snail.b2;

/**
 * 分段锁
 */
public class StripedMap {
    //同步策略：bockets[n]由locks[n%N_LOCKS]来保护
    private static final int N_LOCKS = 16;
    private final Node[] bockes;
    private final Object[] locks;
    public StripedMap(int numBuckets){
        bockes = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        for(int i=0;i<N_LOCKS;i++){
            locks[i]=new Object();
        }
    }

    private final int hash(Object key){
        return Math.abs(key.hashCode() % bockes.length);
    }

    public Object get(Object key){
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]){

        }
        return null;
    }

    public void clear(){
        for(int i=0;i<bockes.length;i++){
            synchronized (locks[i % N_LOCKS]){
                bockes[i] = null;
            }
        }
    }


    private class Node{}
}
