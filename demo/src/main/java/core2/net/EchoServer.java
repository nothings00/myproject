package core2.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/8/28 4:34 PM
 * @version 1.0
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        //establish server socket
        try(ServerSocket serverSocket = new ServerSocket(8189)){
            try (Socket incoming = serverSocket.accept()){
                // wait for client connection
                InputStream in = incoming.getInputStream();
                OutputStream out = incoming.getOutputStream();
                try (Scanner inS = new Scanner(in,StandardCharsets.UTF_8.name())){
                    PrintWriter outP = new PrintWriter(new OutputStreamWriter(out,StandardCharsets.UTF_8.name()),true);
                    outP.println("Hello! Enter quit to exit");
                    // echo client input
                    boolean done = false;
                    while (!done && inS.hasNextLine()) {
                        String line = inS.nextLine();
                        System.out.println("received: " + line);
                        outP.println("Echo: " + line);
                        if ("quit".equalsIgnoreCase(line.trim())) {
                            done = true;
                            in.close();
                            out.close();
                            incoming.close();
                            serverSocket.close();
                            System.out.println("server closed...");
                        }
                    }
                }
            }
        }
    }
}

