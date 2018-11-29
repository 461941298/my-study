package com.yjh.study.nio.cap1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;

import org.junit.Before;
import org.junit.Test;

/**
 * @author yjh
 * @discrption 对于FileChannel的学习
 */
public class FileChannelTest {

    private File file;
    private FileInputStream fileInputStream;
    private FileChannel channel;

    @Before
    public void before() throws FileNotFoundException {
        //获取文件对象
        file = new File("C:\\Users\\yjh\\code-space\\java\\my-study\\java-again\\src\\main\\java\\com\\yjh\\study\\nio\\cap1\\yjh.txt");
        //获取读文件的流
        fileInputStream = new FileInputStream(file);
        //获取主角：FileChannel
        channel = fileInputStream.getChannel();
    }

    /**
     * 测试基本用法
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        //申请与channel交互的buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        while ((channel.read(buffer)) != -1) {
            //重置buffer的position和limit
            buffer.flip();
            //如果buffer的position小于limit
            if (buffer.hasRemaining()) {
                byte[] bytes = new byte[buffer.remaining()];
                //将buffer中的字节复制到新的字节数组中
                buffer.get(bytes);
                String str = new String(bytes, "UTF-8");
                System.out.print(str);
            }
            //清空buffer，其实只是将position设为0，limit设为容量最大值
            buffer.compact();

        }
        //关闭
        fileInputStream.close();
        channel.close();
    }

    /**
     * 测试分散
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        /*
            骤集与分散类似
            ByteBuffer header = ByteBuffer.allocate(128);
            ByteBuffer body   = ByteBuffer.allocate(1024);
            ByteBuffer[] bufferArray = { header, body };
            channel.write(bufferArray);
        */

        //申请与channel交互的buffer
        ByteBuffer buffer1 = ByteBuffer.allocate(64);
        ByteBuffer buffer2 = ByteBuffer.allocate(64);
        ByteBuffer[] buffers = {buffer1, buffer2};

        while ((channel.read(buffers)) != -1) {

            for (ByteBuffer buffer : buffers) {
                //重置buffer的position和limit
                buffer.flip();
                //如果buffer的position小于limit
                if (buffer.hasRemaining()) {
                    byte[] bytes = new byte[buffer.remaining()];
                    //将buffer中的字节复制到新的字节数组中
                    buffer.get(bytes);
                    String str = new String(bytes, "UTF-8");
                    System.out.print(str);
                }
                //清空buffer，其实只是将position设为0，limit设为容量最大值
                buffer.compact();
            }
        }
        //关闭
        fileInputStream.close();
        channel.close();
    }

    @Test
    public void test3(){
        int i = SelectionKey.OP_READ & 4;
        System.out.println();
    }

}
