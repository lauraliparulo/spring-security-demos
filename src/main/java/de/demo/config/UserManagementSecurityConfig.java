package de.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultLdapUsernameToDnMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager;

@Configuration
public class UserManagementSecurityConfig {

	 @Bean                                                       
	  public UserDetailsService userDetailsService() {
	    var cs = new DefaultSpringSecurityContextSource(          
	      "ldap://127.0.0.1:33389/dc=springframework,dc=org");
	    cs.afterPropertiesSet();

	    var manager = new LdapUserDetailsManager(cs);             

	    manager.setUsernameMapper(                                
	      new DefaultLdapUsernameToDnMapper("ou=groups", "uid"));

	    manager.setGroupSearchBase("ou=groups");                  
	    
	    return manager;    
	  }
	   
	  //TODO UsernamePasswordAuthenticationToken
	  
	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	    }

}
