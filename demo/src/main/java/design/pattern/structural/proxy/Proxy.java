package design.pattern.structural.proxy;

/**
 * 代理主题角色
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-02 16:31
 * @version 1.0.0
 */
public class Proxy implements Subject {
    private RealSubject realSubject ;

    public Proxy() {
        this.realSubject = new RealSubject();
    }

    public void preRequest(){
        System.out.println(this.getClass().getSimpleName()+"::preRequest");
    }

    @Override
    public void request() {
        preRequest();
        realSubject.request();
        afterRequest();
    }

    public void afterRequest(){
        System.out.println(this.getClass().getSimpleName()+"::afterRequest");
    }
}
