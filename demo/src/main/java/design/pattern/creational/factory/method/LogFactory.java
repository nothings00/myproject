package design.pattern.creational.factory.method;

/**
 *
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-25 14:58
 * @version 1.0.0
 */
public interface LogFactory {
    /**
     * 通过名称获取日志类型
     * @param name
     * @return
     */
    Log getLog(String name);
}
