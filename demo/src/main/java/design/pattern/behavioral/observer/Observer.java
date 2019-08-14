package design.pattern.behavioral.observer;

/**
 * 观察者
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-14 16:37
 * @version 1.0.0
 */
public interface Observer {
    /**
     * 状态更新
     * @param subject 目标类
     */
     void update(Subject subject);
}
