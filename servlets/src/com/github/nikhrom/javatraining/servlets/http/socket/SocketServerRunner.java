package com.github.nikhrom.javatraining.servlets.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServerRunner {

    public static void main(String[] args) throws IOException {

        try (var serverSocket = new ServerSocket(80);
             var socket = serverSocket.accept();
             var output = new DataOutputStream(socket.getOutputStream());
             var input = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in)) {

            var request = input.readUTF();

            while(!request.equals("stop")){
                System.out.println("Client request: " + request);

                var response = scanner.nextLine();
                output.writeUTF(response);
                request = input.readUTF();
            }



        }


    }

}
