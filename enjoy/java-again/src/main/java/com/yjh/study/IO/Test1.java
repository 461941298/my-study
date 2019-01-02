package com.yjh.study.IO;

import org.junit.Test;

import java.io.*;

/**
 * IO操作中的一些典型应用
 */
public class Test1 {

    /**
     * 缓冲读入文件
     */
    @Test
    public void test1() throws IOException {
        File file = new File("C:\\Users\\yjh\\code-space\\java\\java-again\\src\\main\\java\\com\\yjh\\study\\IO\\testFile\\person.txt");
        BufferedReader in = new BufferedReader(new FileReader(file));
        String read;
        while ((read = in.readLine()) != null) {
            System.out.println(read);
        }
    }

    /**
     * 从键盘输入
     */
    @Test
    public void test2() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        System.out.println("sfsdf");
        while ((s = reader.readLine()) != null){
            System.out.println(s);
        }
    }
}
