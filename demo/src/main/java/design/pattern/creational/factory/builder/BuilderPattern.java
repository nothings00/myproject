package design.pattern.creational.factory.builder;

/**
 * 建造者模式
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-26 15:55
 * @version 1.0.0
 */
public class BuilderPattern {
    public static void main(String[] args) {
        KFCWaiter kfcWaiter  = new KFCWaiter();
        MealBuilder mealBuilder = new MealBuilder();
        kfcWaiter.setMealBuilder(mealBuilder);
        kfcWaiter.constuct();
        System.out.println();
        mealBuilder = new SubMealBuilderA();
        kfcWaiter.setMealBuilder(mealBuilder);
        kfcWaiter.constuct();

        mealBuilder = new SubMealBuilderB();
        kfcWaiter.setMealBuilder(mealBuilder);
        kfcWaiter.constuct();
    }
}
