package org.example.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.example.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Map;


@RequestMapping("/api")
@RestController
public class UrlController {
    @Autowired
    private UrlService u_service;

    //  get the url  * checking the db whether it exist or not.
    @PostMapping("/add")
    public String addUrl(@RequestBody Map<String,String> obj)  // map ki jagah dto bhi use kr sakte , best approach.:3
    {
        String s = u_service.saveUrl(obj.get("url"));
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
}