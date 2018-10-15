package com.munchies.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/home").setViewName("userhome");
       // registry.addViewController("/admin/home").setViewName("adminhome");
        //registry.addViewController("/403").setViewName("403");
    }
}
