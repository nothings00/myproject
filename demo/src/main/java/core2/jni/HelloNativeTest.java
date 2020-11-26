package core2.jni;

/**
 * 本地方法测试
 * @author zenghh
 * @date 2020/11/26 3:08 PM
 * @version 1.0
 */
public class HelloNativeTest {
    static {
        String path = HelloNativeTest.class.getResource("/").getPath();
        System.setProperty("java.library.path",path);
        //mac是libXXX.jinlib,linux是libXXX.so
//        System.loadLibrary("HelloNative");
        System.load(path+"libHelloNative.jnilib");
    }
    public static void main(String[] args) {
        HelloNative.greeting();
    }
}
