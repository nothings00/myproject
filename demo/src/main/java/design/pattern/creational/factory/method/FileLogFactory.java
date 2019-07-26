package design.pattern.creational.factory.method;

/**
 *
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-25 16:30
 * @version 1.0.0
 */
public class FileLogFactory implements LogFactory {
    @Override
    public Log getLog(String name) {
        return new FileLog(name);
    }
}
