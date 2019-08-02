package design.pattern.structural.decorator;

/**
 * 具体构件
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-01 9:47
 * @version 1.0.0
 */
public class ConcreteComponent implements Component{
    @Override
    public void operation() {
        System.out.println(this.getClass().getSimpleName()+"::operation()");
    }
}
