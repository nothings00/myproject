package design.pattern.behavioral.state.demo2;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * TCP状态
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-13 10:42
 * @version 1.0.0
 */
public class TCPState {
    /**
     * 主动打开连接
     * @param connection
     */
    public void activeOpen(TCPConnection connection){
        System.out.println("no connection activeOpen");
    }

    /**
     * 改变状态
     * @param connection
     * @param state
     */
    protected void changeState(TCPConnection connection,TCPState state){
        System.out.println("no connection changeState");
    }

    /**
     * 关闭
     * @param connection
     */
    public void close(TCPConnection connection){
        System.out.println("no connection close");
    }

    /**
     * 被动打开
     * @param connection
     */
    public void passiveOpen(TCPConnection connection){
        System.out.println("no connection passiveOpen");
    }

    /**
     * 发送
     * @param connection
     */
    public void send(TCPConnection connection){
        System.out.println("no connection send");

    }

    public void processInfo(TCPConnection connection,InputStream inputStream) throws IOException {
        System.out.println("no connection process");
    }
}
