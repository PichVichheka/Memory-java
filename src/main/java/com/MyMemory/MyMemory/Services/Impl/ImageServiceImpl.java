package com.MyMemory.MyMemory.Services.Impl;

import com.MyMemory.MyMemory.Dto.Requset.ImageRequestDto;
import com.MyMemory.MyMemory.Dto.Response.ImageResponseDto;
import com.MyMemory.MyMemory.Enitity.Image;
import com.MyMemory.MyMemory.Enitity.User;
import com.MyMemory.MyMemory.Repository.ImageRepository;
import com.MyMemory.MyMemory.Repository.UserRepository;
import com.MyMemory.MyMemory.Services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    private final String uploadDir = "uploads/";

    @Override
    public ImageResponseDto uploadImage(ImageRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MultipartFile file = dto.getFile();
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        try {
            Files.createDirectories(filePath.getParent());
            file.transferTo(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }

        Image image = Image.builder()
                .user(user)
                .fileName(fileName)
                .url("/" + uploadDir + fileName)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Image saved = imageRepository.save(image);
        return mapToResponseDTO(saved);
    }

    @Override
    public List<ImageResponseDto> getAllImages() {
        return imageRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private ImageResponseDto mapToResponseDTO(Image image) {
        return ImageResponseDto.builder()
                .id(image.getId())
                .userId(image.getUser().getId())
                .fileName(image.getFileName())
                .url(image.getUrl())
                .createdAt(image.getCreatedAt())
                .updatedAt(image.getUpdatedAt())
                .build();
    }
}
