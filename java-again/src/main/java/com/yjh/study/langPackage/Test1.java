package com.yjh.study.langPackage;

import org.junit.Test;

public class Test1 {


    /**
     * 可变长参数测试
     */
    @Test
    public void test1() {
        Integer maxInt = maxInt();
        System.out.println(maxInt);

        maxInt = maxInt(1, 2, 3, 4, 5);
        System.out.println(maxInt);
    }

    private Integer maxInt(int... arr) {
        // 可变长参数可以用数据的语法来处理
        if (0 >= arr.length) {
            return null;
        }
        int maxInt = arr[0];
        for (Integer num : arr) {
            if (maxInt < num) {
                maxInt = num;
            }
        }
        return maxInt;
    }
}
