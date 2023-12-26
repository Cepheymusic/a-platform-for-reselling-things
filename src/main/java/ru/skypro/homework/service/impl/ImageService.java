package ru.skypro.homework.service.impl;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.Exceptions.FilePathIsNullException;
import ru.skypro.homework.Exceptions.ImageException;
import ru.skypro.homework.Exceptions.StorageException;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.ImageRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageService {
    private ImageRepository imageRepository;
    @Value("${uploading.image.path}")
    private String imagePath;

    public ImageService(@Value("${uploading.image.path}") ImageRepository imageRepository, String imagePath) {
        this.imageRepository = imageRepository;
        this.imagePath = imagePath;
    }

    @Transactional
    public byte[] getImage(Integer idImage) {
        ImageEntity imageEntity = imageRepository.findById(idImage).orElseThrow(() -> new RuntimeException());
        try {
            Path imagePath = Paths.get(imageEntity.getFilePath());
            return Files.readAllBytes(imagePath);
        } catch (IOException e) {
            throw new ImageException("Error get file");
        }

    }

    public void deleteUserImage(UserEntity userEntity) throws IOException {
        ImageEntity imageEntity = userEntity.getImageEntity();
        if (imageEntity != null) {
            Path imagePath = Paths.get(imageEntity.getFilePath());
            imageRepository.deleteById(imageEntity.getId());
            Files.delete(imagePath);
        } else {
            throw new ImageException("Image is not found");
        }
    }

    public void deleteAdImage(AdEntity adEntity) throws IOException {
        ImageEntity imageEntity = adEntity.getImageEntity();
        if (imageEntity != null) {
            Path imagePath = Paths.get(imageEntity.getFilePath());
            imageRepository.deleteById(imageEntity.getId());
            Files.delete(imagePath);
        } else {
            throw new ImageException("Image is not found");
        }
    }

    public void deleteImageFromPath(AdEntity adEntity) throws IOException {
        ImageEntity imageEntity = adEntity.getImageEntity();
            if (imageEntity != null) {
                Path iPath = Paths.get(imageEntity.getFilePath());
                Files.delete(iPath);
            } else {
            throw new ImageException("Image is not found");
        }
    }

    public ImageEntity downloadImage(MultipartFile multipartFile) {
        Path filePath;
        if (multipartFile.getOriginalFilename() != null) {
            filePath = Paths.get(imagePath + UUID.randomUUID() + "." + multipartFile.getOriginalFilename().split("\\.")[1]);
        } else throw new FilePathIsNullException("File Path is null");
        try {
            if (multipartFile.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.");
        }
        return ImageEntity.builder().
                filePath(filePath.toString()).build();
    }
}
