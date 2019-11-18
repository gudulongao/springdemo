package demo.springdemo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;

public class DateTest {
    public static void testChronoFiled() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now: " + now);
        System.out.println(ChronoField.CLOCK_HOUR_OF_DAY + ": " + now.get(ChronoField.CLOCK_HOUR_OF_DAY));
        System.out.println(ChronoField.YEAR + ": " + now.get(ChronoField.YEAR));
        System.out.println(ChronoField.MONTH_OF_YEAR + ": " + now.get(ChronoField.MONTH_OF_YEAR));
    }

    public static void testTemporalAdjuster() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now: " + now);
        System.out.println(now.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(now.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY)));
    }

    public static void testFormatter() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now: " + now);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("G yyyy年MM月dd号 E a HH:mm:ss:SSS 今年的第D天 " +
                "今年的第w周 本月的第W周");
        System.out.println(now.format(dateTimeFormatter));
        TemporalAccessor parse = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse("2019-12-31");
        System.out.println(parse);
    }

    public static void testLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now: " + now);
        System.out.println("date: " + now.toLocalDate());
        System.out.println("time: " + now.toLocalTime());
        System.out.println(now.getYear() + "年" + now.getMonth().getValue() + "月" + now.getDayOfMonth()
                + "日");
        //毫秒数后补零
        System.out.println("nano: " + now.getNano());
        //当前事件将年替换成2020
        System.out.println("withyear: " + now.withYear(2020));
        //自定义日期
        System.out.println("of date: " + LocalDate.of(2022, 12, 25));
        //增加一个月
        System.out.println("plusmonth: " + now.plusMonths(1));
        //减少一个月
        System.out.println("minusmonth: " + now.minusMonths(1));
        //是否闰年
        System.out.println("isleapyear: " + now.toLocalDate().isLeapYear());
    }

    public static void testInstant() {
        Instant now = Instant.now();
        System.out.println("now: " + now);
        System.out.println("before: " + now.isBefore(now) + " same: " + now.compareTo(now) + " after: " + now.isAfter
                (now));
        System.out.println("before: " + now.compareTo(now.minus(Period.ofDays(1))) + " after: " + now.compareTo(now
                .plus(Period.ofDays(1))) + " same: " + now.compareTo(now));
        System.out.println("epochmilli: " + now.toEpochMilli());
        System.out.println("of: " + Instant.ofEpochMilli(System.currentTimeMillis()));

        //将instant转换成localdatetime
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, zoneId);
        System.out.println(localDateTime);

        //将localdatetime转换成instant
        System.out.println("instant: " + localDateTime.atZone(zoneId).toInstant());
    }

    public static void testDuration() {
        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = date1.minusYears(1);
        Duration between = Duration.between(date1, date2);
        System.out.println(between.toDays());
        throw new NullPointerException();
    }

    public void test() {
        throw new NullPointerException();
    }

    public static void main(String[] args) {
//        testChronoFiled();
//        testTemporalAdjuster();
//        testFormatter();
//        testLocalDateTime();
//        testInstant();
        try {
            testDuration();
        } finally {
            System.out.println("finally...");
        }
    }
}
