package com.place_application.demo.config;

import com.place_application.demo.pojo.Response;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class PermissionHandler {
    @ExceptionHandler(value = { SignatureException.class })
    @ResponseBody
    public Response authorizationException(SignatureException e) {
        Response res = new Response();
        res.setStatus(500);
        res.setInfo(e.getMessage());
        return res;
    }
}

