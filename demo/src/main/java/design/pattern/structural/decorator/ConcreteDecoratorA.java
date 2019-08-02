package design.pattern.structural.decorator;

/**
 * 具体装饰类A
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-01 9:57
 * @version 1.0.0
 */
public class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addBehavior();
    }

    void addBehavior(){
        System.out.println("addBehaviorAAA");
    }
}
