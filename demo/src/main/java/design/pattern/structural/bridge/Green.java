package design.pattern.structural.bridge;

/**
 * 绿色
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-30 17:05
 * @version 1.0.0
 */
public class Green implements ColorImplementor {
    @Override
    public void colorImpl() {
        System.out.println("green");
    }
}
