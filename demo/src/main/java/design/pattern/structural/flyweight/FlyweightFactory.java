package design.pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂类
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-01 11:26
 * @version 1.0.0
 */
public class FlyweightFactory {
    Map<String,Flyweight> map = new HashMap<>();
    Map<String,Flyweight> unshareds = new HashMap<>();

    public Flyweight getFlyweight(String name){
        Flyweight flyweight = map.get(name);
        if (null==flyweight){
            Flyweight concreteFlyweight = new ConcreteFlyweight(name);
            map.put(name,concreteFlyweight);
            return concreteFlyweight;
        }
        System.out.println("Flyweight["+name+"] has already in the pool,use exists one:");
        return flyweight;
    }

}
