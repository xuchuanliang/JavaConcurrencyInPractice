package JavaCoreThreadPatten.capter01;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 使用多线程下载文件
 */
public class FileDownloaderApp {
    public static void main(String[] args){
        Thread thread = null;
        for(String url:args){
            thread = new Thread(new FileDownloader(url));
            thread.start();
        }
    }
    static class FileDownloader implements Runnable{

        private final String url;

        public FileDownloader(String url){
            this.url = url;
        }

        @Override
        public void run() {
            String fileBaseName = url.substring(url.lastIndexOf('/')+1);
            try {
                URL url = new URL(this.url);
                String localFileName = System.getProperty("java.io.tmpdir")+"/viscent-"+fileBaseName;
                download(url,localFileName);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        private void download(URL url,String dir){
            //将文件保存到指定地址中
        }
    }
}
