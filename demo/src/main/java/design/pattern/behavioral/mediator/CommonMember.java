package design.pattern.behavioral.mediator;

/**
 * 普通会员
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-20 16:34
 * @version 1.0.0
 */
public class CommonMember extends Member{

    public CommonMember(String uid) {
        super(uid);
    }

    @Override
    void sendText(String text) {
        System.out.println("common");
        super.sendText(text);
    }
}
