package com.MyMemory.MyMemory.Services;

import com.MyMemory.MyMemory.Dto.Requset.ImageRequestDto;
import com.MyMemory.MyMemory.Dto.Response.ImageResponseDto;

import java.util.List;

public interface ImageService {

    // Upload an image
    ImageResponseDto uploadImage(ImageRequestDto dto);

    // Get all images
    List<ImageResponseDto> getAllImages();
}
