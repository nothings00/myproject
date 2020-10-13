package core2.compile.x;

import javax.swing.*;
/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/10/13 2:49 PM
 * @version 1.0
 */
public abstract class ButtonFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    protected JPanel panel;
    protected JButton yellowButton;
    protected JButton blueButton;
    protected JButton redButton;

    protected abstract void addEventHandlers();

    public ButtonFrame(){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        panel = new JPanel();
        panel.setName("panel");
        add(panel);

        yellowButton = new JButton("Yellow");
        yellowButton.setName("yellowButton");
        blueButton = new JButton("Blue");
        blueButton.setName("blueButton");
        redButton = new JButton("Red");
        redButton.setName("redButton");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        //统一的监听事件加载处
        addEventHandlers();
    }
}
