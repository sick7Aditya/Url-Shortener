package org.example.email;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    private final JavaMailSender jms;

    @Value("${app.mail.from}")
    private String from;

    public MailService(JavaMailSender jms)
    {
        this.jms = jms;
    }

    @Async
    public boolean sendMail(String to,String otp)
    {
        log.info("Mail forwards Starts ...");
        try
        {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo(to);
            msg.setSubject("Delivery of the OTP for Authenti-City.");
            msg.setText("Your OTP is : xxx(db mai check krlo aap :3) for my URl app.(Type Fast otp big boy ,we dont store otp for whole day.)");
            jms.send(msg);
            log.info("Mail has been released from backend");
            return true;
        }
        catch(Exception e)
        {
            log.error("Mail failing -> reason :",e.getMessage());
            e.printStackTrace();
            return false;
        }

    }
}
