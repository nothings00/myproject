package design.pattern.behavioral.state;

import design.pattern.behavioral.state.demo2.TCPConnection;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 状态模式
 * 优点：
   1.封装了转换规则。
   2.枚举可能的状态，在枚举状态之前需要确定状态种类。
   3.将所有与某个状态有关的行为放到一个类中，并且可以方便地增加新的状态，只需要改变对象状态即可改变对象的行为。
   4.允许状态转换逻辑与状态对象合成一体，而不是某一个巨大的条件语句块。
   5.可以让多个环境对象共享一个状态对象，从而减少系统中对象的个数。
 * 缺点：
   1.状态模式的使用必然会增加系统类和对象的个数。
   2.状态模式的结构与实现都较为复杂，如果使用不当将导致程序结构和代码的混乱。
   3.状态模式对“开闭原则”的支持并不太好，对于可以切换状态的状态模式，增加新的状态类需要修改那些负责状态转换的源代码，否则无法切换到新增状态；而且修改某个状态类的行为也需修改对应类的源代码。
 * 适用情况：
   1.对象的行为依赖于它的状态（属性）并且可以根据它的状态改变而改变它的相关行为。
   2.代码中包含大量与对象状态有关的条件语句，这些条件语句的出现，会导致代码的可维护性和灵活性变差，不能方便地增加和删除状态，使客户类与类库之间的耦合增强。在这些条件语句中包含了对象的行为，而且这些条件对应于对象的各种状态。
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-10 17:07
 * @version 1.0.0
 */
public class StatePattern {
    public static void main(String[] args) throws IOException {
//        demo1
//        Context context = new Context();
//        context.request();
//        context.request();
//        context.request();

//        demo2
        ByteArrayInputStream bis = new ByteArrayInputStream("hello,world1".getBytes());
        TCPConnection connection = new TCPConnection();
        connection.activeOpen();
        connection.processInfo(bis);
        connection.close();

        connection.send();
    }
}
