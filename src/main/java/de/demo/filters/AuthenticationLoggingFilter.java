package de.demo.filters;

import java.io.IOException;

import org.jboss.logging.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationLoggingFilter extends OncePerRequestFilter {

	private final Logger logger =
            Logger.getLogger(AuthenticationLoggingFilter.class.getName());

	  @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain filterChain) throws ServletException, IOException {

	        String requestId = request.getHeader("Request-Id");

	        logger.info("Successfully authenticated request with id " +  requestId);

	        filterChain.doFilter(request, response);     
	  }
	}