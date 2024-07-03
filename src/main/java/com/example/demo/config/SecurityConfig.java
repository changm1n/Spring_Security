package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((auth) -> auth //람다식으로 작성
                        .requestMatchers("/","/login","/join").permitAll() //PermitAll 아무런 조건 없이 모두 허용
                        .requestMatchers("/admin").hasRole("ADMIN") //hasRole 특정 role을 가지고 있어야만 허용
                        .requestMatchers("/my/**").hasAnyRole("ADMIN","USER") //hasAnyRole role여러개 설정 가능
                        .anyRequest().authenticated() // authenticated -> 로그인한 사용자만 접근 가능
                ); //위에서부터 동작하기때문에 순서도 중요

        http
                .formLogin((auth)-> auth.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .permitAll()
                );

        http
                .csrf((auth) -> auth.disable());



        return http.build();
    }

}
