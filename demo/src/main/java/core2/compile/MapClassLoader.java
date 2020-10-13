package core2.compile;

import java.util.Map;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/10/13 3:18 PM
 * @version 1.0
 */
public class MapClassLoader extends ClassLoader{
    private Map<String,byte[]> classes;
    public MapClassLoader(Map<String,byte[]> classes){
        this.classes = classes;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = classes.get(name);
        if (classBytes == null){
            throw new ClassNotFoundException(name);
        }
        Class<?> cl = defineClass(name,classBytes,0,classBytes.length);
        if (cl ==null){
            throw new  ClassNotFoundException(name);
        }
        return cl;
    }
}
