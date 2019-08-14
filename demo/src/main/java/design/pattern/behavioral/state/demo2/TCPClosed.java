package design.pattern.behavioral.state.demo2;

/**
 * TCP 关闭状态
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-13 10:50
 * @version 1.0.0
 */
public class TCPClosed extends TCPState{

    public TCPClosed() {
        System.out.println("create a new closed state");
    }

    @Override
    public void activeOpen(TCPConnection connection) {
        System.out.println("tcp connection active opening ");
    }

    @Override
    public void passiveOpen(TCPConnection connection) {
        System.out.println("tcp connection passive opening ");
    }
}
