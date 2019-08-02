package design.pattern.structural.bridge;

/**
 *
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-30 16:47
 * @version 1.0.0
 */
public class Square extends AbstractShape {
    public Square(ColorImplementor colorImplementor) {
        super(colorImplementor);
    }

    @Override
    void color() {
        System.out.print("i'm square,my color:");
        super.color();
    }
}
