package org.example.services;


import org.example.dto.*;
import org.example.models.OTPModel;
import org.example.models.UrlModel;
import org.example.models.UserModel;
import org.example.repo.OtpRepo;
import org.example.repo.UrlRepository;
import org.example.repo.UserModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserModelRepo urepo;

    @Autowired
    private OtpGenerator og;

    @Autowired
    private OtpRepo orepo;

    @Autowired
    private PasswordEncoder passwordEncoder;   // claude na bohot gaali di warne mai toh plain text use kr rahe tha :3.

    @Autowired
    private UrlRepository url_repo;
//    correct waale Login finally (TvT).
    public String Login(LoginTimeDTO lt)
    {
        log.info("Checking if this guy in the db");
        UserModel u = urepo.findByEmail(lt.getEmail());
        if(u == null)
        {
            log.info("The user never existed in the db");
            return "no_mail";
        }
        else {
            if(passwordEncoder.matches(lt.getPwd() , u.getPwd()))
            {
                log.info("The Password Matches. ,isko abhi bhi password yaad hai 🥀.");
                return "Success";
            }
            else {
                log.info("HAHAHA !!!! finally koi bande aaya jo password bhul gya.");
                return "failure";
            }
        }
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
                u.setPwd(passwordEncoder.encode(ov.getPwd()));
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


    public List<UrlModel> provideAllUserUrls(String email)
    {
//        if(urepo.existsByEmail(au.getEmail()))
//        {
//            return url_repo.findAllById(urepo.findByEmail(au.getEmail()).getUserUrls());
//        }
//        else
//        {
//            return new ArrayList<UrlModel>();
//        }

//      this one is more db efficient.
        UserModel user = urepo.findByEmail(email);

        if (user == null) {
            return new ArrayList<>();
        }

        return url_repo.findAllById(user.getUserUrls());
    }



}
