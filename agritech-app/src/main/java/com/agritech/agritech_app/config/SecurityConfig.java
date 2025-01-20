package com.agritech.agritech_app.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.agritech.agritech_app.service.PasswordEncoderService;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("Hi");
		http.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/public/**", "/assets/css/**", "/assets/img/**", "/assets/js/**",
						"/assets/vendor/css/**", "/assets/vendor/css/pages/**",
						"/assets/vendor/fonts/**","/assets/vendor/fonts/boxicons/**",
						"/assets/vendor/js/**",
						"/assets/vendor/libs/**",
						"/assets/vendor/libs/apex-charts/**", "/assets/vendor/libs/highlight/**", "/assets/vendor/libs/jquery/**", "/assets/vendor/libs/masonry/**","/assets/vendor/libs/perfect-scrollbar/**","/assets/vendor/libs/popper/**",
						"/dist/css/**", "/dist/libs/**", "/dist/js/**", "/dist/fonts/**",
						"/libs/apex-charts/**", "/libs/highlight/**", "/libs/jquery/**", "/libs/masonry/**","/libs/perfect-scrollbar/**","/libs/popper/**",
						"/scss/_bootstrap-extended/**","/scss/_bootstrap-extended/forms/**","/scss/_bootstrap-extended/mixins/**",
						"/scss/_components/**","/scss/_components/mixins/**",
						"/scss/_custom-variables/**",
						"/scss/_theme/**",
						"/scss/pages/**",
						"/tasks/**",
						"/fonts/**", "/dist/**", "/css/**", "/js/**", "/static/**",
						"/index.html", "/html/**","/api/authentication/register","/login", "/auth/**","/auth/login")
				.permitAll().anyRequest().authenticated().and()
	            .httpBasic();
		return http.build();
	}
	
	
	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(username -> new org.springframework.security.core.userdetails.User(
                           username, passwordEncoder.encode("password"), new ArrayList<>()))
                   .passwordEncoder(passwordEncoder)
                   .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public PasswordEncoderService passwordEncoderService() {
        return new PasswordEncoderService();
    }
}
