package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.dto.LoginTimeDTO;
import org.example.dto.OtpVerify;
import org.example.dto.Signup;
import org.example.email.MailService;
import org.example.services.OtpGenerator;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private MailService ms;
    @Autowired
    private UserService us;
    @Autowired
    private OtpGenerator op;


//     for Login->check Password == db ka password :
    @PostMapping("/Login")
    public String getLogin(@RequestBody LoginTimeDTO lt , HttpServletRequest req)
    {
        String s = us.Login(lt);
        if(s.equals("no_mail"))
        {
            return "what the heck mail doesnt exist?Sign Up First with this mail!";
        }
        else {
            if(s.equals("Success"))
            {
                // bhaynakar security -> creating an session because koi bhi non user saare url's dekh sakte db ke of any user(no protection) ,

//               storing user in a session (>,<)
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        lt.getEmail(), null, List.of(new SimpleGrantedAuthority("USER"))
                );
//               step 2 :
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(auth);
                SecurityContextHolder.setContext(context);

                req.getSession(true).setAttribute(
                        HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context
                );
                return "gtg";
            }
            else {
                return "wrong_pwd";
            }
        }
    }

//    for signup -> pehli toh user ka auth city kro using otp , then password leke sign in karo .
//     sign up has phases :
//    1. email,username and pwd dalne do frontend mai and now otp banao backend mai.
//    2. otp dalega frontend sa user , backend mai match kro same hai ki nhi.
//    3. otp match krne ke baad usse andar aane do app ke.

    @PostMapping("/SignUp")
    public String makeSignUp(@RequestBody Signup su)
    {
//        credential mai sa email and otp save kro otp db mai.
//        credential mai sa email, pwd , name save kro user db mai.
//        ya upar jo bhi likha hai maine verification of otp ke time pa kare hai.
        if(us.ClickedSignUpButton(su))
        {
            return "Success";
        }
        else
        {
            return "Fails";
        }
    }

//    verify otp
    @PostMapping("/Otp")
    public String checkOTP(@RequestBody OtpVerify ov)
    {
        if(us.verifyOTP(ov))
        {
            return "success";
        }
        else {
            return "Fails";
        }
    }

}

