package com.yjh.study.nio.cap2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yjh
 * @discrption
 */
public class ClientHandler implements Runnable {

    private SocketChannel channel;
    private Selector selector;
    private String host;
    private Integer port;
    private boolean started = false;

    public ClientHandler(String host, Integer port) {
        try {
            this.host = host;
            this.port = port;
            channel = SocketChannel.open();
            channel.configureBlocking(false);
            selector = Selector.open();
            this.started = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isStarted() {
        return started;
    }

    public void stop() {
        started = false;
    }

    @Override
    public void run() {

        try {
            //启动连接，并注册连接事件
            doConnect();

            //通过遍历感兴趣事件，做相应的业务处理
            while (isStarted()) {
                doBusiness();
            }

            //关闭资源
            doClose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doClose() throws IOException {
        if (selector != null) {
            //所有与该selector相关的key，即便没有被取消，这里也会使其失效
            //key所对应的channel也会做撤消登记
            //所有与该selector相关的资源都会被释放
            selector.close();
        }
    }

    private void doBusiness() throws IOException {

        //如果没有已经准备好的channel，这里将会阻塞
        int select = selector.select();
        System.out.println(select);

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
        selectionKeys.clear();
    }

    private void handleKey(SelectionKey key) throws IOException {
        if (key.isValid()) {

            if (key.isConnectable()) {
                doFinishConnect();
            }

            if (key.isReadable()) {
                doRead();
            }
        }

        //将处理完的key取消掉，不然程序会进入死循环
        key.cancel();
    }

    private void doRead() throws IOException {
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
    }

    private void doFinishConnect() throws IOException {
        if (channel.finishConnect()) {
            System.out.println("与服务器建立连接");
        } else {
            System.out.println("不能与服务器建立连接，程序将退出");
            stop();
        }
    }

    private void doConnect() throws IOException {
        //非阻塞模式中，该连接会立刻返回，真正与服务端的连接要有后续调用finishConnect()
        channel.connect(new InetSocketAddress(host, port));
        channel.register(selector, SelectionKey.OP_CONNECT);
    }


    public void sendMsg(String str) {
        try {
            doWrite(str);
//            channel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void doWrite(String str) throws IOException {
        byte[] bytes = str.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        channel.write(buffer);
    }
}
