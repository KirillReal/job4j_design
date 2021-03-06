package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;

public class EchoServer {
    private static final Logger LOG =  LoggerFactory
            .getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    String[] arrStr = str.split("=");
                    str = arrStr[1];

                    if (str.contains("Hello")) {
                        out.write("Hello".getBytes());
                        out.write("HTTP/1.1 200 OK\r\n\r\n".concat(str).getBytes());
                    } else if (str.contains("Exit")) {
                        break;
                    } else {
                        str = Stream.of(str.split(" "))
                                .filter(s -> s.contains("msg"))
                                .findFirst()
                                .orElse("");
                        System.out.println("msg: " + str);
                        out.write("HTTP/1.1 200 OK\r\n\r\n".concat(str).getBytes());
                    }
                }
                }
            } catch (IOException e) {
            LOG.error("Exception in log");
        }
        }
}

