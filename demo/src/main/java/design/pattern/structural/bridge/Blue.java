package design.pattern.structural.bridge;

/**
 * 蓝色
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-30 17:07
 * @version 1.0.0
 */
public class Blue implements ColorImplementor {
    @Override
    public void colorImpl() {
        System.out.println("blue");
    }
}
