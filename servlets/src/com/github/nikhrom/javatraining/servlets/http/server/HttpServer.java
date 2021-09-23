package com.github.nikhrom.javatraining.servlets.http.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private final ExecutorService pool;
    private final int port;
    private boolean stopped;


    public HttpServer(int port, int poolSize) {
        this.pool = Executors.newFixedThreadPool(poolSize);

        this.port = port;
    }

    public void run(){
        try (var server = new ServerSocket(port)){
            while (!stopped) {
                var socket = server.accept();
                System.out.println("Accepted");
                pool.submit(() -> processSocket(socket));
            }
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
            System.out.println(inputStream);
            Thread.sleep(0);
            OptionalInt contentLength = getContentLength(inputHeaders);

            if(contentLength.isPresent()){
                try {
                    System.out.println("Request: " + new String(inputStream.readNBytes(contentLength.getAsInt())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            contentLength.ifPresent((length) ->
//                    {
//                        try {
//                            System.out.println("Request: " + new String(inputStream.readNBytes(length)));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//            );




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

        } catch (IOException | InterruptedException e) {
            // TODO: 22.09.2021 log error message
            e.printStackTrace();
        }
    }

    private OptionalInt getContentLength(List<String> inputHeaders) {
        return inputHeaders.stream()
                        .skip(1)
                        .map(line -> line.split(": "))
//                        .map(line -> new String[]{line[0].trim(), line[1].trim()})
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

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}
