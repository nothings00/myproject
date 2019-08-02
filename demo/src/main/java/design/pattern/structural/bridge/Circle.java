package design.pattern.structural.bridge;

/**
 * 圆形
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-30 16:46
 * @version 1.0.0
 */
public class Circle extends AbstractShape {
    public Circle(ColorImplementor colorImplementor) {
        super(colorImplementor);
    }

    @Override
    void color() {
        System.out.print("i'm circle,my color:");
        super.color();
    }
}
