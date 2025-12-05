package com.MyMemory.MyMemory.Controllers;

import com.MyMemory.MyMemory.Dto.Response.ImageResponseDto;
import com.MyMemory.MyMemory.Services.ImageService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImageResponseDto> uploadImage(
            @RequestParam(required = false) String url,
            @RequestParam(required = false) MultipartFile file
    ) {
        ImageResponseDto response = imageService.uploadImage(url, file);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ImageResponseDto>> getAll() {
        return ResponseEntity.ok(imageService.getAllImages());
    }
}
