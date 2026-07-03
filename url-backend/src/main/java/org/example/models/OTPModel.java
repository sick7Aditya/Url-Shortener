package org.example.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.stereotype.Indexed;

import java.util.Date;

@Document("OTP")
@Data
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class OTPModel {
    @Id
    private String id;
    private String email ,pwd, name;

    @ToString.Exclude
    private String otp;

    @Indexed(expireAfter = "2m")
    private Date expiresAt;

}
