package de.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import de.demo.entity.user.UserAccount;
import de.demo.pwencoders.Sha512PasswordEncoder;
import de.demo.repo.UserAccountRepository;

@Configuration
public class UserManagementSecurityConfig {

//	@Bean
//	CommandLineRunner initUsers(UserAccountRepository repository) {
//		return args -> {
//			repository.save(new UserAccount("alice", "1234", "USER"));
//			repository.save(new UserAccount("bob", "1234", "ROLE_USER"));
//			repository.save(new UserAccount("admin", "1234", "ADMIN"));
//		};
//	}


	  @Bean
	  public UserDetailsService inMemoryUserDetailsService(UserAccountRepository repo) {
		  
		    UserDetails alice =
		            User.builder()
		               .username("alice")
		               .password("{sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0")
		               .authorities("READ","WRITE")
		               .roles("USER")
		               .build();
		    
		    UserDetails bob =
		            User.builder()
		               .username("bob")
		               .password("{noop}password")
		               .authorities("READ")
		               .roles("USER")
		               .build();
		    
		    UserDetails admin =
		            User.builder()
		               .username("admin")
		               .password("{bcrypt}$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a") //pass
		               .roles("ADMIN")
		               .build();
		    
		    //TODO Database add
		    
		    return new InMemoryUserDetailsManager(alice,admin,bob);
		    		  
	  }
	  
	  
	  //TODO UsernamePasswordAuthenticationToken
	  
//	  //all the encoders
//	  @Bean
//	  public PasswordEncoder passwordEncoder() {
//	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	  }
	  
	  @Bean
	  public PasswordEncoder delegatingPasswordEncoder() {
	    Map<String, PasswordEncoder> encoders = new HashMap<>();

	    encoders.put("noop", NoOpPasswordEncoder.getInstance());
	    encoders.put("bcrypt", new BCryptPasswordEncoder());
	    encoders.put("scrypt", new SCryptPasswordEncoder(5, 2, 1, 10, 10));
	    encoders.put("sha", new Sha512PasswordEncoder());

	    return new DelegatingPasswordEncoder("bcrypt", encoders);
	  }

}
