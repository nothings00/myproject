package core2.stream.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/7/6 4:12 下午
 * @version 1.0
 */
public class CountLongWords {
    public static void main(String[] args) throws IOException {
        String counts = new String(Files.readAllBytes(Paths.get("./pom.xml")), StandardCharsets.UTF_8);
        //
        List<String> words = Arrays.asList(counts.split("\\PL+"));

        long count = 0;
        for (String word:words){
            if (word.length() > 12){
                count++;
            }
        }
        System.out.println(count);

        Predicate<String> countWords = (w) ->{
            if (w.length() > 12){
                System.out.println(w);
                return true;
            }
            return false;
        } ;
        count = words.stream().filter(countWords).count();
        System.out.println(count);

        count = words.parallelStream().filter(countWords).count();
        System.out.println(count);

    }
}
