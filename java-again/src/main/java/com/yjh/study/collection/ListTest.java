package com.yjh.study.collection;

import org.junit.Test;

import java.util.*;

public class ListTest {

    // 普通的集合类
    List<Integer> list = new ArrayList();
    // 同步集合视图
    List<Integer> synchronizedList = Collections.synchronizedList(list);

    /**
     * 测试集合同步的正确方法
     * 1. 创建5个线程，并启动；每个线程向lis中添加100个数
     * 2. 线程不同步 test1 直接抛出了 ArrayIndexOutOfBoundsException 异常
     * 3. 线程同步 test2 可以打印正确结果；并且 test2 中的线程操作的都是 synchronizedList，但是最终其实是把数据添加到了 list 中
     * 所以最后 list 的长度是 500.
     */

    @Test
    public void test1() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    list.add(j);
                }
            }).start();
        }

        Thread.sleep(5000);
        System.out.println(list.size());

    }

    @Test
    public void test2() throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    synchronizedList.add(j);
                }
            }).start();
        }

        Thread.sleep(5000);
        System.out.println(synchronizedList.size());
        System.out.println(list.size());
    }

}
