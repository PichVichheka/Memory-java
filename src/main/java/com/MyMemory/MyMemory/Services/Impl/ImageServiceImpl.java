package com.MyMemory.MyMemory.Services.Impl;

import com.MyMemory.MyMemory.Dto.Response.ImageResponseDto;
import com.MyMemory.MyMemory.Enitity.Image;
import com.MyMemory.MyMemory.Repository.ImageRepository;
import com.MyMemory.MyMemory.Services.FileStorageService;
import com.MyMemory.MyMemory.Services.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final FileStorageService fileStorageService;

    @Override
    public ImageResponseDto uploadImage(String url, MultipartFile file) {

        if ((url == null || url.isBlank()) && (file == null || file.isEmpty())) {
            throw new IllegalArgumentException("Please provide a file OR a URL.");
        }

        String finalUrl;
        String fileName = null;

        if (file != null && !file.isEmpty()) {
            try {
                finalUrl = fileStorageService.storeFile(file);
                fileName = file.getOriginalFilename();
            } catch (IOException e) {
                log.error("Failed to upload file: {}", e.getMessage());
                throw new IllegalArgumentException("Failed to upload file.");
            }
        } else {
            finalUrl = url.trim();
        }

        Image image = Image.builder()
                .url(finalUrl)
                .fileName(fileName)
                .createdAt(LocalDateTime.now())
                .build();

        Image saved = imageRepository.save(image);

        return new ImageResponseDto(saved.getId(), saved.getUrl(), saved.getFileName());
    }

    @Override
    public List<ImageResponseDto> getAllImages() {
        return imageRepository.findAll().stream()
                .map(i -> new ImageResponseDto(i.getId(), i.getUrl(), i.getFileName()))
                .collect(Collectors.toList());
    }
}
