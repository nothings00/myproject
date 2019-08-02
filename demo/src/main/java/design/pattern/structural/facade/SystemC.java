package design.pattern.structural.facade;

/**
 * 系统类C
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-01 10:53
 * @version 1.0.0
 */
public class SystemC {
    void operationC(){
        System.out.println(this.getClass().getSimpleName()+"::operationC()");
    }
}
