package com.udacity.jwdnd.course1.cloudstorage.config;

import com.udacity.jwdnd.course1.cloudstorage.service.AuthenticationService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;

@Service
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final AuthenticationService authenticationService;

  public SecurityConfig(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/signup", "/css/**", "/js/**", "/h2-console/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        //                .failureForwardUrl("/login")  // todo: problem was i was using this so
        // login?error was being redirected when throwing error
        .permitAll()
        .defaultSuccessUrl("/home")
        .and()
        .logout()
        .permitAll()
        .and()
        .csrf()
        .disable()
        .headers()
        .frameOptions()
        .disable();
  }
}
