package design.pattern.creational.factory.abstracts;


/**
 * 类型1产品系列
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-26 13:45
 * @version 1.0.0
 */
public class FactoryProduct1 implements Factory {
    @Override
    public ProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB1();
    }
}
