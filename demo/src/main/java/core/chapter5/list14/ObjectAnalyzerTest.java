package core.chapter5.list14;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * 获取反射值
 * @author zenghh, 625111833@qq.com
 * @date 2019-12-09 16:50
 * @version 1.0.0
 */
public class ObjectAnalyzerTest {
    public static void main(String[] args) {
        final int five = 5;

        ArrayList<Integer> squares = new ArrayList<>();
        for (int i = 0; i < five; i++) {
            squares.add(i*i);
        }
        System.out.println(new ObjectAnalyzer().toString(squares));
    }
}

class ObjectAnalyzer{

    private ArrayList<Object> visited = new ArrayList<>();
    /**
     * Converts an object to a string representation that lists all fields.
     * @param object an object
     * @return a string with the object's name and all field names and values
     */
    public String toString(Object object){
        if (object == null){
            return "null";
        }
        if (visited.contains(object)){
            return "...";
        }
        visited.add(object);
        Class clazz = object.getClass();
        if (clazz == String.class) {
            return (String) object;
        }

        if (clazz.isArray()){
            StringBuilder r = new StringBuilder(clazz.getComponentType() + "[]{");
            for (int i = 0 ; i < Array.getLength(object);i++){
                if ( i > 0){
                    r.append(", ");
                }
                Object value = Array.get(object,i);
                if (clazz.getComponentType().isPrimitive() ){
                    r.append(value);
                }else {
                    r.append(toString(value));
                }
            }
            return r.append("}").toString();
        }

        StringBuilder r = new StringBuilder(clazz.getName());
        //inspect the fields of this class and all superclasses
        do{
            r.append("[");
            Field[] fields = clazz.getDeclaredFields();
            AccessibleObject.setAccessible(fields,true);
            //get the names and value of all fields
            for (Field field : fields){
                if (!Modifier.isStatic(field.getModifiers())){
                    if (!r.toString().endsWith("[")){
                        r.append(",");
                    }
                    r.append(field.getName()).append("=");
                    try {
                        Object val = field.get(object);
                        Class type = field.getType();
                        if (type.isPrimitive()){
                            r.append(val);
                        }else {
                            r.append(toString(val));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            r.append("]");
            clazz = clazz.getSuperclass();
        } while (clazz!=null);

        return r.toString();


    }
}
