package core2.security.hash;

import org.apache.tomcat.util.buf.HexUtils;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

/**
 * sm2密钥对，支持数字签名和验签
 * @author zenghh
 * @date 2020/11/25 3:15 PM
 * @version 1.0
 */
public class SM2KeyPair {
    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        KeyPair keyPair = generate();
        System.out.println(HexUtils.toHexString(keyPair.getPrivate().getEncoded()));
        System.out.println(keyPair.getPublic().getEncoded());
        String text= "Hello World.I'm coming中国";
        byte[] sign = sign(text.getBytes(), keyPair.getPrivate());
        System.out.println(sign.length +":"+HexUtils.toHexString(sign));
        boolean verify = verify(text.getBytes(), sign, keyPair.getPublic());
        System.out.println(verify);


    }

    /**
     * 密钥对生成
     * @return 密钥对
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    public static KeyPair generate() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        //获取SM2椭圆曲线的参数
        final ECGenParameterSpec sm2Sp = new ECGenParameterSpec("sm2p256v1");
        //获取一个椭圆曲线类型的密钥对生成器
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC",new BouncyCastleProvider());
        // 使用SM2参数初始化生成器
        keyPairGenerator.initialize(sm2Sp);
        //使用SM2的算法区域初始化迷药生成器
        keyPairGenerator.initialize(sm2Sp,new SecureRandom());
        //获取密钥对
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 数字签名
     * @param plainText 原文
     * @param privateKey 私钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static byte[] sign(byte[] plainText,PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        //生成SM2sign with SM3 签名验签算法实例
        Signature signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(),new BouncyCastleProvider());
        //签名需要使用私钥，使用私钥，初始化签名实例
        signature.initSign(privateKey);
        //签名原文写入算法中
        signature.update(plainText);
        //计算签名值
        return  signature.sign();
    }

    /**
     * 验签
     * @param originText 原文
     * @param signText 加签后的密文
     * @param publicKey 公钥
     * @return 是否验证通过
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verify(byte[] originText, byte[] signText, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        //生成SM2sign with SM3 签名验签算法实例
        Signature signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(),new BouncyCastleProvider());
        //验签需要使用公钥，使用公钥，初始化签名实例
        signature.initVerify(publicKey);
        //写入待验证的签名原文到算法中
        signature.update(originText);
        return signature.verify(signText);
    }

}
