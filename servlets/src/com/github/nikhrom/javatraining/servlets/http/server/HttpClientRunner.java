package com.github.nikhrom.javatraining.servlets.http.server;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientRunner {

    public static void main(String[] args) {
        var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();



        var request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("send request"))
                .header("content-type", "text/plain")
                .uri(URI.create("http://localhost:80"))
                .build();




        try {
            System.out.println("Отправляю");
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Принял");
            var headers = response.headers();
            System.out.println(headers);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
