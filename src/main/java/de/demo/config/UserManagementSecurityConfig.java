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

}
