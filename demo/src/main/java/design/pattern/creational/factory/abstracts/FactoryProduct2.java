package design.pattern.creational.factory.abstracts;

/**
 * 类型2系列产品工厂
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-26 13:43
 * @version 1.0.0
 */
public class FactoryProduct2 implements Factory {

    @Override
    public ProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB2();
    }
}
