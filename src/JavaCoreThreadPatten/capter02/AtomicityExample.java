package JavaCoreThreadPatten.capter02;

public class AtomicityExample {

    private ServerInfo serverInfo;
    public void updateHostInfo(String ip,String port){
        synchronized (serverInfo){
            serverInfo.setIp(ip);
            serverInfo.setPort(port);
        }
    }

    public void connectionToHost(){
        connect(serverInfo.getIp(),serverInfo.getPort());
    }

    private void connect(String ip,String port){

    }

    public static class ServerInfo{
        private String ip;
        private String port;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }
    }
}
