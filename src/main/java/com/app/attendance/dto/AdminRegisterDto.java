package com.app.attendance.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegisterDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
