package core2.datetime;

import org.apache.tomcat.jni.Local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * @author zenghh
 * @version 1.0
 * @email 625111833@qq.com
 * @date 2020/9/17 11:24 AM
 */
public class LocalDates {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("today: " + today);

        LocalDate alonzosBirthday = LocalDate.of(1903, 6, 14);
        alonzosBirthday = LocalDate.of(1903, Month.JUNE, 14);
        System.out.println("alonzosBirthday: " + alonzosBirthday);

        LocalDate programmersDay = LocalDate.of(2020,1,1).plusDays(255);
        System.out.println("programmersDay: " + programmersDay);

        LocalDate independenceDay = LocalDate.of(2020,Month.JULY,4);
        LocalDate christmas = LocalDate.of(2020,Month.DECEMBER,25);

        System.out.println("Until christmas: " + independenceDay.until(christmas));
        System.out.println("Until christmas: " + independenceDay.until(christmas, ChronoUnit.DAYS));

        System.out.println(LocalDate.of(2016,1,31).plusMonths(1));
        System.out.println(LocalDate.of(2016,3,31).minusMonths(1));

        DayOfWeek startOfLastMillennium = LocalDate.of(1900,1,1).getDayOfWeek();
        System.out.println("startOfLastMillennium: "+ startOfLastMillennium);
        System.out.println(startOfLastMillennium.getValue());

        System.out.println(DayOfWeek.SATURDAY.plus(3));
    }
}
