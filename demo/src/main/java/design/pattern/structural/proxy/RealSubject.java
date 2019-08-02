package design.pattern.structural.proxy;

/**
 * 真实主题角色
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-02 16:32
 * @version 1.0.0
 */
public class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println(this.getClass().getSimpleName()+":: request()");
    }
}
