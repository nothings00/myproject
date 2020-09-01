package core2.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/8/28 4:41 PM
 * @version 1.0
 */
public class ClientSocket {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",8189),60*1000);

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name());
        while (socket.isConnected()){
            if (socket.isClosed()){
                break;
            }

            if (scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
            PrintWriter printWriter = new PrintWriter(out,true);
            printWriter.println(new Scanner(System.in).nextLine());
        }
        in.close();
        out.close();
        socket.close();
        System.out.println("server connect closed...");
    }
}
