package com.sygalin.basicproject.configurations;

import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.stereotype.Component;

@Component
public class HttpClient {
   public  <T> T http(Class<T> tClass, String url) {
        return Feign.builder().decoder(new GsonDecoder()).target(tClass,url);
    }
}
