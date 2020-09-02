package core2.net.interruptible;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/9/1 10:11 AM
 * @version 1.0
 */
public class InterruptibleSocketTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame = new InterruptibleSocketFrame();
            frame.setTitle("InterruptibleSocketTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}


class InterruptibleSocketFrame extends JFrame{
    private Scanner in;
    private JButton interruptibleButton;
    private JButton blockingButton;
    private JButton cancelButton;
    private JTextArea messages;
    private Thread connectThread;
    private TestServer server;

    public InterruptibleSocketFrame() throws HeadlessException {
        JPanel northPanel = new JPanel();
        add(northPanel,BorderLayout.NORTH);

        final int TEXT_ROWS = 20;
        final int TEXT_COLUMNS = 60;

        messages = new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
        add(new JScrollPane(messages));

        interruptibleButton = new JButton("Interruptible");
        blockingButton = new JButton("Blocking");

        northPanel.add(interruptibleButton);
        northPanel.add(blockingButton);

        interruptibleButton.addActionListener(e -> {
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);

            connectThread = new Thread(()->{
                try {
                    connectInterrptibly();
                } catch (IOException ioException) {
                    messages.append("\nInterruptibleSocketTest.connectInterruptibly:" + e);
                }
            });

            connectThread.start();
        });

        blockingButton.addActionListener(e -> {
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread = new Thread(()->{
                try {
                    connectBlocking();
                } catch (IOException ioException) {
                    messages.append("\nInterruptibleSocketTest.connectBlocking: " + e);
                }
            });
            connectThread.start();
        });

        cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false);
        northPanel.add(cancelButton);

        cancelButton.addActionListener(e -> {
            connectThread.interrupt();
            cancelButton.setEnabled(false);
        });

        server = new TestServer();
        new Thread(server).start();
        pack();

    }

    public void connectInterrptibly() throws IOException {
        messages.append("Interruptible:\n");
        try(SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost",8189))){
            in = new Scanner(channel,StandardCharsets.UTF_8.name());
            while (!Thread.currentThread().isInterrupted()){
                messages.append("Reading： ");
                if (in.hasNextLine()){
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        }finally {
            EventQueue.invokeLater(()->{
                messages.append("Channel closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    public void connectBlocking() throws IOException {
        messages.append("Blocking:\n");
        try(Socket sock = new Socket("localhost",8189)){
            in = new Scanner(sock.getInputStream(),StandardCharsets.UTF_8.name());
            while (!Thread.currentThread().isInterrupted()){
                messages.append("Reading: ");
                if (in.hasNextLine()){
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        }finally {
            EventQueue.invokeLater(()->{
                messages.append("Socket closed \n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    /**
     * A multithreaded server that listens to port 8189 and sends numbers to the client,simulating a hanging server after 10 nubmers.
     */
    class TestServer implements Runnable{
        @Override
        public void run() {
            try {
                try(ServerSocket s = new ServerSocket(8189)){
                    while (true){
                        Socket incoming = s.accept();
                        Runnable r = new TestServerHandler(incoming);
                        Thread t = new Thread(r);
                        t.start();
                    }
                }
            } catch (IOException e) {
                messages.append("\nTestServer.run: " + e);
            }
        }
    }

    class TestServerHandler implements Runnable{
        private Socket incoming;
        private int counter;

        public TestServerHandler(Socket incoming) {
            this.incoming = incoming;
        }

        @Override
        public void run() {
            try{
                try {
                    OutputStream outStream = incoming.getOutputStream();
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, StandardCharsets.UTF_8.name()),true);
                    while(counter < 100){
                        counter++;
                        if (counter <= 10 ){
                            out.println(counter);
                        }
                        Thread.sleep(100);
                    }
                } finally {
                    incoming.close();
                    messages.append("Closing server "+Thread.currentThread().getId() +"\n");
                }
            }catch (Exception e) {
                messages.append("\n TestServerHandler.run: " + e);
            }
        }
    }

}


