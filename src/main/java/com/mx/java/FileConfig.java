
package com.mx.java;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author tonny
 */
@Configuration
public class FileConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
    
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:/C:/Temp/uploads/");
    }
    
    
}