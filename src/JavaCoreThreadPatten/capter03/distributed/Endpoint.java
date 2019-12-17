package JavaCoreThreadPatten.capter03.distributed;

/**
 * 分布式服务器中标识下游端点的实体类
 */
public class Endpoint {
    private final String host;
    private final int port;
    private final int wright;
    private volatile boolean online = false;

    public Endpoint(String host, int port, int wright) {
        this.host = host;
        this.port = port;
        this.wright = wright;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getWright() {
        return wright;
    }
}
