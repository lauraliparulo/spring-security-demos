package de.demo.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.addHeader("message", "Luke, I am your father!");
//        httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
//        httpServletResponse.sendError(HttpStatus.value());
    }
  
    
}
