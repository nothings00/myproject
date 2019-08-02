package design.pattern.structural.adapter;

/**
 * 适配器--对象适配器
 *
 * @author zenghh, 625111833@qq.com
 * @version 1.0.0
 * @date 2019-07-29 17:56
 */
public class Adapter extends Target {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    void request() {
        adaptee.specificRequest();
    }

}
