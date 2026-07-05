package org.example.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AllUrlRequest;
import org.example.dto.UrlData;
import org.example.models.UrlModel;
import org.example.services.UrlService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;


@RequestMapping("/api")
@RestController
@Slf4j
public class UrlController {
    @Autowired
    private UrlService u_service;


    @Autowired
    private UserService us;
    //  get the url  * checking the db whether it exist or not.
    @PostMapping("/add")
    public String addUrl(@RequestBody UrlData ud , Authentication auth)  // dto ki jagah map bhi use kr sakte , best approach dto he hai.:3
    {
        String s = u_service.saveUrl(ud,auth.getName());
        if(s.startsWith("Success"))
        {
//            return "ur small url :"+s.substring(7,s.length());  // for backend i return this
            return s.substring(7);
        }
        else {
//            return "url Already Existed before in Db:"+s;    //for backend i return this.
            return s;
        }
    }


    @GetMapping("/show/{code}")
    public RedirectView display(@PathVariable String code, HttpServletResponse res) throws Exception
    {
        String url = u_service.provideUrl(code);
        if(url.equals("fails"))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found");
//            return "the code you shared is fake one";
        }

        return new RedirectView(url);
    }


    @GetMapping("/allUrls")
    public List<UrlModel> providingUserAllUrls(Authentication auth)
    {
        log.info("User is asking for the url");
        log.info("Authentication = {}", auth);
        log.info("Name = {}", auth == null ? "null" : auth.getName());
        return us.provideAllUserUrls(auth.getName());
    }
}