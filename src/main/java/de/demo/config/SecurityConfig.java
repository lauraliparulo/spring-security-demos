package de.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import de.demo.entity.user.UserAccount;
import de.demo.repo.UserAccountRepository;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	CommandLineRunner initUsers(UserAccountRepository repository) {
		return args -> {
			repository.save(new UserAccount("alice", "pass", "USER"));
			repository.save(new UserAccount("bob", "pass", "ROLE_USER"));
			repository.save(new UserAccount("admin", "pass", "ADMIN"));
		};
	}


	  @Bean
	  public UserDetailsService userDetailsService(UserAccountRepository repo) {
		  
		    UserDetails user =
		            User.builder()
		               .username("user")
		               .password(passwordEncoder().encode("password"))
		               .roles("USER")
		               .build();
		    UserDetails admin =
		            User.builder()
		               .username("admin")
		               .password(passwordEncoder().encode("password"))
		               .roles("USER","ADMIN")
		               .build();
		    return new InMemoryUserDetailsManager(user,admin);
		  
		  
	  }
	  
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	    }

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
