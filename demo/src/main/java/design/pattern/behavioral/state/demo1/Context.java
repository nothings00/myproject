package design.pattern.behavioral.state.demo1;

/**
 * 环境类
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-10 17:08
 * @version 1.0.0
 */
public class Context {
    private State state;

    public Context() {
        if (null == this.state){
            state = new ConcreteStateFirst();
        }
    }

    public Context(State state) {
        this.state = state;
    }

    /**
     * 状态改变
     * @param state
     */
    void changeState(State state){
        this.state = state;
    }

    public void request(){
        state.handle();
        if (state instanceof  ConcreteStateFirst){
            changeState(new ConcreteStateSecond());
        }else if (state instanceof  ConcreteStateSecond){
            changeState(new ConcreteStateFirst());
        }
    }
}
