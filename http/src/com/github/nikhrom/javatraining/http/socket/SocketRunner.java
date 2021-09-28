package com.github.nikhrom.javatraining.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketRunner {

    public static void main(String[] args) throws IOException {

        InetAddress ip = Inet4Address.getByName("localhost");
        try (var socket = new Socket(ip, 80);
             var output = new DataOutputStream(socket.getOutputStream());
             var input = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in)) {

            while(scanner.hasNextLine()){
                var request = scanner.nextLine();
                output.writeUTF(request);


                var response = input.readUTF();
                System.out.println("Response from server: " + response);
            }


        }

    }
}
