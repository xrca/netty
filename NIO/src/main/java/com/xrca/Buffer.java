package com.xrca;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;

/**
 * @author xrca
 * @date 2020-06-17 21:36
 */
public class Buffer {
    public static void main(String[] args) {
        // 创建一个buffer
        IntBuffer intBuffer = IntBuffer.allocate(8);
        // 向buffer中填充数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        // 读写切换
        // 此处将buffer的limit = position & position = 0 & mark = -1
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }

        // 再次读写切换，写入数据
        intBuffer.flip();
        for (int i = 0; i < intBuffer.capacity() - 1; i++) {
            intBuffer.put(i * 3);
        }

        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }

        ByteBuffer byteBuffer = Charset.defaultCharset().encode("Hello World!");
        //byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.println((char)byteBuffer.get());
        }
    }
}
