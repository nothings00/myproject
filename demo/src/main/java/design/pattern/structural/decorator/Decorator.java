package design.pattern.structural.decorator;

import lombok.Data;

/**
 * 抽象装饰类
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-01 9:44
 * @version 1.0.0
 */
@Data
public abstract class Decorator  {
    Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public void operation(){
        component.operation();
    }
}
