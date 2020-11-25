package core2.security.jaas;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author zenghh
 * @date 2020/11/25 10:14 AM
 * @version 1.0
 */
public class JASSTest {
    //demo 是login模块，test是action模块
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        EventQueue.invokeLater(()->{
//            JFrame frame = new JASSFrame();
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setTitle("JAASTtest");
//            frame.setVisible(true);
        });
    }
}
