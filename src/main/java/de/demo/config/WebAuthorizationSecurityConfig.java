package de.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

		httpSecurity.oauth2Login(c -> {
            c.clientRegistrationRepository(clientRepository());
        });

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

	private ClientRegistrationRepository clientRepository() {
		var c = clientRegistration();
		return new InMemoryClientRegistrationRepository(c);
	}

	private ClientRegistration clientRegistration() {
		return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("41979400b93e0f39ae16")
				.clientSecret("80da7303c4a8e6adb4ea020feb49694b417a9852").build();
	}
}