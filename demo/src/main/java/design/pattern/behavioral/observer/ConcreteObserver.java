package design.pattern.behavioral.observer;

/**
 * 具体监测类
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-14 16:49
 * @version 1.0.0
 */
public class ConcreteObserver implements Observer{
    @Override
    public void update(Subject subject) {
        System.out.println(this+",state:" + subject.getState());
    }
}
