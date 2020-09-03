package core2.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/9/2 3:44 PM
 * @version 1.0
 */
public class IpUtil {
    public static final Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
    public static String getInnerIp(){
        String innerIp = null;
        return innerIp;
    }

    public static String getIpv4() throws IOException {
        String ip = null;
        String url = "http://ip.chinaz.com";
        String read;
        StringBuilder inputLine = new StringBuilder();

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8.name()))){
            while ((read = in.readLine()) != null){
                inputLine.append(read + "\r\n");
            }
        }
        Matcher m  = p.matcher(inputLine.toString());
        if (m.find()){
            String ipstr = m.group(1);
            ip = ipstr;
        }

        return  ip;
    }

    //TODO：内外ip，外网ip获取
}
