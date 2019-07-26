package design.pattern.creational.factory.method;

/**
 * 数据库日志
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-25 15:01
 * @version 1.0.0
 */
public class DataBaseLog implements Log{
    @Override
    public void writeLog() {
        System.out.println("writeLog:"+this.getClass().getSimpleName());
    }
}
