package core2.javaSe8.optional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/7/8 3:31 PM
 * @version 1.0
 */
public class OptionalTest {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("./pom.xml")), StandardCharsets.UTF_8);
        //非字符分割
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));

        Optional<String> optionalValue = wordList.stream()
                .filter( s -> s.contains("spring"))
                .findFirst();

        System.out.println(optionalValue.orElse("No Word") + " contains spring");

        Optional<String> optionalS = Optional.empty();
        String result = optionalS.orElse("N/A");
        System.out.println("result: " + result);

        result = optionalS.orElseGet(()-> Locale.getDefault().getDisplayName());
        System.out.println("result: " + result);

        try {
            result = optionalS.orElseThrow(IllegalAccessError::new);
            System.out.println("result: " + result);
        }catch (Throwable t){
            t.printStackTrace();
        }

        optionalS = wordList.stream()
                .filter(s -> s.contains("word"))
                .findFirst();
        optionalS.ifPresent( s -> System.out.println( s + "contains word"));

        Set<String> results = new HashSet<>();
        optionalS.ifPresent(results::add);
        Optional<Boolean> added  = optionalS.map(results::add);
        System.out.println(added);

        System.out.println(inverse(4.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(-1.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(0.0).flatMap(OptionalTest::squareRoot));

        Optional<Double> result2 = Optional.of(-4.0).flatMap(OptionalTest::inverse).flatMap(OptionalTest::squareRoot);
        System.out.println(result2);

        Collectors.toList();
    }

    public static Optional<Double> inverse(Double x){
        return x == 0 ? Optional.empty() : Optional.of( 1 / x );
    }

    public static Optional<Double> squareRoot(Double x){
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
