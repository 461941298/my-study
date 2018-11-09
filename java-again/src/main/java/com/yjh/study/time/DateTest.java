package com.yjh.study.time;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期测试类
 */
public class DateTest {

    @Test
    public void test() {
        Date date1 = new Date();
        long date1Value = date1.getTime();

        Date date2 = new Date(date1Value + 1000);
        long date2Value = date2.getTime();

        assert date1.before(date2) && date1Value < date2Value;
    }

    /**
     * 日期转字符串
     */
    @Test
    public void format() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(dateFormat.format(date));
    }

    /**
     * 字符串转日期
     *
     * @throws ParseException
     */
    @Test
    public void parse() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2018-5-15 3:25:50");
        System.out.println(dateFormat.format(date));
    }
}
