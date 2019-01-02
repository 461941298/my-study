package com.yjh.study.langPackage;

import org.junit.Test;

/**
 * 枚举测试类
 */
public class EnumTest {

    @Test
    public void test1() {
        /*
        1. 所有的枚举类都默认继承自 Enum
        2. Enum 内置了 values方法，返回所有枚举对象的数组
        3. Enum 内置了 valueOf(Class , String)方法
        4. 所有枚举类都有一个 valueOf(String)方法
         */
        System.out.println(SexType.valueOf(1));
        System.out.println(SexType.valueOf("MAN"));
        System.out.println(Enum.valueOf(SexType.class, "MAN"));
    }
}

enum SexType {

    // 定义枚举成员
    MAN('男', 0),
    WOMAN('女', 1);

    // 定义枚举类的属性
    char name;
    int value;

    // 定义枚举类的构造方法
    SexType(char name, int value) {
        this.name = name;
        this.value = value;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static SexType valueOf(int value) {
        SexType sexType = null;
        for (SexType sex : SexType.values()) {
            if (sex.value == value) {
                sexType = sex;
            }
        }
        return sexType;
    }

}
