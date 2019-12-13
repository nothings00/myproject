package core.chapter6.list3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * 回调测试
 * @author zenghh, 625111833@qq.com
 * @date 2019-12-10 14:12
 * @version 1.0.0
 */
public class TimerTest {
    private String s;
    public static void main(String[] args) {
        ActionListener actionListener = new TimerPrint();
        //construct a timer calls the listener
        //once every 10 seconds
        Timer timer = new Timer(10*1000,actionListener);
        timer.start();
        JOptionPane.showMessageDialog(null,"Quit program?");
        System.exit(0);
    }
}

class TimerPrint implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone ,the time is "+new Date());
        Toolkit.getDefaultToolkit().beep();
    }
}
