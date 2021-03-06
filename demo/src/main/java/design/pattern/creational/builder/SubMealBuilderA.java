package design.pattern.creational.builder;

/**
 * 套餐A
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-26 16:17
 * @version 1.0.0
 */
public class SubMealBuilderA extends MealBuilder{
    @Override
    public void buildDrink() {
        getMeal().setDrink("apple juice");
    }

    @Override
    public void buildFood() {
        getMeal().setFood("fried chicken");
    }
}
