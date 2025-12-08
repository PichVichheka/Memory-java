package com.MyMemory.MyMemory.Dto.Requset;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}
