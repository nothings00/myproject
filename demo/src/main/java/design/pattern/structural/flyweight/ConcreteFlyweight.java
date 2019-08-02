package design.pattern.structural.flyweight;

/**
 * 具体享元类
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-01 11:26
 * @version 1.0.0
 */
public class ConcreteFlyweight implements Flyweight {

    private String intrinsicState;


    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation() {
        System.out.println("Flyweight ["+intrinsicState+"] do operation;");
    }
}
