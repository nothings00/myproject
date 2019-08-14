package design.pattern.behavioral.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * 具体目标
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-14 16:47
 * @version 1.0.0
 */
public class ConcreteSubject implements Subject{

    private List<Observer> observers = new LinkedList<>();
    private int state;

    /**
     * 关联
     * @param observer 观察者
     */
    @Override
    public void attach(Observer observer){
        observers.add(observer);

    }

    /**
     * 解除关联
     * @param observer 观察者
     */
    @Override
    public void detach(Observer observer){
        observers.remove(observer);
    }

    /**
     * 提醒观察者
     */
    @Override
    public void notifyObservers(){
        for (Observer observer : observers){
            observer.update(this);
        }
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }
}
