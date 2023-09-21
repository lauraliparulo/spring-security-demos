package de.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableJpaAuditing
public class RestReactDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestReactDemoApplication.class, args);
  }

  
  @Bean
  public RestTemplate restTemplate() {
      return new RestTemplate();
  }
  
}
