package com.github.nikhrom.javatraining.servlets.http.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.*;



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
            var inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var outputStream = new DataOutputStream(socket.getOutputStream())){

            // handle request
            List<String> inputHeaders = readHeaders(inputReader);
            System.out.println(inputHeaders);
            OptionalInt contentLength = getContentLength(inputHeaders);

            contentLength.ifPresent((length) ->{
                try {
                    char[] body = new char[length];
                    inputReader.read(body);
                    System.out.println("Body: " + new String(body));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });



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
                        .map(line -> line.split(": "))
                        .filter(header -> header[0].toLowerCase().equals("Content-Length".toLowerCase()))
                        .mapToInt(line -> Integer.parseInt(String.valueOf(line[1])))
                        .findFirst();
    }

    private List<String> readHeaders(Reader reader) throws IOException {

        var headerStream = new BufferedReader(reader);
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
