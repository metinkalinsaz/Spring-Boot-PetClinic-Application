package com.javaegitimleri.petclinic;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@Order(value = 1) //Diger Security Configurition'dan daha önce çalışacak
public class RestSecurityConfiguration extends AbstractSecurityConfiguration {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.antMatcher("/rest/**");
        http.antMatcher("/rest/**").authorizeRequests().antMatchers("/rest/**").access("hasRole('EDITOR')");
        http.csrf().disable();
        http.httpBasic();
    }
}