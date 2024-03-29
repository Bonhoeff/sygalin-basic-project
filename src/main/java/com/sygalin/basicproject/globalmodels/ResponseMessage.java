package com.sygalin.basicproject.globalmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;

@AllArgsConstructor @NoArgsConstructor @Data
public class ResponseMessage<T> {
    private int status;
    private int code;
    private String error;
    private String message;
    private ArrayList<T> data=new ArrayList<>();
}
