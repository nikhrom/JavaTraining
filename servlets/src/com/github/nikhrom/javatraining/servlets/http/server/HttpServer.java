package com.github.nikhrom.javatraining.servlets.http.server;

import javax.crypto.spec.PSource;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class HttpServer {

    private final int port;


    public HttpServer(int port) {
        this.port = port;
    }

    public void run(){
        try (var server = new ServerSocket(port)){
            System.out.println("создал");

            var socket = server.accept();
            processSocket(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        try(socket;
            var inputStream = new DataInputStream(socket.getInputStream());
            var outputStream = new DataOutputStream(socket.getOutputStream())){

            // handle request
            List<String> inputHeaders = readHeaders(inputStream);
            System.out.println(inputHeaders);

            OptionalInt contentLength = getContentLength(inputHeaders);

            contentLength.ifPresent((length) ->
                    {
                        try {
                            System.out.println("Request: " + new String(inputStream.readNBytes(length)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );




            // handle response
            var body = Files.readAllBytes(Path.of("resources", "example.html"));
            var headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length).getBytes();

            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);

        } catch (IOException e) {
            // TODO: 22.09.2021 log error message
            e.printStackTrace();
        }
    }

    private OptionalInt getContentLength(List<String> inputHeaders) {
        return inputHeaders.stream()
                        .skip(1)
                        .map(line -> line.split(":"))
                        .map(line -> new String[]{line[0].trim(), line[1].trim()})
                        .filter(header -> header[0].toLowerCase().equals("Content-Length".toLowerCase()))
                        .mapToInt(line -> Integer.parseInt(String.valueOf(line[1])))
                        .findFirst();
    }

    private List<String> readHeaders(InputStream inputStream) throws IOException {

        var headerStream = new BufferedReader(new InputStreamReader(inputStream));
        List<String> inputHeaders = new ArrayList<>();
        String currentLine = headerStream.readLine();
        while (!currentLine.equals("")){
            inputHeaders.add(currentLine);
            currentLine = headerStream.readLine();
        }

        return inputHeaders;
    }

}
