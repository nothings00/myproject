package core.chapter5.list13;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * 反射类测试
 * @author zenghh, 625111833@qq.com
 * @date 2019-12-09 14:08
 * @version 1.0.0
 */
public class ReflectionTest {
    public static void main(String[] args) {
        // read class name from command line args or user input
        String name ;
        if (args.length>0 ){
            name = args[0];
        }else {
            Scanner in  = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date): ");
            name=in.next();
        }

        try {
            //print class name and superclass name (if != Object)
            Class clazz = Class.forName(name);
            Class superClazz = clazz.getSuperclass();
            String modifiers = Modifier.toString(clazz.getModifiers());
            if (modifiers.length() >0){
                System.out.print(modifiers + " ");
            }
            System.out.print("class " + name);
            if (superClazz!=null && superClazz != Object.class){
                System.out.print(" extends "+superClazz);
            }
            System.out.println(" {\n");

            printFields(clazz);
            System.out.println();
            printConstructors(clazz);
            System.out.println();
            printMethods(clazz);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * print all
     * @param clazz
     */
    public static void printFields(Class clazz){
        Field[] fields = clazz.getFields();
        //print modifier,type,name
        for (Field field : fields){
            System.out.print("    ");
            String modifier = Modifier.toString(field.getModifiers());
            if (modifier.length()>0){
                System.out.print(modifier+" ");
            }
            Class fieldType = field.getType();
            String name = field.getName();
            System.out.println(fieldType.getName() + " " + name+";" );
        }

    }
    /**
     * print all constructors of a class
     * @param clazz a class
     */
    public static void printConstructors(Class clazz){
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors){
            System.out.print("    ");
            String  name = constructor.getName();
            String modifier = Modifier.toString(constructor.getModifiers());
            if (modifier.length() > 0 ){
                System.out.print(modifier + " ");
            }
            System.out.print(name+"(");

            //print parameter types
            printParameters(constructor.getParameterTypes());
        }
    }

    /**
     * print all methods of a class
     * @param clazz a class
     */
    public static void printMethods(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){
            System.out.print("    ");
            Class retType = method.getReturnType();
            String name  = method.getName();

            //print modifier,return type,name,parameters
            String modifier = Modifier.toString(method.getModifiers());
            if (modifier.length() > 0){
                System.out.print(modifier+" ");
            }
            System.out.print(retType + " " + name + "(");
            printParameters(method.getParameterTypes());
        }
    }

    /**
     * print parameter
     * @param parameterTypes all parameter type
     */
    private static void printParameters(Class<?>[] parameterTypes) {
        for (int i = 0; i < parameterTypes.length; i ++){
            if (i > 0){
                System.out.print(", ");
            }
            System.out.print(parameterTypes[i].getName());
        }
        System.out.println(");");
    }
}
