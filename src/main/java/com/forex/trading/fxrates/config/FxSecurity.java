package com.forex.trading.fxrates.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Implements the basic authentication with credentials admin / admin .
 *  Applies to the uri paths /fx /fxdetails. Prompts a login page where
 *  credentials has to be given before traversing to other pages. 
 *     
 * @author muralee
 *
 */


@Configuration
@EnableWebSecurity
public class FxSecurity extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/fxdetails","/fx/**").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
            throws Exception 
    {
		auth.inMemoryAuthentication()
        .withUser("admin").password("{noop}admin").roles("ADMIN");
        
    }
}