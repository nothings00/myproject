package design.pattern.behavioral.command;

/**
 * 打开操作（具体命令类）
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-02 17:12
 * @version 1.0.0
 */
public class TVOpenCommand implements Command {
    private Television tv;

    public TVOpenCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void excute() {
        tv.open();
    }
}
