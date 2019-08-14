package design.pattern.behavioral.state.demo2;

/**
 * TCP监听状态
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-13 10:49
 * @version 1.0.0
 */
public class TCPListen extends TCPState {
    public TCPListen() {
        System.out.println("create a new listen state");
    }

    @Override
    public void send(TCPConnection connection) {
        System.out.println("tcp connection  listen sending");
    }
}
