package com.yjh.study.time;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日历测试类
 */
public class CalendarTest {

    @Test
    public void test1() {
        //日历的实例化
        Calendar calendar1 = Calendar.getInstance();
        show(calendar1);

        //最大的月份
        int maxMonth = calendar1.getMaximum(Calendar.MONTH);
        System.out.println("最大月份是 " + maxMonth);
        int maxDay = calendar1.getMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("最大天数是 " + maxDay);

        //获取Date对象
        Date date = calendar1.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(dateFormat.format(date));

        //自定义日历
        calendar1.set(2018, 1 - 1, 1); // 1 - 1 代表真实的1月份
        System.out.println("自定义日历1");
        show(calendar1);

        calendar1.set(2017, 1 - 1, 2, 15, 15, 15);
        System.out.println("自定义日历2");
        show(calendar1);

        //向日历中添加时间
        calendar1.add(Calendar.YEAR, 3);
        System.out.println("添加3年后");
        show(calendar1);

        calendar1.add(Calendar.YEAR, -5);
        System.out.println("减少5后后");
        show(calendar1);

    }

    private void show(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; //日历中1月份是0，所以要 +1
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY); //这个是24小时， HOUR是12小时
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);
        System.out.println(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + ":" +
                second + " " + millisecond);
    }
}
