package core2.security.classloader;

import core2.international.retire.Retire;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 解密类
 * @author zenghh
 * @version 1.0
 * @date 2020/11/3 11:28 AM
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new ClassLoaderFrame();
            frame.setTitle("ClassLoaderTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * 填写加载文件等类名和加密密钥
 */
class ClassLoaderFrame extends JFrame {
    private JTextField keyField = new JTextField("3", 4);
    private JTextField nameField = new JTextField("Calculator", 30);
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public ClassLoaderFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new Label("Class"), gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(nameField, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new Label("Key"),gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx =100;
        gbc.weighty = 0;
        add(keyField,gbc);
        JButton loadButton = new JButton("Load");
        add(loadButton, new Retire.GBC(0,2,2,1));
        loadButton.addActionListener(e -> {
            runClass(nameField.getText(),keyField.getText());
        });
        pack();
    }

    public void runClass(String name,String key){
        try{
            ClassLoader loader = new CryptoClassLoader(Integer.parseInt(key));
            Class<?> c = loader.loadClass(name);
            Method m = c.getMethod("main",String[].class);
            m.invoke(null, (Object) new String[]{});
        }catch (Throwable e){
            JOptionPane.showMessageDialog(this,e);
        }
    }
}

class CryptoClassLoader extends ClassLoader{
    private int key;

    public CryptoClassLoader(int key) {
        this.key = key;
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = null;
        classBytes = loadClassBytes(name);
        Class<?> cl = defineClass(name,classBytes,0,classBytes.length);
        if (cl == null){
            throw new ClassNotFoundException(name);
        }
        return cl;

    }

    private byte[] loadClassBytes(String name) throws IOException{
        String canme =name.replace('.','/')+".caesar";
        byte[] bytes = Files.readAllBytes(Paths.get(canme));
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] - key);
        }
        return bytes;
    }
}