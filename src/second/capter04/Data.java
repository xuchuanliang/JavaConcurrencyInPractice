package second.capter04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author xuchuanliangbt
 * @title: Data
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2514:43
 * @Version
 */
public class Data {
    private final String fileName;
    private String content;
    private boolean changed;

    public Data(String fileName,String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    /**
     * 修改数据内容
     * @param content
     */
    public synchronized void change(String content){
        this.content = content;
        this.changed = true;
    }

    /**
     * 将修改的内容保存到文件
     * @throws IOException
     */
    public synchronized void save() throws IOException {
        if(!changed){
            return;
        }
        doSave();
        changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName()+" call doSave ,content = "+content);
        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        Writer writer = new FileWriter(new File(fileName));
        writer.append(content);
        writer.flush();
        writer.close();
    }
}
