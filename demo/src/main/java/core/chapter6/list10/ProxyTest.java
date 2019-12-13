package core.chapter6.list10;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * 代理测试
 * @author zenghh, 625111833@qq.com
 * @date 2019-12-11 18:17
 * @version 1.0.0
 */
public class ProxyTest {
    public static void main(String[] args) {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Object[] elements = new Object[1000];

        for (int i = 0; i < elements.length; i++) {
            InvocationHandler handler  =  new TraceHandler((Integer)i+1);
            Object proxy = Proxy.newProxyInstance(null,Integer.class.getInterfaces(),handler);
            elements[i] = proxy ;
        }

        //construct a random integer
        Integer key = new Random().nextInt(elements.length)+1;

        //search for the key
        int result = Arrays.binarySearch(elements,key);

        //print match if found
        if (result >=0){
            System.out.println(elements[result]);
        }
    }
}


/**
 * an invocation handler that prints out the method name and parameters,
 * then invokes the original method
 * @author zenghh, 625111833@qq.com
 * @date 2019-12-11 18:20
 * @version 1.0.0
 */
class TraceHandler implements InvocationHandler{
    private Object target;

    /**
     * Construct a TraceHandler
     * @param object
     */
    public TraceHandler(Object object){
            target = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //print implicit argument
        System.out.print(target);
        //print method name
        System.out.print("."+method.getName()+"(");
        //print explicit arguments
        if (args != null){
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1){
                    System.out.print(", ");
                }
            }
        }
        System.out.println(")");

        // invoke actual method
        return method.invoke(target,args);
    }
}
