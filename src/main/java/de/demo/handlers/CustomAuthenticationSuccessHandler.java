package de.demo.handlers;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        var authorities = authentication.getAuthorities();

        var auth = authorities.stream()
                    //.filter(a -> a.getAuthority().equals("READ"))
                    .findFirst();
        
        System.out.println("AUTH -- "+auth.toString());
       httpServletResponse.sendRedirect("/");
        
//        if (auth.isPresent()) {
//            httpServletResponse.sendRedirect("/");
//        } else {
//            httpServletResponse.sendRedirect("/error");
//        }
    }
}