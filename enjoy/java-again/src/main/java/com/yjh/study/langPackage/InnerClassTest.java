package com.yjh.study.langPackage;

import org.junit.Test;

public class InnerClassTest {

    /**
     * 非静态内部类的两种创建方法
     */
    @Test
    public void test1() {

        Outer outer = new Outer("this is outer");

        // 第一种：在外部类中添加返回内部类的方法，并调用该方法
        Outer.Inner inner = outer.getInner("this is inner");
        System.out.println(inner);

        // 第二种：使用 "外部对象.new 内部类名()" 这样的方法
        inner = outer.new Inner("this is inner2");
        System.out.println(inner);

        /*
        1. 内部类可以访问外部类的所有元素， 通过："外部类名.this.外部类属性名" 实现
         */
    }


}

class Outer {
    String name;

    public Outer(String name) {
        this.name = name;
    }

    public Inner getInner(String name) {
        return new Inner(name);
    }

    @Override
    public String toString() {
        return "Outer{" +
                "name='" + name + '\'' +
                '}';
    }

    class Inner {
        String name;
        public Inner(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            // 内部类可以访问外部类的所有元素， 通过："外部类名.this.外部类属性名" 实现
            return Outer.this.toString() + '\n' +
                    "Inner{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
