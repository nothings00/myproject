package design.pattern.behavioral.state.demo1;

/**
 * 状态B
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-13 10:20
 * @version 1.0.0
 */
public class ConcreteStateSecond implements State {
    @Override
    public void handle() {
        System.out.println("Second State ");
    }
}
