package core.chapter14.list1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/6/2 3:27 下午
 * @version 1.0
 */
public class Bounce {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new BounceFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    static class BounceFrame extends JFrame {

        private BallComponent component;
        public static final int STEPS = 1000;
        public static final int DELAY = 3;

        public BounceFrame() {
            setTitle("Bounce");
            component = new BallComponent();
            add(component,BorderLayout.CENTER);
            JPanel buttonPanel = new JPanel();
//            addButton(buttonPanel,"Start", event -> addBall());
            addButton(buttonPanel,"Start", event -> addBallThread());
            addButton(buttonPanel,"Close", event -> System.exit(0));
            add(buttonPanel,BorderLayout.SOUTH);
            pack();
        }

        public void addButton(Container c, String title, ActionListener listener){
            JButton button = new JButton(title);
            c.add(button);
            button.addActionListener(listener);
        }

        public void addBall(){
            try {
                Ball ball = new Ball();
                component.add(ball);

                for (int i = 1; i<=STEPS;i++){
                    ball.move(component.getBounds());
                    component.paint(component.getGraphics());
                    Thread.sleep(DELAY);
                }
            } catch (InterruptedException e){

            }
        }

        public void addBallThread(){
            Ball ball = new Ball();
            component.add(ball);

            Runnable r = () -> {
                for (int i = 1; i<=STEPS;i++){
                    ball.move(component.getBounds());
                    component.paint(component.getGraphics());
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread t = new Thread(r);
            t.start();
        }

    }

}
