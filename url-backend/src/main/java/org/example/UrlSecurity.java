package org.example;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
public class UrlSecurity {

    @Value("${app.allowed.origin}")
    private String url;

    @Bean
    public SecurityFilterChain Intercept(HttpSecurity http) throws Exception
    {
        http
                .cors(cors->cors.configurationSource(hehe()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/SignUp", "/api/Otp", "/api/Login", "/api/show/**" ).permitAll()
                        .requestMatchers("/actuator/health").permitAll()
                        .anyRequest().authenticated()    // add krne waali kr di authenticated.
                );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource hehe()
    {
        CorsConfiguration cs = new CorsConfiguration() ;
        cs.setAllowedOrigins(List.of(url));
        cs.setAllowedMethods(List.of("GET","POST","DELETE","PUT","OPTIONS"));
        cs.setAllowedHeaders(List.of("*"));
        cs.setMaxAge(3600L);
        cs.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource ccs = new UrlBasedCorsConfigurationSource();
        ccs.registerCorsConfiguration("/**",cs);

        return ccs;
    }

}

