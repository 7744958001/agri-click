package com.agritech.agritech_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.agritech.agritech_app.service.CustomLoginSuccessHandler;
import com.agritech.agritech_app.service.CustomUserDetailsService;
import com.agritech.agritech_app.service.PasswordEncoderService;
import com.agritech.agritech_app.util.JwtFilter;

@Configuration
public class SecurityConfig {

	private final CustomLoginSuccessHandler successHandler;
	private final CustomUserDetailsService userDetailsService;

	public SecurityConfig(CustomLoginSuccessHandler successHandler, CustomUserDetailsService userDetailsService) {
		this.successHandler = successHandler;
		this.userDetailsService = userDetailsService;
	}

	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("Hi");
		http.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/public/**", "/assets/css/**", "/assets/img/**", "/assets/js/**",
						"/assets/vendor/css/**", "/assets/vendor/css/pages/**", "/assets/vendor/fonts/**",
						"/assets/vendor/fonts/boxicons/**", "/assets/vendor/js/**", "/assets/vendor/libs/**",
						"/assets/vendor/libs/apex-charts/**", "/assets/vend" + "or/libs/highlight/**",
						"/assets/vendor/libs/jquery/**", "/assets/vendor/libs/masonry/**",
						"/assets/vendor/libs/perfect-scrollbar/**", "/assets/vendor/libs/popper/**", "/dist/css/**",
						"/dist/libs/**", "/dist/js/**", "/dist/fonts/**", "/libs/apex-charts/**", "/libs/highlight/**",
						"/libs/jquery/**", "/libs/masonry/**", "/libs/perfect-scrollbar/**", "/libs/popper/**",
						"/scss/_bootstrap-extended/**", "/scss/_bootstrap-extended/forms/**",
						"/scss/_bootstrap-extended/mixins/**", "/scss/_components/**", "/scss/_components/mixins/**",
						"/scss/_custom-variables/**", "/scss/_theme/**", "/scss/pages/**", "/tasks/**", "/fonts/**",
						"/dist/**", "/css/**", "/js/**", "/static/**", "/index.html", "/html/**",
						"/api/authentication/register", "/login", "/auth/**", "/auth/login")
				.permitAll().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
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
