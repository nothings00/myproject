package design.pattern.behavioral.strategy;

/**
 * 具体策略类B
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-14 16:10
 * @version 1.0.0
 */
public class ConcreteStrategyB implements Strategy {
    @Override
    public void algorithm() {
        System.out.println("using strategy B");
    }
}
