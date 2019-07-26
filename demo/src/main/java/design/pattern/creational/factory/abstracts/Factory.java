package design.pattern.creational.factory.abstracts;


/**
 * 工厂接口
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-26 10:30
 * @version 1.0.0
 */
public interface Factory {
    /**
     * 创建产品A
     * @return
     */
    ProductA createProductA();

    /**
     * 创建产品B
     * @return
     */
    ProductB createProductB();
}
