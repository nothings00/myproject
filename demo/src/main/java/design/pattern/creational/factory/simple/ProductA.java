package design.pattern.creational.factory.simple;

/**
 * 产品A
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-25 10:12
 * @version 1.0.0
 */
public class ProductA implements Product {
    @Override
    public ProductA use() {
        System.out.println("Use ProductA");
        return  this;
    }
}
