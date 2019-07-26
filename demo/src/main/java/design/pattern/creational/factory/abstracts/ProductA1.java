package design.pattern.creational.factory.abstracts;

/**
 * 产品A类型1
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-26 13:41
 * @version 1.0.0
 */
public class ProductA1 implements ProductA {
    @Override
    public void walk() {
        System.out.println("ProductA1 walk sometimes ");
    }
}
