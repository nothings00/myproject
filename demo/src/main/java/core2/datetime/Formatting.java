package core2.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/9/21 3:09 PM
 * @version 1.0
 */
public class Formatting {
    public static void main(String[] args) {
        ZonedDateTime apollo11launch = ZonedDateTime.of(1969,7,16,
                9,32,0,0, ZoneId.of("America/New_York"));

        String formatted = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(apollo11launch);
        //1969-07-16T09:32:00-04:00
        System.out.println(formatted);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        formatted = formatter.format(apollo11launch);
        //July 16, 1969
        System.out.println(formatted);

        formatted = formatter.withLocale(Locale.CHINA).format(apollo11launch);
        //1969年7月16日
        System.out.println(formatted);

        formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
        formatted = formatter.format(apollo11launch);
        //Wed 1969-07-16 09:32
        System.out.println(formatted);

        LocalDate churchBirthday = LocalDate.parse("1903-06-14");
        //churchBirthday: 1903-06-14
        System.out.println("churchBirthday: " +churchBirthday);
        apollo11launch = ZonedDateTime.parse("1969-07-16 03:32:00-0400",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
        //apollo11launch: 1969-07-16T03:32-04:00
        System.out.println("apollo11launch: " + apollo11launch);

        //星期一 星期二 星期三 星期四 星期五 星期六 星期日
        for (DayOfWeek w :DayOfWeek.values()){
            System.out.print(w.getDisplayName(TextStyle.SHORT,Locale.CHINA)+" ");
        }
    }
}
