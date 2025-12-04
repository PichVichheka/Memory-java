package com.MyMemory.MyMemory.Dto.Requset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequestDto {
    private Long userId;      
    private MultipartFile file; 
}
