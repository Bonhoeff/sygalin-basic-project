package com.sygalin.basicproject.feignclients;

import feign.RequestLine;

import java.util.List;

public interface ExampleFeignClient {
    @RequestLine("GET /posts")
List<ExampleModelForFeignClient> getPosts();
}
