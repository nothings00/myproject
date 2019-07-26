package design.pattern.creational.factory.simple;


/**
 * 简单工厂
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-25 10:05
 * @version 1.0.0
 */
public class ProductSimpleFactory {
    public static final String PRODUCT_A = "PA";
    public static final String PRODUCT_B = "PB";

    /**
     * 根据产品名称返回产品
     * @param productName
     * @return
     */
    public static Product getProductInstance(String productName){
        switch (productName){
            case PRODUCT_A:
                return new ProductA();
            case  PRODUCT_B:
                return new ProductB();
            default:
                return new Product() {
                    @Override
                    public Product use() {
                        System.out.println("I CAN'T WORK AS YOU LIKE");
                        return this;
                    }
                };

        }

    }
}
