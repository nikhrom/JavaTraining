package com.github.nikhrom.javatraining.http.practice.servlet;

import com.github.nikhrom.javatraining.http.practice.service.ImageService;
import com.github.nikhrom.javatraining.http.practice.util.UrlPath;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@WebServlet(UrlPath.IMAGES + "/*")
public class ImageServlet extends HttpServlet{

    private final ImageService imageService = ImageService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var requestUri = req.getRequestURI();
        var imagePath = requestUri.replace("/images", "");

        imageService.get(imagePath)
        .ifPresentOrElse(image -> {
            resp.setContentType("application/octet-stream");
            writeImage(image, resp);
            }, () -> resp.setStatus(404));

    }

    @SneakyThrows
    private void writeImage(InputStream image, HttpServletResponse resp) {
        try(image;
            var outputStream = resp.getOutputStream()){

            int currentByte;
            while ((currentByte = image.read()) != -1){
                outputStream.write(currentByte);
            }

        }
    }
}
