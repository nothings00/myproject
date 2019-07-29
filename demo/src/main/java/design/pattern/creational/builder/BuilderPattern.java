package design.pattern.creational.builder;

/**
 * 建造者模式
 * 优点：
   1.在建造者模式中， 客户端不必知道产品内部组成的细节，将产品本身与产品的创建过程解耦，使得相同的创建过程可以创建不同的产品对象。
   2.每一个具体建造者都相对独立，而与其他的具体建造者无关，因此可以很方便地替换具体建造者或增加新的具体建造者， 用户使用不同的具体建造者即可得到不同的产品对象 。
   3.可以更加精细地控制产品的创建过程 。将复杂产品的创建步骤分解在不同的方法中，使得创建过程更加清晰，也更方便使用程序来控制创建过程。
   4.增加新的具体建造者无须修改原有类库的代码，指挥者类针对抽象建造者类编程，系统扩展方便，符合“开闭原则”。
 * 缺点：
   1.建造者模式所创建的产品一般具有较多的共同点，其组成部分相似，如果产品之间的差异性很大，则不适合使用建造者模式，因此其使用范围受到一定的限制。
   2.如果产品的内部变化复杂，可能会导致需要定义很多具体建造者类来实现这种变化，导致系统变得很庞大。
 * 适用环境：
   1.需要生成的产品对象有复杂的内部结构，这些产品对象通常包含多个成员属性。
   2.需要生成的产品对象的属性相互依赖，需要指定其生成顺序。
   3.对象的创建过程独立于创建该对象的类。在建造者模式中引入了指挥者类，将创建过程封装在指挥者类中，而不在建造者类中。
   4.隔离复杂对象的创建和使用，并使得相同的创建过程可以创建不同的产品
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
        System.out.println(mealBuilder.getMeal());

        mealBuilder = new SubMealBuilderA();
        kfcWaiter.setMealBuilder(mealBuilder);
        kfcWaiter.constuct();
        System.out.println(mealBuilder.getMeal());

        mealBuilder = new SubMealBuilderB();
        kfcWaiter.setMealBuilder(mealBuilder);
        kfcWaiter.constuct();
        System.out.println(mealBuilder.getMeal());
    }
}
