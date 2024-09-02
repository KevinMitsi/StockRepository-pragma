package com.kevin.emazon.infraestructure.config.feign;




import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


public interface UserServiceClient {

    @PostMapping ("/api/v1/user/login")
    ResponseEntity<String> getUserData(@RequestHeader("Authorization") String token);
}