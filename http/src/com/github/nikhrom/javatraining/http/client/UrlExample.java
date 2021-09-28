package com.github.nikhrom.javatraining.http.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlExample {

    public static void main(String[] args) throws IOException {
        var url = new URL("https://www.google.com");
        var urlConnection = url.openConnection();

        urlConnection.getContent();

    }

}
