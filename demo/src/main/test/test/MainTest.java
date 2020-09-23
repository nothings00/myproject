package test;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

/**
 * 执行各种临时语句
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/9/21 3:30 PM
 * @version 1.0
 */
public class MainTest {
    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        System.out.println(locale);
        NumberFormat instance = NumberFormat.getCurrencyInstance(Locale.CHINA);
        String s = instance.format(123456789.1234);
        System.out.println(s);
    }
}
