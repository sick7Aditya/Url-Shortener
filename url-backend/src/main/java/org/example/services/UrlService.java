package org.example.services;

import lombok.extern.slf4j.Slf4j;
import org.example.models.UrlModel;
import org.example.repo.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@Slf4j
public class UrlService {

    @Autowired
    private UrlRepository u_repo;

//   url service does two thing :
//    saves url
//    provide url connection.


    //    1. save url
    public String saveUrl(String url)
    {
        boolean sign = u_repo.existsByUrl(url);
        if(sign)
        {
            log.info("What the Heck man !! Stop filling the db");
            UrlModel ur = u_repo.findByUrl(url);
            return ur.getSmallHashCode();
        }
        else
        {
            log.info("New Pokemon for the Db"+url+"Enter the shield");
            String hashCode = encode(url);
            UrlModel ur = new UrlModel(url,hashCode);
            u_repo.save(ur);
//            return "Success "+ur.getSmallHashCode();  // isko trim karne pade kyuki hashcode ke saath bakwaas bhi likhi hai na. :3
            return ur.getSmallHashCode(); // frontend mai easy hoti iss sa :3
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
        return Base64.getUrlEncoder().encodeToString(url.getBytes());
    }


}
