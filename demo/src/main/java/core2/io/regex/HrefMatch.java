package core2.io.regex;


import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/8/6 11:22 AM
 * @version 1.0
 */
public class HrefMatch {
    public static void main(String[] args)  {
        try {
            String urlString = "http://horstman.com";
            if (StringUtils.isEmpty(urlString)){
                urlString = "http://java.sun.com";
            }

            InputStreamReader in = new InputStreamReader(new URL(urlString).openStream(), StandardCharsets.UTF_8);

            StringBuilder input = new StringBuilder();
            int ch;
            while ((ch = in.read())!=-1){
                input.append((char) ch);
            }

            String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"[^\\s>]*)\\s*>";
            Pattern pattern = Pattern.compile(patternString,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()){
                String  match = matcher.group();
                System.out.println(match);
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
