package com.github.nikhrom.javatraining.http.practice.service;

import com.github.nikhrom.javatraining.http.practice.util.PropertiesUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {

    private static final ImageService INSTANCE = new ImageService();

    private final String baseDir = PropertiesUtil.get("image.base.url");

    public static ImageService getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public Optional<InputStream> get(String imagePath) {
        var imageFullPath = Path.of(baseDir, imagePath);
        return Files.exists(imageFullPath)
                ? Optional.of(Files.newInputStream(imageFullPath))
                : Optional.empty();
    }

    @SneakyThrows
    public void upload(String imagePath, InputStream imageContent){
        if(imageContent != null) {
            try (imageContent) {
                if (imagePath != null) {
                    var imageFullPath = Path.of(baseDir + imagePath);
                    Files.createDirectories(imageFullPath.getParent());
                    Files.write(imageFullPath, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
                }
            }
        }
    }
}
