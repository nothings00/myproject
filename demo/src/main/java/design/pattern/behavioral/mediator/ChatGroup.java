package design.pattern.behavioral.mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * 聊天组
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-20 16:33
 * @version 1.0.0
 */
public class ChatGroup implements ChatRoom {
    private Map<String,Member> members = new HashMap<>();

    @Override
    public void register(Member member) {
        members.put(member.getUid(),member);
        member.setChatRoom(this);
    }

    @Override
    public void sendImage(String image) {
        System.out.println(image);
    }

    @Override
    public void sendText(String from,String text) {
        System.out.println(from+":"+text);
    }

    @Override
    public void sendText(String from, String to, String text) {
        Member toMember = members.get(to);
        toMember.receiveText(from,text);
    }
}
