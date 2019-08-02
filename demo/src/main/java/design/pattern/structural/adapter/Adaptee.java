package design.pattern.structural.adapter;

/**
 * 适配者
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-29 17:56
 * @version 1.0.0
 */
public class Adaptee {
    void specificRequest(){
        System.out.println(this.getClass().getSimpleName()+"::specificRequest ");
    };
}
