package design.pattern.behavioral.observer.instance;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-20 16:06
 * @version 1.0.0
 */
public class ObserverImpl implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(o.countObservers()+arg.toString());
    }
}
