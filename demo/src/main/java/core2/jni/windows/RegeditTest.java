package core2.jni.windows;

import java.util.Enumeration;

/**
 * 访问Windows注册表
 * @author zenghh
 * @date 2020/11/30 11:40 AM
 * @version 1.0
 */
public class RegeditTest {
    public static void main(String[] args) {
        Win32RegKey key =  new Win32RegKey(Win32RegKey.HKEY_CURRENT_USER,"Software\\JavaSoft\\Java Runtime Environment");
        key.setValue("Default user","Harry Hacker");
        key.setValue("Lucky number",new Integer(13));
        key.setValue("Small primes",new byte[]{2,3,5,7,11});

        Enumeration<String> e = key.names();
        while (e.hasMoreElements()){
            String name =  e.nextElement();
            System.out.print(name + "=");

            Object value = key.getValue(name);

            if (value instanceof byte[]){
                for (byte b : (byte[]) value){
                    System.out.print((b & 0xFF) + " ");
                }
            }else {
                System.out.print(value);
            }

            System.out.println();
        }

    }
}
