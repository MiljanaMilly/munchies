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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService customUserDetailsService;


    @Autowired
    private DataSource dataSource;

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
                    "/createnewgrouporder/**",
                    "/newgrouporder/**",
                    "/api/**",
                    "/viewrestdetails/**",
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
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/home").deleteCookies("my-remember-me-cookie").permitAll().and()
            .rememberMe()
            .rememberMeCookieName("my-remember-me-cookie")
            .tokenRepository(persistentTokenRepository())
            .tokenValiditySeconds(24 * 60 * 60)
            .and()
            .exceptionHandling()
    ;
    }

    PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }


}
