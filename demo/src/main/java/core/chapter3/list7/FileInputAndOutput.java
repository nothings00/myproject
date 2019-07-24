package core.chapter3.list7;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-11 10:54
 * @version 1.0.0
 */
public class FileInputAndOutput {
    //show current time from file
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        PrintWriter printWriter = new PrintWriter("today.txt", "UTF-8");
        printWriter.printf("%1$tF %1$tT",new Date());
        printWriter.close();

        Scanner scanner = new Scanner(Paths.get("today.txt"),"UTF-8");
        System.out.println(scanner.nextLine());
        scanner.close();

    }
}
