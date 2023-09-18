package de.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class SecurityContextConfig {

	// the security context is copied to the next thread in case of asynchronous
	// calls
//	@Bean
//	public InitializingBean initializingBean() {
//		return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//	}
}
