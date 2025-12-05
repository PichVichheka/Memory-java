package com.MyMemory.MyMemory.Dto.Requset;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class ImageUrlRequestDto {
    
    @NotBlank(message = "URL cannot be blank")
    private String url;
}