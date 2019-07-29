package design.pattern.creational.builder;

/**
 * KFC 服务员
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-26 16:22
 * @version 1.0.0
 */
public class KFCWaiter {
    private MealBuilder mealBuilder;

    public void setMealBuilder(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    public void constuct(){
        mealBuilder.buildDrink();
        mealBuilder.buildFood();
    }
}
