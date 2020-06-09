package core.chapter14.list1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/6/2 3:39 下午
 * @version 1.0
 */
public class BallComponent extends JPanel {
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;

    List<Ball> balls = new ArrayList<>();

    public void add(Ball b){
        balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g){
        //erase background
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        for (Ball b : balls){
            g2.fill(b.getShape());
        }
    }

    public Dimension getPreferredSized(){
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }


}
