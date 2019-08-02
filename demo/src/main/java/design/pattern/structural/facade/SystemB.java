package design.pattern.structural.facade;

/**
 * 系统类B
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-01 10:53
 * @version 1.0.0
 */
public class SystemB {
    void operationB(){
        System.out.println(this.getClass().getSimpleName()+"::operationB()");
    }
}
