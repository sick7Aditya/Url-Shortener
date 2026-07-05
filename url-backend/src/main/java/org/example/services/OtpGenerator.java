package org.example.services;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.Signup;
import org.example.email.RealMailService;
import org.example.models.OTPModel;
import org.example.repo.OtpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;

@Service
@Slf4j
public class OtpGenerator {
    @Autowired
    private OtpRepo orepo;

    @Autowired
    private RealMailService ms;


    public String returnOTP(String email)
    {
        log.info("The OTP is being searched & in both case -> handing it to userService");
        String v = (orepo.existsByEmail(email))?orepo.findByEmail(email).getOtp() : "-1";
        return v;
    }

    public boolean generateOTP(Signup su)
    {
        orepo.deleteByEmail(su.getEmail());   //previous waale ko delete,agar present hai toh ..
        String otp = "" + (100000 + new SecureRandom().nextInt(900000));

        if(ms.sendMail(su.getEmail(),otp))
        {
            OTPModel o = new OTPModel();
            o.setOtp(otp);
            o.setEmail(su.getEmail());
            o.setName(su.getName());
            o.setPwd(su.getPwd());
            o.setExpiresAt(new Date());
            orepo.save(o);
            log.info("OTP saved in db");
            return true;
        }
        else
        {
            log.info("Mail mai error aagyi.");
            return false;
        }
    }
}
