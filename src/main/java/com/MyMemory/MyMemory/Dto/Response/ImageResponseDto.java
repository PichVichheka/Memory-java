package com.MyMemory.MyMemory.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponseDto {

    private Long id;
    private Long userId; // The ID of the user who owns the image
    private String fileName;
    private String url;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
