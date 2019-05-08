/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.resourcehub.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Software Guld
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserDetailsService service;
    
    @Autowired
    public void configureGlobalInDB(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(bCryptPasswordEncoder());
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Autowired
//    public void configureGlobalInMemory(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("requester").password("{noop}password").roles("REQUESTER")
//                .and()
//                .withUser("supplier").password("{noop}password").roles("SUPPLIER")
//                .and()
//                .withUser("admin").password("{noop}password").roles("ADMIN", "SUPPLIER", "REQUESTER");
//    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    
                .authorizeRequests()
                
                    .antMatchers("/suppliers").hasAnyRole("ADMIN", "SUPPLIER") // want the supplier as well
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/").permitAll()
                    .antMatchers("itemDetails**").permitAll() // url is the itemDetails?id=1
                    .antMatchers("/items").permitAll()
                    .antMatchers("/aboutUs").permitAll()
                    .antMatchers("/contactUs").permitAll()
                    .antMatchers("/css/**", "/js/**", "/fonts/**", "/image/**").permitAll()
                    .anyRequest().hasAnyRole("REQUESTER","ADMIN","SUPPLIER")//need to be a supplier/ requester
                .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?login_error=1")
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();          
    }
}
