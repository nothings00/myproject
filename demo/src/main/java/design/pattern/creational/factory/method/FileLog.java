package design.pattern.creational.factory.method;

/**
 * 文件日志
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-25 16:31
 * @version 1.0.0
 */
public class FileLog implements Log {

    String name;

    @Override
    public void writeLog() {
        System.out.println("writeLog:"+this.getClass().getSimpleName()+"-"+name);
    }

    public FileLog(String name) {
        this.name=name;
    }
}
