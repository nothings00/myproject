package core2.compile;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/10/13 2:05 PM
 * @version 1.0
 */
public class ByteArrayJavaClass extends SimpleJavaFileObject {
    private ByteArrayOutputStream stream;

    public ByteArrayJavaClass(String name){
        super(URI.create("bytes:///" + name),Kind.CLASS);
        stream = new ByteArrayOutputStream();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return  stream;
    }

    public byte[] getBytes(){
        return stream.toByteArray();
    }
}
