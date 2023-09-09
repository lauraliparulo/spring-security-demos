package de.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import de.demo.entity.user.UserAccount;
import de.demo.repo.UserAccountRepository;

@Configuration
public class UserManagementSecurityConfig {

	@Bean
	CommandLineRunner initUsers(UserAccountRepository repository) {
		return args -> {
			repository.save(new UserAccount("alice", "1234", "USER"));
			repository.save(new UserAccount("bob", "1234", "ROLE_USER"));
			repository.save(new UserAccount("admin", "1234", "ADMIN"));
		};
	}


	  @Bean
	  public UserDetailsService inMemoryUserDetailsService(UserAccountRepository repo) {
		  
		    UserDetails user =
		            User.builder()
		               .username("alice")
		               .password(passwordEncoder().encode("1234"))
		               .authorities("read")
		               .roles("USER")
		               .build();
		    
		    UserDetails admin =
		            User.builder()
		               .username("admin")
		               .password(passwordEncoder().encode("1234"))
		               .roles("ADMIN")
		               .build();
		    
		    //TODO Database add
		    
		    return new InMemoryUserDetailsManager(user,admin);
		  
		  
	  }
	  
	  
	  //TODO UsernamePasswordAuthenticationToken
	  
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	    }

}
