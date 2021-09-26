package com.github.nikhrom.javatraining.servlets.http.server;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

public class HttpClientRunner {

    public static void main(String[] args) throws InterruptedException {
        var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();



        var request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("request"))
                .header("content-type", "text/plain")
                .uri(URI.create("http://localhost:80"))
                .build();




        try {
            System.out.println("Отправляю");

            var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            var response2 = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            var response3 = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            System.out.println("Принял");

            System.out.println(response.headers());
            System.out.println(response2.headers());
            System.out.println(response3.headers());




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
