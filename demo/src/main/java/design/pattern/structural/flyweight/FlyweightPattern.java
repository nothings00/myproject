package design.pattern.structural.flyweight;

/**
 * 享元模式
 * 优点：
   1.享元模式的优点在于它可以极大减少内存中对象的数量，使得相同对象或相似对象在内存中只保存一份。
   2.享元模式的外部状态相对独立，而且不会影响其内部状态，从而使得享元对象可以在不同的环境中被共享。
 * 缺点：
   1.享元模式使得系统更加复杂，需要分离出内部状态和外部状态，这使得程序的逻辑复杂化。
   2.为了使对象可以共享，享元模式需要将享元对象的状态外部化，而读取外部状态使得运行时间变长。
 * 适用情况：
   1.一个系统有大量相同或者相似的对象，由于这类对象的大量使用，造成内存的大量耗费。
   2.对象的大部分状态都可以外部化，可以将这些外部状态传入对象中。
   3.使用享元模式需要维护一个存储享元对象的享元池，而这需要耗费资源，因此，应当在多次重复使用享元对象时才值得使用享元模式。
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-01 11:24
 * @version 1.0.0
 */
public class FlyweightPattern {
    public static void main(String[] args) {
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        Flyweight flyweight = flyweightFactory.getFlyweight("first");
        flyweight.operation();
        flyweight = flyweightFactory.getFlyweight("second");
        flyweight.operation();

        flyweight = flyweightFactory.getFlyweight("first");
        flyweight.operation();
    }
}
