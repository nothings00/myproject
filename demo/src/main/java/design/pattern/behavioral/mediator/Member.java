package design.pattern.behavioral.mediator;

/**
 * 聊天室成员
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-20 16:30
 * @version 1.0.0
 */
public class Member {

    ChatRoom chatRoom;

    private String uid;

    public Member(String uid) {
        this.uid = uid;
    }

    /** 接收图片*/
    void receiveImage(String image){

    };

    /** 接收文本*/
    void receiveText(String from,String text){
        System.out.println("receive "+ from +"'s message:"+text);
    };

    /** 发送图片*/
    void sendImage(String image){
        chatRoom.sendImage(image);

    };

    /** 发送文本*/
    void sendText(String to,String text){
        chatRoom.sendText(uid,to,text);
    };

    /** 发送文本*/
    void sendText(String text){
        chatRoom.sendText(uid,text);
    };

    /** 设置聊天室*/
    void setChatRoom(ChatRoom chatRoom){
        this.chatRoom = chatRoom;
    };

    public String getUid() {
        return uid;
    }

}
