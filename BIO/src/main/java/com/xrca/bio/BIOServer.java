package com.xrca.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xrca
 * @date 2020-06-16 22:53
 */
public class BIOServer {
    public static void main(String[] args) throws Exception {
        // 创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(8090);

        System.out.println("========== socket服务启动了 ==========");

        while (true) {
            final Socket socket = serverSocket.accept();

            System.out.println("连接到客户端");

            executorService.execute(() -> handle(socket));
        }
    }

    // 处理通讯请求
    public static void handle(Socket socket) {
        try {
            byte[] msg = new byte[1024];

            // 通过socket获取输入流
            InputStream ips = socket.getInputStream();
            while (true) {
                int read = ips.read(msg);

                if (read > -1) {
                    System.out.println(new String(msg, 0, read));
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
