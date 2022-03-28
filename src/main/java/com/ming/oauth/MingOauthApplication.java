package com.ming.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@SpringBootApplication
public class MingOauthApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {

        SpringApplication.run(MingOauthApplication.class, args);
    }

    // security filter
    //permitted endpoints에 대한 화이트 리스트 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(a -> a.antMatchers("/", "/error", "/webjars/**")
                .permitAll()
                .anyRequest().authenticated())
                .exceptionHandling(e -> e.authenticationEntryPoint(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                ))
                .oauth2Login();

        // require POST method
        http.logout(l -> l.logoutSuccessUrl("/")
                .permitAll());
    }
}
