package design.pattern.structural.bridge;

/**
 * 椭圆
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-30 16:46
 * @version 1.0.0
 */
public  class Oval extends AbstractShape {
    public Oval(ColorImplementor colorImplementor) {
        super(colorImplementor);
    }

    @Override
    void color() {
        System.out.print("i'm oval,my color:");
        super.color();
    }
}
