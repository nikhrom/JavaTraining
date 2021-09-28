package com.github.nikhrom.javatraining.http.server;

public class HttpServerRunner {

    public static void main(String[] args) {
        var httpServer = new HttpServer(80, 5);
        httpServer.run();
    }

}

