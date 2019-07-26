package design.pattern.creational.factory.builder;

/**
 * 套餐B
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-26 16:17
 * @version 1.0.0
 */
public class SubMealBuilderB extends MealBuilder{
    @Override
    public void buildDrink() {
        getMeal().setDrink("drink - package B drink - milk");
    }

    @Override
    public void buildFood() {
        getMeal().setFood("food - package B food - two eggs");
    }
}
