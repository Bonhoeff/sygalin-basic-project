package com.sygalin.basicproject.feignclients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class ExampleModelForFeignClient {
    private int userId;
    private int id;
    private String title;
    private String body;
}
