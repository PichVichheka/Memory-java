package com.MyMemory.MyMemory.Controllers;

import com.MyMemory.MyMemory.Dto.Requset.ImageRequestDto;
import com.MyMemory.MyMemory.Dto.Response.ImageResponseDto;
import com.MyMemory.MyMemory.Enitity.User;
import com.MyMemory.MyMemory.Repository.UserRepository;
import com.MyMemory.MyMemory.Services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final UserRepository userRepository;

    // Upload image
    @PostMapping("/upload")
    public ResponseEntity<ImageResponseDto> uploadImage(
            @RequestParam("userId") Long userId,
            @RequestParam("file") MultipartFile file) {

        ImageRequestDto dto = new ImageRequestDto(userId, file);
        ImageResponseDto response = imageService.uploadImage(dto);
        return ResponseEntity.ok(response);
    }

    // Get all images
    @GetMapping
    public ResponseEntity<List<ImageResponseDto>> getAllImages() {
        return ResponseEntity.ok(imageService.getAllImages());
    }

    // Temporary endpoint to create a test user
    @PostMapping("/test/create-user")
    public ResponseEntity<User> createTestUser() {
        User user = User.builder()
                .email("test@example.com")
                .password("123456")
                .phone("0123456789")
                .fullName("Test User")
                .createdAt(LocalDateTime.now())
                .build();
        User saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }
}
