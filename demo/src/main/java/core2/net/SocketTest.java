package core2.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/8/28 4:00 PM
 * @version 1.0
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        try(Socket s = new Socket("time-a.nist.gov",13);
            Scanner in = new Scanner(s.getInputStream(), String.valueOf(StandardCharsets.UTF_8))){
            while (in.hasNextLine()){
                String line =in.nextLine();
                System.out.println(line);
            }
        }
    }
}
