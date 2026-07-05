package org.example.services;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.UrlData;
import org.example.models.UrlModel;
import org.example.models.UserModel;
import org.example.repo.UrlRepository;
import org.example.repo.UserModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@Slf4j
public class UrlService {

    @Autowired
    private UrlRepository u_repo;

    @Autowired
    private UserModelRepo user_r;

//   url service does two thing :
//    saves url
//    provide url connection.


    //    1. save url
    public String saveUrl(UrlData ud,String email)
    {
        if(user_r.existsByEmail(email))
        {
            UserModel um = user_r.findByEmail(email);
            if(um.getLimit() == 0)
            {
                log.info("Limit Reached hai user ki.5 sa zyade nhi kar sakte.");
                return "Limit Reached Paaji!";
            }
            else
            {
                boolean sign = u_repo.existsByUrl(ud.getUrl());
                if (sign) {
                    log.info("Optimized Storage -> Url already Existed.");
                    UrlModel ur = u_repo.findByUrl(ud.getUrl());
                    um.setLimit(5 - um.getUserUrls().size() - 1);    // decreasing limit.
                    um.getUserUrls().add(ur.getId());
                    user_r.save(um);
                    return ur.getSmallHashCode();
                }
                else {
                    log.info("New Pokemon(url) for the db :" + ud.getUrl());
                    String hashCode ;   // smalling the url.
                    do {
                        hashCode = encode(ud.getUrl());   //argument pass krne ka fyade nhi hai btw.
                    } while (u_repo.existsBySmallHashCode(hashCode));

                    UrlModel ur = u_repo.save(new UrlModel(ud.getUrl(), hashCode));    // save krte samay ya record return krdete (danvir karan).
                                 // Storing the url.
//                    ur = u_repo.findByUrl(ud.getUrl());         // jo abhi store kiya uski id chahiye thi.
//                    UserModel um = user_r.findByEmail(ud.getEmail());  // User Document mai limit kam ki user ki &&  url's ki list mai url ki id add ki.
                    um.setLimit(5 - um.getUserUrls().size() - 1);    // decreasing limit.
                    um.getUserUrls().add(ur.getId());
                    user_r.save(um);    //saved krdiye.
                    return ur.getSmallHashCode(); // frontend mai aasani hoti iss sa :3

//            return "Success "+ur.getSmallHashCode();  // isko trim karne pade kyuki hashcode ke saath bakwaas bhi likhi hai na. :3
                }
            }
        }
        else {
            return "Wrong_Email";
        }
    }

    //    2. provide url connection.
    public String provideUrl(String code)
    {
        boolean sign = u_repo.existsBySmallHashCode(code);
        if(sign)
        {
            UrlModel ur = u_repo.findBySmallHashCode(code);
            log.info("Here goes ur Url :"+ur.getUrl());
            return ur.getUrl();
        }
        else
        {
            log.info("Database mai aaj tak aise url nhi aaya TvT");
            return "fails";
        }

    }

    public String encode(String url) {
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder sb = new StringBuilder();
        SecureRandom r = new SecureRandom();
        for (int i = 0; i < 7; i++) {
            sb.append(CHARACTERS.charAt(r.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
        }
}
