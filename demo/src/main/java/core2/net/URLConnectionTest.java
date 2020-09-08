package core2.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 从服务器读取数据
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/9/3 10:08 AM
 * @version 1.0
 */
public class URLConnectionTest {
    public static void main(String[] args) {
        try {
            String urlName;
            if (args.length > 0){
                urlName = args[0];
            }else {
                urlName = "http://horstmann.com";
            }

            URL url = new URL(urlName);
            URLConnection connection = url.openConnection();

            //set username,password if specified on command line

            if (args.length >2){
                String username = args[1];
                String password = args[2];
                String input = username + ":" + password;
                Base64.Encoder encoder = Base64.getEncoder();
                String encoding = encoder.encodeToString(input.getBytes(StandardCharsets.UTF_8));
                connection.setRequestProperty("Authorization","Basic "+encoding);
            }

            connection.connect();

            //print header fields
            Map<String, List<String>> headers = connection.getHeaderFields();
            for (Map.Entry<String,List<String>> entry : headers.entrySet()){
                String key = entry.getKey();
                for (String value : entry.getValue()){
                    System.out.println(key + ": " + value);
                }
            }

            long timeStamp = connection.getDate();
            LocalDateTime date = LocalDateTime.ofEpochSecond(timeStamp/1000,0, ZoneOffset.ofHours(8));
            //print convenience functions
            System.out.println("----------");
            System.out.println("getContentType: " + connection.getContentType());
            System.out.println("getContentLength: " + connection.getContentLength());
            System.out.println("getContentEncoding: " + connection.getContentEncoding());
            System.out.println("getDate: "+ date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            System.out.println("getExpiration: " + connection.getExpiration());
            System.out.println("getLastModified: " + connection.getLastModified());
            System.out.println("getConnectTimeout: " + connection.getConnectTimeout());
            System.out.println("getReadTimeout: " + connection.getReadTimeout());
            System.out.println("----------");

            String encoding = connection.getContentEncoding();
            if (encoding == null){
                encoding = StandardCharsets.UTF_8.name();
            }
            try (Scanner in = new Scanner(connection.getInputStream(),encoding)){
                //print first ten lines of contents
                for (int i = 1; i < 10 & in.hasNextLine(); i++) {
                    System.out.println(in.nextLine());
                }
                if (in.hasNextLine()){
                    System.out.println(". . .");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
