package core.chapter3.list7;

import java.util.Scanner;

/**
 * demonstrates console input
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-11 10:25
 * @version 1.0.0
 */
public class InputTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //get first input
        System.out.print("What's your name?");
        String name = scanner.nextLine();

        //get second input
        System.out.print("How old are you?");
        int age = scanner.nextInt();

        //display output on console
        System.out.println("Hello,"+name+".Next year, you'll be "+(age+1));
        scanner.close();

        //TODO： console为空指针，原因不明
//        Console console = System.console();
//        String username = console.readLine("User name: ");
//        char[] pwd = console.readPassword("Password: ");
//        System.out.println("name:"+username+",password:"+pwd);
    }
}
