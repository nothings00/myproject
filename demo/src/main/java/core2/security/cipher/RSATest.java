package core2.security.cipher;

import org.apache.tomcat.util.buf.HexUtils;

import javax.crypto.*;
import java.io.*;
import java.security.*;

/**
 *
 * @author zenghh
 * @date 2020/11/26 1:08 AM
 * @version 1.0
 */
public class RSATest {
    private static final int KEY_SIZE = 2048;
    private static final String ORIGIN = RSATest.class.getResource("RSATest.class").getPath();
    private static final String CRYPT = RSATest.class.getResource("/security/cipher/RSATest.rsa").getPath();
    private static final String DECRYPT =RSATest.class.getResource( "/security/cipher/RSATest.class").getPath();
    private static final String PUBLIC_KEY = RSATest.class.getResource("/security/cipher/rsa.public").getPath();
    private static final String PRIVATE_KEY = RSATest.class.getResource("/security/cipher/rsa.private").getPath();
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, ShortBufferException, InvalidKeyException, ClassNotFoundException {
//        generateKeyPair();
        encrypt();
        decrypt();



    }

    public static void generateKeyPair() throws IOException, NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom();
        keyPairGenerator.initialize(KEY_SIZE,secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY))){
            out.writeObject(keyPair.getPublic());
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY))){
            out.writeObject(keyPair.getPrivate());
        }
    }

    public static void encrypt() throws NoSuchAlgorithmException, IOException, ClassNotFoundException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, ShortBufferException, BadPaddingException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(secureRandom);
        SecretKey key = keyGenerator.generateKey();
        //RSA公钥加密,密钥，加密文件，源文件
        try(ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(PUBLIC_KEY));
            DataOutputStream out = new DataOutputStream(new FileOutputStream(CRYPT));
            InputStream in = new FileInputStream(ORIGIN)){
            //公钥加密密钥
            Key publicKey = (Key) keyIn.readObject();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.WRAP_MODE,publicKey);
            System.out.println(HexUtils.toHexString(key.getEncoded()));
            byte[] warpKey = cipher.wrap(key);
            out.writeInt(warpKey.length);
            out.write(warpKey);

            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            //AES加密源文件，out 含 加密完密钥length，被加密对称密钥，被加密原文
            Util.crypt(in,out,cipher);
        }
    }

    public static void decrypt() throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, ShortBufferException, IllegalBlockSizeException, ClassNotFoundException {
        try(DataInputStream in = new DataInputStream(new FileInputStream(CRYPT));
            ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(PRIVATE_KEY));
            OutputStream outputStream = new FileOutputStream(DECRYPT)){

            //获取被加密后密钥长度
            int length = in.readInt();
            byte[] warppedKey = new byte[length];
            //读取被加密密钥
            in.read(warppedKey,0,length);

            //私钥密钥
            Key privateKey = (Key) keyIn.readObject();

            //解密密钥
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.UNWRAP_MODE,privateKey);
            Key key = cipher.unwrap(warppedKey,"AES" ,Cipher.SECRET_KEY );
            System.out.println(HexUtils.toHexString(key.getEncoded()));

            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,key);

            //AES解密密文
            Util.crypt(in,outputStream,cipher);
        }
    }
}
