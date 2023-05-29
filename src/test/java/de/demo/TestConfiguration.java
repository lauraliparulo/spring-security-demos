package de.demo;

//@org.springframework.boot.test.context.TestConfiguration
public class TestConfiguration {

//	@Bean
//	public AlbumService albumService() {
//		return new AlbumService() {
//			// implement methods
//		};
//	}

//  @Bean
//  SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
//    http.authorizeHttpRequests() //
//      .requestMatchers("/resources/**", "/about", "/login").permitAll() //
//      .requestMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN") //
//      .requestMatchers("/db/**").access((authentication, object) -> {
//        boolean anyMissing = Stream.of("ADMIN", "DBA")//
//          .map(role -> hasRole(role).check(authentication, object).isGranted()) //
//          .filter(granted -> !granted) //
//          .findAny() //
//          .orElse(false); //
//        return new AuthorizationDecision(!anyMissing);
//      }) //
//      .anyRequest().denyAll() //
//      .and() //
//      .formLogin() //
//      .and() //
//      .httpBasic();
//    return http.build();
//  }
//
//  
//  @Bean
//  public UserDetailsService userDetailsService() {
//    UserDetailsManager userDetailsManager = //
//      new InMemoryUserDetailsManager();
//    userDetailsManager.createUser( //
//      User.withDefaultPasswordEncoder() //
//        .username("user") //
//        .password("password") //
//        .roles("USER") //
//        .build());
//    userDetailsManager.createUser( //
//      User.withDefaultPasswordEncoder() //
//        .username("admin") //
//        .password("password") //
//        .roles("ADMIN") //
//        .build());
//    return userDetailsManager;
//  }

}