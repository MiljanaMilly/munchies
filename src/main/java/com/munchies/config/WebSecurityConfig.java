package com.munchies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }


    protected void configure(HttpSecurity http) throws Exception{
    http.csrf().disable().headers()
            .and().authorizeRequests()
            .antMatchers("/",
                    "/login",
                    "/home",
                    "/signup",
                    "/createnewgrouporder",
                    "/newgrouporder",
                    "/api/**",
                    "/viewrestdetails",
                    "/ListOfActiveOrders",
                    "/getlistofactiveorders", "/css/**",
                    "/img/**", "/upload-dir",
                    "/uploadnewfile/**",
                    "/files/**",
                    "/sendGroupOrderEmail/**",
                    "/getPageOfRestaurants", "/sortandpage").permitAll()
            .antMatchers("/admin/**")
            .access("hasAuthority('ADMIN')").anyRequest()
            .authenticated()
            .and().exceptionHandling().accessDeniedPage("/403").and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/home")
            .usernameParameter("email").passwordParameter("password")
            .failureUrl("/login?error").permitAll()
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/home").permitAll();
    }


}