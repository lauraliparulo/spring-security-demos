package de.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebAuthorizationSecurityConfig {

	
//		@Bean
//		@Order(1)                                                        
//		public SecurityFilterChain apiFilterChain(HttpSecurity httpSecurity) throws Exception {
//			httpSecurity
//				.securityMatcher("/api/**")                                   
//				.authorizeHttpRequests(authorize -> authorize
//					.anyRequest().hasRole("ADMIN")
//				)
//				.httpBasic(withDefaults());
//			return httpSecurity.build();
//		}
		
	  @Bean
	  SecurityFilterChain formLoginFilterChain(HttpSecurity httpSecurity) throws Exception {
		     	        
		  httpSecurity.authorizeHttpRequests() //
//          .requestMatchers("/").authenticated()
          .requestMatchers("/login").permitAll()
          .requestMatchers("/react").permitAll() //
          .requestMatchers(HttpMethod.POST, "/search").authenticated() //
          .requestMatchers(HttpMethod.GET, "/api/**").authenticated()//
          .requestMatchers("/admin").hasRole("ADMIN") //
          .requestMatchers("/h2-console").hasRole("ADMIN") //
          .requestMatchers(HttpMethod.POST, "/delete/**", "/new-video").authenticated() //
          .anyRequest().authenticated() //
	              .and() //
//	              .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
	              .formLogin(withDefaults()) //
	              .httpBasic(withDefaults());
	    return httpSecurity.build();
	  }
	  
	  
//	   @Bean
//	    public AccessDeniedHandler accessDeniedHandler() {
//	        return new CustomAccessDeniedHandler();
//	    }

}
