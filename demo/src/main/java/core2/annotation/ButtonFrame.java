package core2.annotation;

import javax.swing.*;
import java.awt.*;

public class ButtonFrame extends JFrame{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    protected JPanel panel;
    protected JButton yellowButton;
    protected JButton blueButton;
    protected JButton redButton;


    public ButtonFrame(){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        panel = new JPanel();
        add(panel);

        yellowButton = new JButton("Yellow");
        blueButton = new JButton("Blue");
        redButton = new JButton("Red");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);

        ActionListenerInstaller.processAnnotations(this);

    }

    @ActionListenerFor(source="yellowButton")
    public void yellowBackground(){
        panel.setBackground(Color.yellow);
    }

    @ActionListenerFor(source = "blueButton")
    public void blueBackground(){
        panel.setBackground(Color.blue);
    }

    @ActionListenerFor(source ="redButton")
    public void redBackground(){
        panel.setBackground(Color.RED);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new ButtonFrame();
            frame.setTitle("AnnotationTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

    }
}
