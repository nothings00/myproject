package core2.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/8/28 4:19 PM
 * @version 1.0
 */
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localhostAddress = InetAddress.getLocalHost();
        System.out.println(localhostAddress);
    }
}
