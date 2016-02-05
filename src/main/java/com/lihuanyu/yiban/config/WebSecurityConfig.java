package com.lihuanyu.yiban.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by skyADMIN on 16/2/5.
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login", "/webjars/**").permitAll()
                .anyRequest().authenticated().and().logout().logoutSuccessUrl("/").permitAll();
    }
}
