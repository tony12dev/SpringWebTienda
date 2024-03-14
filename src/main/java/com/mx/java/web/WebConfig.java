/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.java.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

/**
 *
 * @author tonny
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
     registro.addViewController("/api/cliente").setViewName("index");
     registro.addViewController("/api/producto").setViewName("producto");
     registro.addViewController("/login");
    }
    
    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }
    
    
}
