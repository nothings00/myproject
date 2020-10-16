package core2.annotation;

import java.awt.event.ActionListener;
import java.lang.reflect.*;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/10/15 5:25 PM
 * @version 1.0
 */
public class ActionListenerInstaller {
    public static void processAnnotations(Object obj){
        try{
            Class<?> cl = obj.getClass();
            for (Method m : cl.getDeclaredMethods()){
                // 1.找到注解
                ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
                if (a != null){
                    //2.获取注解参数对应的字段
                    Field f = cl.getDeclaredField(a.source());
                    f.setAccessible(true);
                    //3.添加监听事件
                    // （JButton，ButtonFrame，colorBackground）
                    addListener(f.get(obj),obj,m);
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加监听事件
     * @param source 被添加对象
     * @param param 被添加方法入参
     * @param m 被添加方法
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void addListener(Object source,final Object param,final Method m)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //修改被调用方法入参为param
        InvocationHandler handler = (proxy, method, args) -> m.invoke(param);
        //1.创建ActionListener代理类
        Object listener = Proxy.newProxyInstance(null,new Class[]{java.awt.event.ActionListener.class},handler);
        //2.找到被添加对象的添加listen方法
        Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
        //3.被添加对象调用添加listen方法
        adder.invoke(source,listener);
    }
}
