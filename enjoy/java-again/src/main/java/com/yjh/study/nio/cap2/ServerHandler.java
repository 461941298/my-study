package com.yjh.study.nio.cap2;

import org.junit.internal.runners.statements.RunAfters;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author yjh
 * @discrption
 */
public class ServerHandler implements Runnable {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private String host;
    private Integer port;
    private boolean started = false;

    public ServerHandler(Integer port) {
        try {
            this.host = host;
            this.port = port;
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            this.started = true;
            System.out.println("服务端开启端口：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        try {
            while (started) {
                //如果没有已经准备好的channel，这里将会阻塞
                selector.select();

                //获取所有可用的key
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //获取遍历器
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    handleKey(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleKey(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                SocketChannel channel = serverSocketChannel.accept();
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_READ);
            }

            if (key.isReadable()) {
                doRead((SocketChannel) key.channel());
            }

        }
//        key.cancel();
    }

    private void doWrite(SocketChannel channel) {
        Scanner scanner = new Scanner(System.in);
        String str = null;
        while (scanner.hasNext()) {
            str = scanner.next();
            if ("over".equals(str)) {
                break;
            }
            byte[] bytes = str.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            try {
                channel.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void doRead(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(48);
        while ((channel.read(buffer)) > 0) {
            buffer.flip();
            if (buffer.hasRemaining()) {
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                System.out.println(new String(bytes, "UTF-8"));
            }
            buffer.clear();
        }
        doWrite(channel);
//        channel.close();
    }
}
