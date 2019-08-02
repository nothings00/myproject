package design.pattern.behavioral.command;

/**
 * 命令模式
 * 优点：
   1.降低系统的耦合度。
   2.新的命令可以很容易地加入到系统中。
   3.可以比较容易地设计一个命令队列和宏命令（组合命令）。
   4.可以方便地实现对请求的Undo和Redo。
 * 缺点：
   1.使用命令模式可能会导致某些系统有过多的具体命令类。因为针对每一个命令都需要设计一个具体命令类，因此某些系统可能需要大量具体命令类，这将影响命令模式的使用。
 * 适用环境：
   1.系统需要将请求调用者和请求接收者解耦，使得调用者和接收者不直接交互。
   2.系统需要在不同的时间指定请求、将请求排队和执行请求。
   3.系统需要支持命令的撤销(Undo)操作和恢复(Redo)操作。
   4.系统需要将一组操作组合在一起，即支持宏命令
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-02 17:07
 * @version 1.0.0
 */
public class CommandPattern {
    public static void main(String[] args) {
        Television tv = new Television(Integer.MAX_VALUE);
        TVOpenCommand tvOpen = new TVOpenCommand(tv);
        TVChangeChannelCommand tvChangeChannel = new TVChangeChannelCommand(tv);
        TVCloseCommand tvClose = new TVCloseCommand(tv);

        Controller controller = new Controller(tvOpen);
        controller.call();

        controller = new Controller(tvChangeChannel);
        controller.call();

        controller = new Controller(tvClose);
        controller.call();

    }
}
