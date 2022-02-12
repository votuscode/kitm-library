package com.kitm.library.backend.spring.web.config;

import com.kitm.library.backend.domain.user.UserRepository;
import com.kitm.library.backend.spring.web.config.security.JwtTokenFilter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final Logger logger;
  private final UserRepository userRepository;
  private final JwtTokenFilter jwtTokenFilter;

  @Value("${springdoc.api-docs.path}")
  private String restApiDocPath;
  @Value("${springdoc.swagger-ui.path}")
  private String swaggerPath;

  public SecurityConfig(Logger logger, UserRepository userRepository, JwtTokenFilter jwtTokenFilter) {
    super();

    this.logger = logger;
    this.userRepository = userRepository;
    this.jwtTokenFilter = jwtTokenFilter;

    // Inherit security context in async function calls
    SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(username -> userRepository
        .findUserEntityByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(format("User: %s, not found", username))));
  }

  // Set password encoding schema
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Enable CORS and disable CSRF
    http = http.cors().and().csrf().disable();

    // Set session management to stateless
    http = http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and();

    // Set unauthorized requests exception handler
    http = http
        .exceptionHandling()
        .authenticationEntryPoint(
            (request, response, ex) -> {
              logger.error("Unauthorized request - {}", ex.getMessage());
              response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
            }
        )
        .and();

    // Set permissions on endpoints
    http.authorizeRequests()
        // Swagger endpoints must be publicly accessible
        .antMatchers("/").permitAll()
        .antMatchers(format("%s/**", restApiDocPath)).permitAll()
        .antMatchers(format("%s/**", swaggerPath)).permitAll()
        // Our public endpoints
        .antMatchers("/api/public/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
        .antMatchers(HttpMethod.POST, "/api/author/search").permitAll()
        .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
        .antMatchers(HttpMethod.POST, "/api/book/search").permitAll()
        // Our private endpoints
        .anyRequest().authenticated();

    // Add JWT token filter
    http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  // Used by spring security if CORS is enabled.
  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

  // Expose authentication manager bean
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
