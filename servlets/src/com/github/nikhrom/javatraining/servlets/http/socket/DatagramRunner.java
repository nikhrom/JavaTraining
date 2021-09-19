package com.github.nikhrom.javatraining.servlets.http.socket;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class DatagramRunner {

    public static void main(String[] args) throws IOException {

        InetAddress address = Inet4Address.getByName("localhost");

        try (var datagramSocket = new DatagramSocket()) {

            var scanner = new Scanner(System.in);


            while (scanner.hasNextLine()) {
                var data = new byte[512];
                String request = scanner.nextLine();
                System.arraycopy(request.getBytes(), 0, data, 0, request.getBytes().length);

                DatagramPacket packet = new DatagramPacket(data, data.length, address, 80);
                datagramSocket.send(packet);
            }
        }
    }

}
