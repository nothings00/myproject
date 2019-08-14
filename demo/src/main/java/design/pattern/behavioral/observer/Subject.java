package design.pattern.behavioral.observer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 目标
 *
 * @author zenghh, 625111833@qq.com
 * @version 1.0.0
 * @date 2019-08-14 16:37
 */
public interface Subject {

    /**
     * 关联
     *
     * @param observer 观察者
     */
    void attach(Observer observer);

    /**
     * 解除关联
     *
     * @param observer 观察者
     */
    void detach(Observer observer);

    /**
     * 提醒观察者
     */
    void notifyObservers();

    /**
     * 获取状态
     *
     * @return
     */
    int getState();

    /**
     * 设置状态
     *
     * @param state
     */
    void setState(int state);
}
