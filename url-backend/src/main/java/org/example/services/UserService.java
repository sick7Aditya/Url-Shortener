package org.example.services;


import org.example.dto.LoginTimeDTO;
import org.example.dto.OtpVerify;
import org.example.dto.Signup;
import org.example.models.OTPModel;
import org.example.models.UserModel;
import org.example.repo.OtpRepo;
import org.example.repo.UserModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserModelRepo urepo;

    @Autowired
    private OtpGenerator og;

    @Autowired
    private OtpRepo orepo;

//    correct waale Login finally (TvT).
    public String Login(LoginTimeDTO lt)
    {
        log.info("Checking if this nig in the db");
        UserModel u = urepo.findByEmail(lt.getEmail());
        if(u == null)
        {
            log.info("The user never existed in the db");
            return "no_mail";
        }
        else {
            if(u.getPwd().equals(lt.getPwd()))
            {
                log.info("Nig the Password Matches.");
                return "Success";
            }
            else {
                log.info("Bro who the hell u tryne to be.");
                return "failure";
            }
        }
    }
    

    public boolean verifyLimit(String email)
    {
        UserModel u=urepo.findByEmail(email);
        if(u.getLimit() > 0)
        {
            u.setLimit(u.getLimit()-1);
            urepo.save(u);
            log.info("okay Limit decreases for u :"+u.getLimit());
            return true;
        }
        log.info("user has its limit , contact owner to get more space.");
        return false;
    }

    public boolean ClickedSignUpButton(Signup su)
    {
        log.info("ok !! new email");
        if(urepo.existsByEmail(su.getEmail()))
        {
            log.info("Mail Existed !!! hence cannot be used again to make the acc.");
            return false;
        }
        else {
            if (og.generateOTP(su)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean verifyOTP(OtpVerify ov)
    {
        log.info("Aight verifying the OTP...");
        String otp = og.returnOTP(ov.getEmail());

        if(otp.equals("-1"))
        {
            log.info("OTP with this mail doesnt exist in the db");
            return false;
        }
        else
        {
            if(ov.getOtp().equals(otp))
            {
                OTPModel o = orepo.findByEmail(ov.getEmail());
                UserModel u = new UserModel();
                u.setEmail(o.getEmail());
                u.setName(o.getName());
                u.setPwd(o.getPwd());
                u.setLimit(5);
                urepo.save(u);
                orepo.deleteByEmail(ov.getEmail());
                log.info("User saved in the DB. and OTP details delete from db.");
                return true;
            }
            else
            {
                log.info("Wrong OTP Entered by user");
                return false;
            }
        }
    }



}
