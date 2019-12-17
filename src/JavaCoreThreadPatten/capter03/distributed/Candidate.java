package JavaCoreThreadPatten.capter03.distributed;

import java.util.Iterator;
import java.util.Set;

/**
 * 存储下游端点
 */
public class Candidate implements Iterable<Endpoint> {
    /**
     * 下游端点列表
     */
    private final Set<Endpoint> endpoints;
    /**
     * 总权重
     */
    public final int totalWeight;

    public Candidate(Set<Endpoint> endpoints) {
        this.endpoints = endpoints;
        totalWeight = endpoints.stream().mapToInt(Endpoint::getWright).sum();
    }

    @Override
    public Iterator<Endpoint> iterator() {
        return null;
    }
}
