package design.pattern.behavioral.observer.instance;

public class InstanceTest {
    public static void main(String[] args) {
        ObserverImpl observer = new ObserverImpl();
        Subject subject = new Subject();
        subject.addObserver(observer);
        subject.setChanged();
        subject.notifyObservers("hi");
    }
}
