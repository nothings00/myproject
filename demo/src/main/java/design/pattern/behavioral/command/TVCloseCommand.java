package design.pattern.behavioral.command;


/**
 * 电视关闭操作（具体命令类）
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-02 17:13
 * @version 1.0.0
 */
public class TVCloseCommand implements Command {
    private Television tv;

    public TVCloseCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void excute() {
        tv.off();
    }
}
