package com.example.carrental.Exception;


public class ApiException extends RuntimeException {
    public ApiException(String msg){
        super(msg);
    }
}
