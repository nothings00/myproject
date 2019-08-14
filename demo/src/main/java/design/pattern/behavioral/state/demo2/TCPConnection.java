package design.pattern.behavioral.state.demo2;

import javassist.bytecode.ByteArray;

import javax.xml.crypto.OctetStreamData;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * TCP连接
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-13 10:42
 * @version 1.0.0
 */
public class TCPConnection {

    private TCPState state;

    public TCPConnection() {
        System.out.println("create a new connection");
        state = new TCPClosed();
    }

    public TCPConnection(TCPState state) {
        System.out.println("create a new connection");
        this.state = state;
    }

    private void changeState(TCPState state){
        this.state = state;
    }
    /**
     * 主动打开连接
     */
    public void activeOpen(){
        state.activeOpen(this);
        changeState(new TCPEstablish());

    }

    /**
     * 关闭
     */
    public void close(){
        state.close(this);
        changeState(new TCPListen());
    }

    /**
     * 被动打开
     */
    public void passiveOpen(){
        state.passiveOpen(this);
        changeState(new TCPEstablish());
    }

    /**
     * 发送
     */
    public void send(){
        state.send(this);
        changeState(new TCPEstablish());
    }

    public void processInfo(InputStream inputStream) throws IOException {
        state.processInfo(this,inputStream);
    }
}
