package de.demo.config;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import de.demo.entity.user.UserAccount;
import de.demo.repo.UserAccountRepository;

@Configuration
public class UserManagementSecurityConfig {

	  @Bean
	  public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
		  return new JdbcUserDetailsManager(dataSource);
		  
	  }
	  
	  
	  //TODO UsernamePasswordAuthenticationToken
	  
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	    }

}
