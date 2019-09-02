package design.pattern.behavioral.mediator;

/**
 * 中介者模式
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-20 16:27
 * @version 1.0.0
 */
public class MediatorPattern {
    public static void main(String[] args) {
        CommonMember commonMember = new CommonMember("1");
        DiamondMember diamondMember = new DiamondMember("2");
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.register(commonMember);
        chatGroup.register(diamondMember);
        commonMember.sendText("hi,i'm zhh");
        diamondMember.sendText("hello,i'm zyc");


    }
}
