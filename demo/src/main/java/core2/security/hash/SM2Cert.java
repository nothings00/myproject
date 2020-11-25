package core2.security.hash;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.misc.NetscapeCertType;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.X509KeyUsage;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.encoders.Base64;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * SM2数字证书
 * @author zenghh
 * @date 2020/11/25 4:18 PM
 * @version 1.0
 */
public class SM2Cert {
    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, OperatorCreationException, IOException, CertificateException {
        //1.生成自签名公私钥对
        KeyPair keyPair = SM2KeyPair.generate();
        //2.证书签名算法提供者
        //在制作证书时需要使用到签名算法签名证书中部分数据区域，国密类型的数字证书使用的签名算法是SM3withSM2，这里使用私钥创建算法提供容器
        ContentSigner signer = new JcaContentSignerBuilder("SM3withSM2")
                .setProvider(new BouncyCastleProvider())
                .build(keyPair.getPrivate());
        //3.设置证书信息
        X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(
                //颁发者信息
                createStdBuilder().build()
                //证书序列号
                , BigInteger.valueOf(1)
                //证书生效日期
                ,new Date(System.currentTimeMillis() - 50 * 1000)
                //证书失效日期
                ,new Date(System.currentTimeMillis() + 50 * 1000)
                //使用者信息（由于是自签证书，所以颁发者和使用者DN都相同）
                ,createStdBuilder().build()
                //证书公钥
                ,keyPair.getPublic())
                /*
                  设置证书扩展
                  证书扩展属性，根据需求设定，参数请参考 《RFC5280》
                  */
                //设置密钥用法
                .addExtension(Extension.keyUsage,false,new X509KeyUsage(X509KeyUsage.digitalSignature | X509KeyUsage.nonRepudiation))
                //设置扩展密钥用法：客户端身份认证、安全电子邮件
                .addExtension(Extension.extendedKeyUsage,false,extendedKeyUsage())
                //基础约束，标识是否是CA证书，这里false标识为实体证书
                .addExtension(Extension.basicConstraints,false,new BasicConstraints(false))
                //Netscape Cert Type SSL客户端身份认证
                .addExtension(MiscObjectIdentifiers.netscapeCertType,false,new NetscapeCertType(NetscapeCertType.sslClient));
        //4. X.509格式证书对象生成
        X509Certificate certificate = new JcaX509CertificateConverter()
                .setProvider(new BouncyCastleProvider())
                .getCertificate(certGen.build(signer));
        //5. 保存证书
        makeCertFile(certificate, Paths.get("test-cert.cer"));

    }

    /**
     * 标识信息构造（DN,Distinct Name）
     * 设置证书的基本数据：使用者信息、颁发者信息、证书序号、证书生效日期、证书失效日期，以及证书扩展属性。
     * 这里的键是一个ASN1ObjectIdentifier，实际上Bouncycastle已经为我们把需要的大多键都已经列好了，我们只要使用这个类org.bouncycastle.asn1.x500.style.BCStyle的静态变量就可以。
     * @return
     */
    private static X500NameBuilder createStdBuilder(){
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        // 国家代码
        builder.addRDN(BCStyle.C,"CN");
        // 组织
        builder.addRDN(BCStyle.O,"COM.NOTHINGS");
        // 省份
        builder.addRDN(BCStyle.ST,"Fujian");
        // 地区
        builder.addRDN(BCStyle.L,"Xiamen");
        return builder;
    }

    /**
     * 扩展密钥用途（可选）
     * 如果需要设置证书的扩展密钥用途，可以使用DERSequence来构造一个拓展密钥用途的序列。
     * @return
     */
    public static DERSequence extendedKeyUsage(){
        // 构造容器对象
        ASN1EncodableVector vector = new ASN1EncodableVector();
        // 客户端身份认证
        vector.add(KeyPurposeId.id_kp_clientAuth);
        // 安全电子邮件
        vector.add(KeyPurposeId.id_kp_emailProtection);

        return new DERSequence(vector);
    }

    public static void makeCertFile(X509Certificate x509Certificate, Path savePath) throws IOException, CertificateEncodingException {
        if (Files.exists(savePath)){
            //删除已存在文件
            Files.deleteIfExists(savePath);
        }
        //创建文件
        Files.createFile(savePath);
        //获取ASN.1编码的证书字节码
        byte[] asn1BinCert = x509Certificate.getEncoded();
        //编码为Base64，便于传输
        byte[] base64EncodedCert = Base64.encode(asn1BinCert);
        //写入文件
        Files.write(savePath,base64EncodedCert);
    }
}
