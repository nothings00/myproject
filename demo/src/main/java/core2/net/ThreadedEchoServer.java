package core2.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/8/28 5:06 PM
 * @version 1.0
 */
public class ThreadedEchoServer {
    public static void main(String[] args) throws IOException {
        try(ServerSocket s = new ServerSocket(8189)){
            int i = 1;
            while (true){
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedEchoHandler(incoming,i);
                Thread t = new Thread(r);
                t.start();;
                i++;
            }
        }
    }
}

class ThreadedEchoHandler implements Runnable{

    private Socket incoming ;
    private int i;

    public ThreadedEchoHandler(Socket incoming,int i) {
        this.incoming = incoming;
        this.i = i;
    }

    @Override
    public void run() {
        try (InputStream in = incoming.getInputStream();
             OutputStream out = incoming.getOutputStream()){
            Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name());
            PrintWriter writer = new PrintWriter(out,true);
            writer.println("Hello! Enter QUIT to exit.");

            boolean done = false;
            while(!done && scanner.hasNextLine()){
                String line = scanner.nextLine();
                writer.println("Echo: " + line);
                System.out.println("receive: " + line + ",from: " + i );
                if ("quit".equalsIgnoreCase(line.trim())){
                    done = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
