package de.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebAuthorizationSecurityConfig {

	private final KeycloakLogoutHandler keycloakLogoutHandler;

	WebAuthorizationSecurityConfig(KeycloakLogoutHandler keycloakLogoutHandler) {
		this.keycloakLogoutHandler = keycloakLogoutHandler;
	}

	@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
	String jwkSetUri;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


		httpSecurity.oauth2Login().and().logout().addLogoutHandler(keycloakLogoutHandler).logoutSuccessUrl("/");

		httpSecurity.logout().addLogoutHandler(keycloakLogoutHandler).and()

				.authorizeHttpRequests().requestMatchers("/login").permitAll().requestMatchers("/logout").permitAll()
				.requestMatchers(HttpMethod.POST, "/search").authenticated() //
				.requestMatchers(HttpMethod.GET, "/api/**").authenticated()//
				.requestMatchers("/admin").hasRole("admin") //
				.requestMatchers("/h2-console/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/delete/**", "/new-video").authenticated()

				//
				.anyRequest().authenticated().and().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

		return httpSecurity.build();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
	}

}