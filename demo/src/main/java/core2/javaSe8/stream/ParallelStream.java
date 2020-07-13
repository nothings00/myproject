package core2.javaSe8.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/7/13 4:02 PM
 * @version 1.0
 */
public class ParallelStream {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("./pom.xml")), StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));


        // bad
        int[] shortWords = new int[10];
        wordList.parallelStream().forEach(s -> {
            if(s.length() < 10){
                shortWords[s.length()]++;
            }
        });
        System.out.println(Arrays.toString(shortWords));

        // bad
        Arrays.fill(shortWords,0);
        wordList.parallelStream().forEach(s -> {
            if (s.length() < 10){
                shortWords[s.length()]++;
            }
        });
        System.out.println(Arrays.toString(shortWords));

        Map<Integer,List<String>> result = wordList.parallelStream().collect(
                Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(14));

        result = wordList.parallelStream().collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(14));

        Map<Integer,Long> wordCounts = wordList.parallelStream().filter(s -> s.length() < 10).collect(
                Collectors.groupingByConcurrent(String::length,Collectors.counting()));
        System.out.println(wordCounts);

    }

}
