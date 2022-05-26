package com.houdaoul.ecom.onlineshopping.config;

import com.houdaoul.ecom.onlineshopping.util.Constants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtTokenProvider jwtTokenProvider;

  private final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeRequests()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/api/products").hasAuthority(Constants.USER_ROLE)
            .antMatchers("/api/cartItems").hasAuthority(Constants.USER_ROLE)
            .antMatchers("/api/cartItems/**").hasAuthority(Constants.USER_ROLE)
            .antMatchers("/api/carts").hasAuthority(Constants.USER_ROLE)
            .antMatchers("/api/carts/**").hasAuthority(Constants.USER_ROLE)
            .antMatchers("/api/users").hasAuthority(Constants.ADMIN_ROLE)
            .anyRequest()
            .authenticated();

    http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}

