package design.pattern.creational.factory.simple;


/**
 * 产品B
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-25 10:14
 * @version 1.0.0
 */
public class ProductB implements Product {
    @Override
    public Product use() {
        System.out.println("Use ProductB");
        return this;
    }
}
