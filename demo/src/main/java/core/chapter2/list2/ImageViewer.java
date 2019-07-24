package core.chapter2.list2;


import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * A program for viewing images
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-10 9:14
 * @version 1.0.0
 */
public class ImageViewer {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame jFrame = new ImageViewerFrame();
            jFrame.setTitle("ImageViewer");
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jFrame.setVisible(true);
        });
    }
}

/**
 * a frame with a label to show an image
 */
class ImageViewerFrame extends JFrame{
    private static final String PNG = "png";
    private static final String JPEG = "jpeg";
    private static final String JPG = "jpg";
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    private JLabel label;
    private  JFileChooser chooser;

    ImageViewerFrame() {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

        //user a label to display the images
        label = new JLabel();
        add(label);

        //set up the file chooser
        chooser =  new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        //set up the menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);

        openItem.addActionListener(event->{
            // show file chooser dialog
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION){
                //display by image width and height
                File imgae = chooser.getSelectedFile();
                String name =  imgae.getName();
                int point = name.indexOf(".");
                String suffix = name.substring(point+1);
                if (JPG.equals(suffix)|| JPEG.equals(suffix)|| PNG.equals(suffix)){
                    setSize(ImageTool.getImageHeight(imgae),ImageTool.getImageHeight(imgae));

                }
                String path = imgae.getPath();
                label.setIcon(new ImageIcon(path));
            }
        });

        JMenuItem exitItem  =  new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(event-> System.exit(0));

    }
}
