package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class OtpVerify {

    private String otp;
    private String email,name,pwd;
}
