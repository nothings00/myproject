package design.pattern.structural.bridge;

/**
 * 抽象类
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-30 16:35
 * @version 1.0.0
 */
public abstract class AbstractShape {
    private ColorImplementor colorImplementor;
    public AbstractShape(ColorImplementor colorImplementor) {
        this.colorImplementor = colorImplementor;
    }
    void color(){
        colorImplementor.colorImpl();
    };
}
