package design.pattern.behavioral.mediator;


/**
 * 聊天室
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-20 16:28
 * @version 1.0.0
 */
public interface ChatRoom {
    /** 用户加入*/
    void register(Member member);

    /** 发送图片消息*/
    void sendImage(String image);

    /** 发送文本消息*/
    void sendText(String from,String text);

    /**
     *  发送文本消息
     * @param from 接收方
     * @param to 发送方
     * @param text 文本
     */
    void sendText(String from ,String to,String text);
}
