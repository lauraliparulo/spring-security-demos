package de.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import de.demo.filters.AuthenticationLoggingFilter;
import de.demo.filters.CsrfTokenLogger;
import de.demo.filters.RequestValidationFilter;
import de.demo.handlers.CustomAuthenticationFailureHandler;
import de.demo.handlers.CustomAuthenticationSuccessHandler;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class WebAuthorizationSecurityConfig {

	@Bean(name = "securityFilterChain")
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		// TODO Not working!!!!
		httpSecurity = httpSecurity.csrf(c -> {
			c.ignoringRequestMatchers("/h2-console/**");
		});

		httpSecurity = httpSecurity.cors(withDefaults());

		httpSecurity.oauth2Login();

		httpSecurity.authorizeHttpRequests().requestMatchers("/login").permitAll().requestMatchers("/logout")
				.permitAll().requestMatchers(HttpMethod.POST, "/search").authenticated() //
				.requestMatchers(HttpMethod.GET, "/api/**").authenticated()//
				.requestMatchers("/admin").hasRole("ADMIN") //
				.requestMatchers("/h2-console/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/delete/**", "/new-video").authenticated()
				//
				.anyRequest().authenticated(); //

		return httpSecurity.build();

	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}

//TODO	@Bean
//	    public AccessDeniedHandler accessDeniedHandler() {
//	        return new CustomAccessDeniedHandler();
//	    }
