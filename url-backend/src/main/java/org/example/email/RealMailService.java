package org.example.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RealMailService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${app.mail.from}")
    private String from;

    @Async
    public boolean sendMail(String to, String otp) {

        log.info("Mail forwarding starts...");

        try {

            String url = "https://api.brevo.com/v3/smtp/email";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", apiKey);

            Map<String, Object> requestBody = Map.of(
                    "sender", Map.of(
                            "name", "Authenti-City",
                            "email", from
                    ),
                    "to", List.of(
                            Map.of("email", to)
                    ),
                    "subject", "Delivery of the OTP for Authenti-City.",
                    "textContent", "Your OTP is : " + otp +
                            " for my URL app. (Type fast! We don't store OTPs for long.)"
            );

            HttpEntity<Map<String, Object>> entity =
                    new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    url,
                    entity,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Mail sent successfully.");
                log.info("Brevo Response: {}", response.getBody());
                return true;
            }

            log.error("Failed to send mail. Status: {}", response.getStatusCode());
            return false;

        } catch (Exception e) {
            log.error("Mail failed.", e);
            return false;
        }
    }
}