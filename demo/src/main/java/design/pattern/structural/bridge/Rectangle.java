package design.pattern.structural.bridge;

/**
 * 矩形
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-30 16:37
 * @version 1.0.0
 */
public  class Rectangle extends AbstractShape {
    public Rectangle(ColorImplementor colorImplementor) {
        super(colorImplementor);
    }

    @Override
    void color() {
        System.out.print("i'm rectangle,my color:");
        super.color();
    }
}
