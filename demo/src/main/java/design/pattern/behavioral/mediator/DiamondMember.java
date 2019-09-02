package design.pattern.behavioral.mediator;

/**
 * 钻石会员
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-20 16:33
 * @version 1.0.0
 */
public class DiamondMember extends Member {


    public DiamondMember(String uid) {
        super(uid);
    }

    @Override
    void sendImage(String image) {
        super.sendImage(image);
    }

    @Override
    void sendText(String text) {
        System.out.println("diamond");
        super.sendText(text);
    }
}
