package org.example.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    private String otp;

    @Indexed(expireAfterSeconds = 0)
    private Date expiresAt;

}
