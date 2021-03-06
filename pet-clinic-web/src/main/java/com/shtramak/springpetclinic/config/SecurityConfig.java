package com.shtramak.springpetclinic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll() // This will be your home screen URL
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .defaultSuccessUrl("/findOwners.html")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
