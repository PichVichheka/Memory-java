package com.MyMemory.MyMemory.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageResponseDto {
    private Long id;
    private String url;
    private String fileName;
}
