package core2.security.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author zenghh
 * @version 1.0
 * @date 2020/11/26 12:15 AM
 */
public class Util {
    /**
     * Use a Cipher to transform the bytes in an input stream and sends the transformed bytes to an output stream
     * @param in
     * @param out
     * @param cipher
     * @throws IOException
     * @throws ShortBufferException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static void crypt(InputStream in, OutputStream out, Cipher cipher) throws IOException, ShortBufferException, BadPaddingException, IllegalBlockSizeException {
        int blockSize = cipher.getBlockSize();
        int outputSize = cipher.getOutputSize(blockSize);
        byte[] inBytes = new byte[blockSize];
        byte[] outBytes = new byte[outputSize];

        int inLength = 0;
        boolean more = true;
        while (more){
            inLength = in.read(inBytes);
            if (inLength == blockSize){
                //加密输入的字节
                int outLength =  cipher.update(inBytes,0,blockSize,outBytes);
                //输出加密完字节
                out.write(outBytes,0,outLength);
            }else {
                more = false;
            }
        }
        //最后不足blockSize处理
        if (inLength > 0){
            outBytes = cipher.doFinal(inBytes,0,inLength);
        }else {
            outBytes = cipher.doFinal();
        }
        out.write(outBytes);
    }
}
