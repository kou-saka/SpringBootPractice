package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.AdminDetailsService;

@Configuration(proxyBeanMethods = false) //プロキシがデフォルトでONだからfalseにする
public class SecurityConfig {

  @Bean
  public DaoAuthenticationProvider authProvider(
      AdminDetailsService detailsSvc,	//ユーザー情報(パスワードなど)を取得する
      PasswordEncoder pwEncoder) {		//入力パスワードと保存済みパスワードのマッチングをする
    DaoAuthenticationProvider p = new DaoAuthenticationProvider();
    p.setUserDetailsService(detailsSvc);
    p.setPasswordEncoder(pwEncoder);
    return p;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(
		  HttpSecurity http, 
		  DaoAuthenticationProvider authProvider) throws Exception {	//チェック例外？
      http
        .authenticationProvider(authProvider)
        .authorizeHttpRequests(authz -> authz
          .requestMatchers("/admin/signup","/admin/signin","/css/**","/js/**").permitAll()
          .requestMatchers("/admin/**").hasRole("ADMIN")
          .anyRequest().authenticated()
        )
        .formLogin(f -> f
          .loginPage("/admin/signin")
          .loginProcessingUrl("/admin/signin")
          .defaultSuccessUrl("/admin/contacts", true)
          .failureUrl("/admin/signin?error")
          .permitAll()
        )
        .logout(l -> l
          .logoutUrl("/admin/signout")
          .logoutSuccessUrl("/admin/signin")
          .permitAll()
        );
      return http.build();
  }


}
