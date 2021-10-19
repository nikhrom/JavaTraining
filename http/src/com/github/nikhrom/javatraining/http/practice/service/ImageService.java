package com.github.nikhrom.javatraining.http.practice.service;

import com.github.nikhrom.javatraining.http.practice.util.PropertiesUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {

    private static final ImageService INSTANCE = new ImageService();

    private final String baseDir = PropertiesUtil.get("image.base.url");

    public static ImageService getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public void upload(Optional<String> imagePath, Optional<InputStream> imageContent){
        if(imageContent.isPresent()) {
            InputStream imageStream = imageContent.get();
            try (imageStream) {
                if (imagePath.isPresent()) {
                    var imageFullPath = Path.of(baseDir + imagePath.get());
                    Files.createDirectories(imageFullPath.getParent());
                    Files.write(imageFullPath, imageStream.readAllBytes(), CREATE, TRUNCATE_EXISTING);
                }
            }
        }
    }

}
