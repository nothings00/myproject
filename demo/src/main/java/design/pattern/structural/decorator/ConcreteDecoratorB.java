package design.pattern.structural.decorator;

/**
 * 具体装饰类B
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-01 10:03
 * @version 1.0.0
 */
public class ConcreteDecoratorB extends Decorator{
    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
    }

    void addBehavior(){
        System.out.println("add behavior BBB");
    }
}
