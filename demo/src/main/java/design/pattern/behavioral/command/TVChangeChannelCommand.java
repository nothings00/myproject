package design.pattern.behavioral.command;

/**
 * 切换电视频道（具体命令类）
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-02 17:13
 * @version 1.0.0
 */
public class TVChangeChannelCommand implements Command{

    private Television tv;

    public TVChangeChannelCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void excute() {
        tv.changeChannel();
    }
}
