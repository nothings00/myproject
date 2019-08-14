package design.pattern.behavioral.observer;

/**
 * 观察者模式
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-14 16:36
 * @version 1.0.0
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Observer observer1 = new ConcreteObserver();
        Observer observer2 = new ConcreteObserver();
        Subject subject = new ConcreteSubject();
        subject.attach(observer1);
        subject.attach(observer2);
        subject.setState(1);
        subject.notifyObservers();
        System.out.println(String.format("%80s","-").replaceAll("\\s","-"));
        subject.detach(observer1);
        subject.setState(2);
        subject.notifyObservers();
    }
}
