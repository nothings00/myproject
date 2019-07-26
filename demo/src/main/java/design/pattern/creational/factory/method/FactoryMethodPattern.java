package design.pattern.creational.factory.method;

import java.sql.*;

/**
 * 工厂方法模式（工厂模式）
 * 优点：
   1.在工厂方法模式中，工厂方法用来创建客户所需要的产品，同时还向客户隐藏了哪种具体产品类将被实例化这一细节，用户只需要关心所需产品对应的工厂，无须关心创建细节，甚至无须知道具体产品类的类名。
   2.基于工厂角色和产品角色的多态性设计是工厂方法模式的关键。它能够使工厂可以自主确定创建何种产品对象，而如何创建这个对象的细节则完全封装在具体工厂内部。工厂方法模式之所以又被称为多态工厂模式，是因为所有的具体工厂类都具有同一抽象父类。
   3.使用工厂方法模式的另一个优点是在系统中加入新产品时，无须修改抽象工厂和抽象产品提供的接口，无须修改客户端，也无须修改其他的具体工厂和具体产品，而只要添加一个具体工厂和具体产品就可以了。这样，系统的可扩展性也就变得非常好，完全符合“开闭原则”。
 缺点：
   1.在添加新产品时，需要编写新的具体产品类，而且还要提供与之对应的具体工厂类，系统中类的个数将成对增加，在一定程度上增加了系统的复杂度，有更多的类需要编译和运行，会给系统带来一些额外的开销。
   2.由于考虑到系统的可扩展性，需要引入抽象层，在客户端代码中均使用抽象层进行定义，增加了系统的抽象性和理解难度，且在实现时可能需要用到DOM、反射等技术，增加了系统的实现难度。
 在以下情况下可以使用工厂方法模式：
   1.一个类不知道它所需要的对象的类：在工厂方法模式中，客户端不需要知道具体产品类的类名，只需要知道所对应的工厂即可，具体的产品对象由具体工厂类创建；客户端需要知道创建具体产品的工厂类。
   2.一个类通过其子类来指定创建哪个对象：在工厂方法模式中，对于抽象工厂类只需要提供一个创建产品的接口，而由其子类来确定具体要创建的对象，利用面向对象的多态性和里氏代换原则，在程序运行时，子类对象将覆盖父类对象，从而使得系统更容易扩展。
   3.将创建对象的任务委托给多个工厂子类中的某一个，客户端在使用时可以无须关心是哪一个工厂子类创建产品子类，需要时再动态指定，可将具体工厂类的类名存储在配置文件或数据库中。
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-25 14:17
 * @version 1.0.0
 */
public class FactoryMethodPattern {
    public static void main(String[] args) {

        //fileLog
        LogFactory logFactory = FactoryManager.getLogFactory("file");
        Log log = logFactory.getLog("file1");
        log.writeLog();

        //database log
        logFactory = FactoryManager.getLogFactory("database");
        log = logFactory.getLog("database1");
        log.writeLog();

        //application
        try {
            Connection connection = null;
            connection = DriverManager.getConnection("test");
            Statement statement = connection.createStatement();
            ResultSet rs= statement.executeQuery("select * from dual");
        } catch (SQLException e) {
            System.out.println("sql exception:"+e.getMessage());
        }


    }
}
