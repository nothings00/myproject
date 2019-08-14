package design.pattern.behavioral.strategy;

/**
 * 环境类
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-14 16:05
 * @version 1.0.0
 */
public class Context {
    private Strategy strategy ;

    /**
     * 改变策略
     * @param strategy
     */
    void setStrategy(Strategy strategy){
        this.strategy=strategy;
    }

    /**
     * 执行策略
     */
    void algorithm(){
        strategy.algorithm();
    }
}
