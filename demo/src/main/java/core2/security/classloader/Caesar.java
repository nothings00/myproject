package core2.security.classloader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 加密类
 * args[0] in 输入文件
 * args[1] out 输出文件.caesar
 * args[2] key 加密关键字
 * @author zenghh
 * @version 1.0
 * @date 2020/11/3 3:56 PM
 */
public class Caesar {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("USAGE: java core2.security.classLoader.Caesar in out key");
            return;
        }
        try (FileInputStream in = new FileInputStream(args[0]);
             FileOutputStream out = new FileOutputStream(args[1])) {
            int key = Integer.parseInt(args[2]);
            int ch;
            while ((ch = in.read()) != -1) {
                byte c = (byte) (ch +key);
                out.write(c);
            }
        }
    }
}
