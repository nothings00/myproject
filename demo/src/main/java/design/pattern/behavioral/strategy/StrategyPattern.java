package design.pattern.behavioral.strategy;

/**
 * 策略模式 policy
 * 优点：
   1.策略模式提供了对“开闭原则”的完美支持，用户可以在不修改原有系统的基础上选择算法或行为，也可以灵活地增加新的算法或行为。
   2.策略模式提供了管理相关的算法族的办法。
   3.策略模式提供了可以替换继承关系的办法。
   4.使用策略模式可以避免使用多重条件转移语句。
 * 缺点：
   1.客户端必须知道所有的策略类，并自行决定使用哪一个策略类。
   2.策略模式将造成产生很多策略类，可以通过使用享元模式在一定程度上减少对象的数量。
 * 适用场景：
   1.如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为。
   2.一个系统需要动态地在几种算法中选择一种。
   3.如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现。
   4.不希望客户端知道复杂的、与算法相关的数据结构，在具体策略类中封装算法和相关的数据结构，提高算法的保密性与安全性。
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-14 16:05
 * @version 1.0.0
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Context context = new Context();
        // strategy A
        context.setStrategy(new ConcreteStrategyA());
        context.algorithm();
        // strategy B
        context.setStrategy(new ConcreteStrategyB());
        context.algorithm();

    }
}
