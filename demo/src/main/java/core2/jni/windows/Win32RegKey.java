package core2.jni.windows;

import java.util.Enumeration;

/**
 * A Win32RegKey Object can be used to get and set values of a registry key in the windows registry.
 * @author zenghh
 * @date 2020/12/1 3:56 PM
 * @version 1.0
 */
public class Win32RegKey {
    static {
        System.loadLibrary("Win32RegKey");
    }
    public static final int HKEY_CLASSES_ROOT =0x80000000;
    public static final int HKEY_CURRENT_USER =0x80000001;
    public static final int HKEY_LOCAL_MACHINE =0x80000002;
    public static final int HKEY_USERS =0x80000003;
    public static final int HKEY_CURRENT_CONFIG =0x80000004;
    public static final int HKEY_DYN_DATA =0x80000005;

    private int root;
    private String path;

    /**
     * gets the value of a registry entry.
     * @param name the entry name
     * @return the associated value
     */
    public native Object getValue(String name);

    /**
     * sets the value of a registry entry;
     * @param name the entry name
     * @param value the new value
     */
    public native void setValue(String name,Object value);

    public Win32RegKey(int root, String path) {
        this.root = root;
        this.path = path;
    }

    /**
     * Enumerates all names of registry entries under the path that this object describes.
     * @return an enumeration listing all entry names.
     */
    public Enumeration<String> names(){
            return new Win32RegKeyNameEnumeration(root,path);
    }

}

class Win32RegKeyNameEnumeration implements Enumeration<String>{
    @Override
    public native String nextElement();
    @Override
    public native boolean hasMoreElements();

    private int root;
    private String path;
    private int index = -1;
    private int hkey = 0;
    private int maxsize;
    private int count;

    public Win32RegKeyNameEnumeration(int root, String path) {
        this.root = root;
        this.path = path;
    }
}

class Win32RegKeyException extends RuntimeException{
    public Win32RegKeyException() {

    }

    public Win32RegKeyException(String message) {
        super(message);
    }
}
