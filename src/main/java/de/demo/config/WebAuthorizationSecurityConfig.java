package de.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import de.demo.handlers.CustomAuthenticationFailureHandler;
import de.demo.handlers.CustomAuthenticationSuccessHandler;


@Configuration
@EnableMethodSecurity
//@EnableWebSecurity
public class WebAuthorizationSecurityConfig {
	
    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

	@Bean
	SecurityFilterChain formLoginFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.formLogin().successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler).and().httpBasic(c -> {
					c.realmName("OTHER");
					c.authenticationEntryPoint(new CustomEntryPoint());
				});

		httpSecurity.authorizeHttpRequests() //
//          .requestMatchers("/").authenticated()
				.requestMatchers("/login").permitAll().requestMatchers(HttpMethod.POST, "/search").authenticated() //
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

//TODO	@Bean
//	    public AccessDeniedHandler accessDeniedHandler() {
//	        return new CustomAccessDeniedHandler();
//	    }

}
