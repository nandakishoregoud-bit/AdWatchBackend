package com.AdWatch.AdWatch.Rewards.App.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileStorageConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configure Spring to serve profile-pics directory from the root of the app
        registry.addResourceHandler("/profile-pics/**")
                .addResourceLocations("file:profile-pics/");
    }
}
