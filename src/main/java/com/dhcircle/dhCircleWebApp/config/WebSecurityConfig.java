package com.dhcircle.dhCircleWebApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dhcircle.dhCircleWebApp.webservice.user.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
	        .antMatchers("/static/**").permitAll()
	        .antMatchers("/css/**").permitAll()
	        .antMatchers("/js/**").permitAll()
	        .antMatchers("/img/**").permitAll()
	        .antMatchers("/fonts/**").permitAll()
	        .antMatchers("/assets/**").permitAll()
	        .antMatchers("/json/**").permitAll()
	        .antMatchers("/access-denied").permitAll()
	        .antMatchers("/confirm-account").permitAll()
        	.antMatchers("/").permitAll()
        	.antMatchers("/registration").permitAll()
        	.antMatchers("/email").permitAll()
        	.antMatchers("/login").permitAll()
        	.antMatchers("/view/**").permitAll()
        	.antMatchers("/api/**").permitAll()
        	.antMatchers("/admin/**").hasAnyAuthority("ADMIN", "SUPER ADMIN","SUBSCRIBER")
            .anyRequest().authenticated()
            .and()
            .csrf()
            .disable()
            .formLogin().loginPage("/login")
            .failureUrl("/login?error=true")
            .defaultSuccessUrl("/admin/home")
            .usernameParameter("user_name")
            .passwordParameter("password")
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login")
            .and()
            .exceptionHandling()
            .accessDeniedPage("/access-denied")
            ;
    }
}
