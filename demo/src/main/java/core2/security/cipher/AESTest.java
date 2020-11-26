package core2.security.cipher;

import org.apache.tomcat.util.buf.HexUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Advanced Encrypt Standard
 * @author zenghh
 * @date 2020/11/26 12:00 AM
 * @version 1.0
 */
public class AESTest {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, ClassNotFoundException, InvalidKeyException, BadPaddingException, ShortBufferException, IllegalBlockSizeException {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("secret.key"));){
            // 1.为加密算法获取密钥生成器
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            // 2.随机源初始化密钥生成器，如果密码长度是可变的，还要指定期望密码块长度
            SecureRandom random = new SecureRandom();
            keygen.init(random);
            SecretKey key  = keygen.generateKey();
            System.out.println(HexUtils.toHexString(key.getEncoded()).toUpperCase()+":"+key.getAlgorithm()+":"+key.getFormat());
            out.writeObject(key);
        }

        try(ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream("secret.key"));
            InputStream in = new FileInputStream(AESTest.class.getResource("AESTest.class").getFile());
            OutputStream out = new FileOutputStream(AESTest.class.getResource("/security/cipher/AESTest.aes").getFile());){
            Key sKey = (Key) keyIn.readObject();
            Cipher aes = Cipher.getInstance("AES",new BouncyCastleProvider());
            int mode  = Cipher.ENCRYPT_MODE;
            aes.init(mode,sKey);
            Util.crypt(in,out,aes);
        }


        //在target里看结果
        try(ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream("secret.key"));
            InputStream in = new FileInputStream(AESTest.class.getResource("/security/cipher/AESTest.aes").getFile());
            OutputStream out = new FileOutputStream(AESTest.class.getResource("/security/cipher/AESTest.class").getFile());) {
            Key sKey = (Key) keyIn.readObject();
            Cipher deAes = Cipher.getInstance("AES",new BouncyCastleProvider());
            deAes.init(Cipher.DECRYPT_MODE,sKey);
            Util.crypt(in,out,deAes);
        };



    }
}
