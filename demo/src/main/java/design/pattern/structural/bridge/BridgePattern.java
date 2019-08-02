package design.pattern.structural.bridge;

/**
 * 桥接模式 --又名 Handler and Body,Interface
 * 优点：
   1.分离抽象接口及其实现部分。
   2.桥接模式有时类似于多继承方案，但是多继承方案违背了类的单一职责原则（即一个类只有一个变化的原因），复用性比较差，而且多继承结构中类的个数非常庞大，桥接模式是比多继承方案更好的解决方法。
   3.桥接模式提高了系统的可扩充性，在两个变化维度中任意扩展一个维度，都不需要修改原有系统。
   4.实现细节对客户透明，可以对用户隐藏实现细节。
 * 缺点：
   1.桥接模式的引入会增加系统的理解与设计难度，由于聚合关联关系建立在抽象层，要求开发者针对抽象进行设计与编程。 - 桥接模式要求正确识别出系统中两个独立变化的维度，因此其使用范围具有一定的局限性。
 * 适用场景：
   1.如果一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性，避免在两个层次之间建立静态的继承联系，通过桥接模式可以使它们在抽象层建立一个关联关系。
   2.抽象化角色和实现化角色可以以继承的方式独立扩展而互不影响，在程序运行时可以动态将一个抽象化子类的对象和一个实现化子类的对象进行组合，即系统需要对抽象化角色和实现化角色进行动态耦合。
   3.一个类存在两个独立变化的维度，且这两个维度都需要进行扩展。
   4.虽然在系统中使用继承是没有问题的，但是由于抽象化角色和具体化角色需要独立变化，设计要求需要独立管理这两者。
   5.对于那些不希望使用继承或因为多层次继承导致系统类的个数急剧增加的系统，桥接模式尤为适用
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-30 16:33
 * @version 1.0.0
 */
public class BridgePattern {
    public static void main(String[] args) {
        ColorImplementor yellow = new Yellow();
        AbstractShape circle = new Circle(yellow);
        circle.color();

        ColorImplementor green = new Green();
        AbstractShape oval = new Oval(green);
        oval.color();

        ColorImplementor blue = new Blue();
        AbstractShape rectangle = new Rectangle(blue);
        rectangle.color();
    }
}
