package com.MyMemory.MyMemory.Services;

import com.MyMemory.MyMemory.Dto.Response.ImageResponseDto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    ImageResponseDto uploadImage(String url, MultipartFile file);

    List<ImageResponseDto> getAllImages();
}
