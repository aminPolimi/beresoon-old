//package com.poliMobile.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@Configuration
//@EnableWebSecurity
//
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
//
////	private static String REALM="MY_TEST_REALM";
////    
////	@Autowired
////	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//////	  auth.inMemoryAuthentication().withUser("beresUser").password("beres1048").roles("USER");
//// 	  auth.inMemoryAuthentication().withUser("root").password("aabbcc11").roles("ADMIN");
//////	  auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");
////	}
////     
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		http.csrf().disable();
////	  http.authorizeRequests()//.anyRequest().authenticated()
////		.antMatchers("/**").access("hasRole('ROLE_USER')")
////		.and().httpBasic()
////		.and().formLogin().disable();
////	  
////
////	}
////     
////     
////    /* To allow Pre-flight [OPTIONS] request from browser */
////    @Override
////    public void configure(WebSecurity web) throws Exception {
////        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
////    }
//    
//}
