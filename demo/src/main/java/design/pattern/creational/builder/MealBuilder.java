package design.pattern.creational.builder;

import lombok.ToString;

/**
 * 套餐建造者
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-26 16:12
 * @version 1.0.0
 */
@ToString
public class MealBuilder {
    private Meal meal = new Meal();

    public void buildDrink(){
        meal.setDrink("orange juice");
    }

    public void buildFood(){
        meal.setFood("rice");
    }

    public Meal getMeal() {
        return meal;
    }
}
