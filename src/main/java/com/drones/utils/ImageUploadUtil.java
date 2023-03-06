package com.drones.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

public class ImageUploadUtil {

    private static final String UPLOAD_DIR = "src/main/resources/images/";

    public static void saveImage(final String fileName,
                                final MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get(UPLOAD_DIR);

        if (!Files.exists(uploadPath)) {

            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {

            Path filePath = uploadPath.resolve(fileName);

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ioe) {

            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static String uploadImage(final MultipartFile multipartFile) {

        final String imagePath = UPLOAD_DIR + multipartFile.getOriginalFilename();

        String imageName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        try {

            saveImage(imageName, multipartFile);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        return imagePath;
    }
}
