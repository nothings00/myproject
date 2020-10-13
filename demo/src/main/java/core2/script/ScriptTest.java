package core2.script;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/10/13 9:58 AM
 * @version 1.0
 */
public class ScriptTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()-> {
            try {
                ScriptEngineManager manager = new ScriptEngineManager();
                String language;
                if (args.length == 0) {
                    System.out.println("Available factories: ");
                    for (ScriptEngineFactory factory : manager.getEngineFactories()) {
                        System.out.println(factory.getEngineName());
                    }
                    language = "nashorn";
                } else {
                    language = args[0];
                }
                final ScriptEngine engine = manager.getEngineByName(language);
                if (engine == null) {
                    System.err.println("No engine for " + language);
                    System.exit(1);
                }


                JFrame frame = new ButtonFrame();
                InputStream in = frame.getClass().getResourceAsStream("/script/init." + language);
                if (in != null) {
                    engine.eval(new InputStreamReader(in));

                }

                Map<String, Component> components = new HashMap<>();
                getComponentBindings(frame, components);
                components.forEach((name, c) -> {
                    engine.put(name, c);
                });

                final Properties events = new Properties();
                in = frame.getClass().getResourceAsStream("/script/" + language + ".properties");
                events.load(in);

                for (final Object e : events.keySet()) {
                    String[] s = ((String) e).split("\\.");
                    addListener(s[0], s[1], (String) events.get(e), engine, components);
                }
                frame.setTitle("ScriptTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch (IOException | ScriptException | IllegalAccessException | IntrospectionException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });


    }

    public static void getComponentBindings(Component c, Map<String,Component> namedComponents){
        String name = c.getName();
        if (name != null){
            namedComponents.put(name,c);
        }
        if (c instanceof Container){
            for (Component child : ((Container) c).getComponents()){
                getComponentBindings(child,namedComponents);
            }
        }
    }

    public static void addListener(String beanName, String eventName, final String scriptCode, final ScriptEngine engine
            , Map<String, Component> components) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Object bean = components.get(beanName);
        EventSetDescriptor descriptor = getEventSetDescriptor(bean,eventName);
        if (descriptor == null) {
            return;
        }
        descriptor.getAddListenerMethod().invoke(bean, Proxy.newProxyInstance(null
                ,new Class[]{descriptor.getListenerType()}
                ,(proxy,method,args)->{
                    engine.eval(scriptCode);
                    return null;
                 }
                ));

    }
    public static EventSetDescriptor getEventSetDescriptor(Object bean,String eventName) throws IntrospectionException {
        for (EventSetDescriptor descriptor : Introspector.getBeanInfo(bean.getClass()).getEventSetDescriptors()){
            if (descriptor.getName().equals(eventName)){
                return descriptor;
            }
        }
        return null;
    }
}

class ButtonFrame extends JFrame{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    private JPanel panel;
    private JButton yellowButton;
    private JButton blueButton;
    private JButton redButton;

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


    }
}
