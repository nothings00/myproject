package design.pattern.creational.factory.builder;

import lombok.ToString;

/**
 * 套餐建造者
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-26 16:12
 * @version 1.0.0
 */
@ToString
public class MealBuilder {
    private Meal meal;

    public void buildDrink(){
        meal.setDrink("drink - orange juice");
    }

    public void buildFood(){
        meal.setFood("food - rice");
    }

    public Meal getMeal() {
        return meal;
    }
}
