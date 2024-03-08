package com.sygalin.basicproject.security;

import com.sygalin.basicproject.configurations.AppException;

import java.util.ArrayList;
import java.util.Map;

public interface TokenManager {
    String generateToken(Map<String,String> claims);
    boolean isCorrectToken(String token);
    Map<String,String> getClaims(ArrayList<String> claimsKeys, String token) throws Exception;
    void setForUser(String forUser);
}
