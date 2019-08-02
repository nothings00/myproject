package design.pattern.behavioral.command;

/**
 * 遥控器（请求者）
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-02 17:14
 * @version 1.0.0
 */
public class Controller {
    private Command command;

    public Controller(Command command) {
        this.command = command;
    }

   void call(){
        command.excute();
   }
}
