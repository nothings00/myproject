package design.pattern.behavioral.state.demo1;

/**
 * 抽象状态类
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-13 10:03
 * @version 1.0.0
 */
public interface State {
    /**
     * 状态修改触发事件
     */
    void handle();
}
