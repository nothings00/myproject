package design.pattern.behavioral.state.demo2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 简历状态
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-13 10:49
 * @version 1.0.0
 */
public class TCPEstablish extends TCPState {
    public TCPEstablish() {
        System.out.println("create a new establish state");
    }


    @Override
    public void close(TCPConnection connection) {
        System.out.println("tcp establish is closing");
    }

    @Override
    public void processInfo(TCPConnection connection, InputStream inputStream) throws IOException {
        System.out.println("tcp establish processInfo");
        byte[] buffer = new byte[1024];
        int n =0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ( (n = inputStream.read(buffer)) !=-1){
            bos.write(buffer,0,n);
        }
        bos.close();
        System.out.println("process info:"+ bos + "");
    }
}
