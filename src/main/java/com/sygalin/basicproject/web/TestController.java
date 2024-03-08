package com.sygalin.basicproject.web;

import com.sygalin.basicproject.configurations.AppException;
import com.sygalin.basicproject.security.TokenManager;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TestController {
    private TokenManager tokenManager;
    @GetMapping("/test")
    public String test() throws Exception {
        try{
            ArrayList<String> keys = new ArrayList<>();
            keys.add("username");
            Map<String, String> claims1 = tokenManager.getClaims(keys, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MDkwMzAwNzgsImlzcyI6IkJBU0lDIiwic3ViIjoiQUNDRVNTIiwidXNlcm5hbWUiOiJkaWV0cmljaCJ9.m2Q_f_Xap4OuqtrPBDopJCs270CtPuYh-CaSt2mB6SU");
        }catch (Exception e) {
            throw new AppException(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
        }
        return "";
    }
}
