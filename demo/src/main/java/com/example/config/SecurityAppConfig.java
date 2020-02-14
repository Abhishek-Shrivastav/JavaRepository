package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityAppConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth
    	.userDetailsService(userDetailsService)
    	.passwordEncoder(bCryptPasswordEncoder());
		
		/*auth.inMemoryAuthentication()
			.withUser("jduser").password("{noop}jdu@123").authorities("ROLE_USER")
			.and()
			.withUser("jdadmin").password("{noop}jda@123").authorities("ROLE_USER","ROLE_ADMIN");*/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.headers()
				.frameOptions().sameOrigin()
			.and()
			.authorizeRequests()
				.antMatchers("/resources/**", "/registration").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/homePage").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/userPage").access("hasRole('ROLE_USER')")
				.antMatchers("/adminPage").access("hasRole('ROLE_ADMIN')")
				//.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/loginPage")
				.defaultSuccessUrl("/homePage")
				.failureUrl("/loginPage?error")
				.usernameParameter("username").passwordParameter("password")				
			.and()
			.logout()
				.logoutSuccessUrl("/loginPage?logout"); 
	}
}
