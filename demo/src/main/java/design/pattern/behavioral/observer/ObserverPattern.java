package design.pattern.behavioral.observer;

/**
 * 观察者模式
 * 优点：
   1.观察者模式可以实现表示层和数据逻辑层的分离，并定义了稳定的消息更新传递机制，抽象了更新接口，使得可以有各种各样不同的表示层作为具体观察者角色。
   2.观察者模式在观察目标和观察者之间建立一个抽象的耦合。
   3.观察者模式支持广播通信。
   4.观察者模式符合“开闭原则”的要求。
 * 缺点：
  1.如果一个观察目标对象有很多直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间。
  2. 如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。
  3. 观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。
 * 适用情况：
  1.一个抽象模型有两个方面，其中一个方面依赖于另一个方面。将这些方面封装在独立的对象中使它们可以各自独立地改变和复用。
  2.一个对象的改变将导致其他一个或多个对象也发生改变，而不知道具体有多少对象将发生改变，可以降低对象之间的耦合度。
  3.一个对象必须通知其他对象，而并不知道这些对象是谁。
  4.需要在系统中创建一个触发链，A对象的行为将影响B对象，B对象的行为将影响C对象……，可以使用观察者模式创建一种链式触发机制。
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
