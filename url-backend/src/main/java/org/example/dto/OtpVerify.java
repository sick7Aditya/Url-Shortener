package org.example.dto;


import lombok.*;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class OtpVerify {

    @ToString.Exclude
    private String otp;
    private String email,name;
    @ToString.Exclude
    String pwd;
}
