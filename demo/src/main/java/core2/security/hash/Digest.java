package core2.security.hash;

import org.apache.tomcat.util.buf.HexUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This program computes the message digest of a file.
 * algorithm SHA-1,SHA-256,MD5
 *
 * @author zenghh
 * @version 1.0
 * @date 2020/11/25 11:16 AM
 */
public class Digest {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String algname = "SHA-256";
        MessageDigest alg = MessageDigest.getInstance(algname);
        byte[] input = Files.readAllBytes(Paths.get(Digest.class.getResource("/core2/security/hash/Digest.class").getPath()));
        byte[] hash = alg.digest(input);
        String d = "";
        for (int i = 0; i < hash.length; i++) {
            int v = hash[i] & 0xFF;
            if (v < 16) {
                d += "0";
            }
            d += Integer.toString(v, 16).toUpperCase() + " ";
        }
        System.out.println(d);
        MessageDigest alg2 = MessageDigest.getInstance(algname);
        alg2.update(input);
        System.out.println(HexUtils.toHexString(alg2.digest()).toUpperCase());
    }
}
