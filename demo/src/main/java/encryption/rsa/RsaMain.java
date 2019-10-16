package encryption.rsa;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 加密算法RSA
 * @author zenghh, 625111833@qq.com
 * @date 2019-10-14 18:17
 * @version 1.0.0
 */
public class RsaMain {
    /**
     * * RSA加密对明文的长度有所限制，规定需加密的明文最大长度=密钥长度-11（单位是字节，即byte），
     * 所以在加密和解密的过程中需要分块进行.而密钥默认是1024位，即1024位/8位-11=128-11=117字节。
     * 所以默认加密前的明文最大长度117字节，解密密文最大长度为128字。
     * 那么为啥两者相差11字节呢？
     * 是因为RSA加密使用到了填充模式（padding），即内容不足117字节时会自动填满，用到填充模式自然会占用一定的字节，
     * 而且这部分字节也是参与加密的。
     */
    /**
     * RSA最大加密明文大小
     */
    private static final Integer MAX_ENCRYPT_BLOCK= 117;

    /**
     * RSA最大解密密文大小
     */
    private static final Integer MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取秘钥对
     * @return 秘钥对
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //秘钥长度设置 1024/8 -11 = 128 - 11 = 117 字节
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 获取私钥
     * @param privateKey 私钥字符串
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     * @param publicKey 公钥字符串
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedeKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decodedeKey);
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    /**
     * RSA 公钥加密
     * @param data 待加密数据
     * @param publicKey 公钥
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IOException
     */
    public static String encrypt(String data,PublicKey publicKey) throws NoSuchPaddingException
            , NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
            , IOException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        //对数据进行分段加密
        while (inputLen - offset > 0){
                if (inputLen - offset > MAX_ENCRYPT_BLOCK){
                    cache = cipher.doFinal(data.getBytes(),offset,MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data.getBytes(),offset,inputLen - offset);
                }
                bos.write(cache,0,cache.length);
                i++;
                offset  = i * MAX_ENCRYPT_BLOCK;
        }
        byte[]  encryptData = bos.toByteArray();
        bos.close();
        // 获取加密内容使用base64进行编码，并以UTF-8位标准转化成字符串
        return new String(Base64.encodeBase64String(encryptData));
    }

    /**
     * RSA解密
     * @param data 待解密数据
     * @param privateKey 私钥
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IOException
     */
    public static String decrypt(String data,PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        //对数据进行分段解密
        while (inputLen - offset > 0){
            if (inputLen - offset > MAX_DECRYPT_BLOCK){
                cache = cipher.doFinal(dataBytes,offset,MAX_DECRYPT_BLOCK);
            }else {
                cache = cipher.doFinal(dataBytes,offset,inputLen - offset);
            }
            bos.write(cache,0,cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = bos.toByteArray();
        bos.close();
        return new String(decryptedData,"UTF-8");
    }

    /**
     * 签名
     * @param data 待签名数据
     * @param privateKey 私钥
     * @return 签名
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static String sign(String data,PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, SignatureException {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        // Signature.static里获取签名类型
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     * @param srcData 原始字符串
     * @param publicKey 公钥
     * @param sign 签名
     * @return 是否验签通过
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verify(String srcData,PublicKey publicKey,String sign) throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, SignatureException {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));

    }
    public static void main(String[] args) {

        KeyPair keyPair = null;
        String privateKey =null;
        String publicKey = null;
        try {
            //生成秘钥对
            keyPair = getKeyPair();
            privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
            publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
            System.out.println("私钥："+privateKey+",长度："+privateKey.length());
            System.out.println("公钥："+publicKey+",长度："+publicKey.length());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("秘钥对生成异常");
        }

        String data ="待加密数据待加密数据,待加密数据'待加密数据l待加密数据3待加密数据^$%待加密数据待加密数据待加密数据待加密数据待加密数据1"
                +"待加密数据待加密数据,待加密数据'待加密数据l待加密数据3待加密数据^$%待加密数据待加密数据待加密数据待加密数据待加密数据2"
                +"待加密数据待加密数据,待加密数据'待加密数据l待加密数据3待加密数据^$%待加密数据待加密数据待加密数据待加密数据待加密数据3";
        String encryptData = null;
        System.out.println("加密前内容："+data+"，长度："+data.length());
        try {
            //数据加密
            encryptData =  encrypt(data,getPublicKey(publicKey));
            System.out.println("加密后内容："+encryptData);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("加密时异常");
        }

        try {
            //数据解密
            String decryptData = decrypt(encryptData,getPrivateKey(privateKey));
            System.out.println("解密后内容："+decryptData);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解密时异常");
        }

        String sign= null;
        try {
            //RSA签名
            sign = sign(data,getPrivateKey(privateKey));
            System.out.println("数据："+data+",RSA签名："+sign);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("RSA签名时异常");
        }

        //RSA验签
        try {
            boolean result =verify(data,getPublicKey(publicKey),sign);
            System.out.println("数据："+data+",验签结果："+result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("RSA验签时异常");
        }
    }
}
