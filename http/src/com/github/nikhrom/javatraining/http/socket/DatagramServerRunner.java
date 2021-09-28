package com.github.nikhrom.javatraining.http.socket;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

public class DatagramServerRunner {

    public static void main(String[] args) throws IOException {

        try (var datagramSocket = new DatagramSocket(80)) {

            byte[] buffer = new byte[512];


            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(packet);

            var receive = new String(buffer);

            while (!receive.equals("stop")) {
                System.out.println(receive);
                datagramSocket.receive(packet);
                receive = new String(buffer);
            }


        }

    }

}
