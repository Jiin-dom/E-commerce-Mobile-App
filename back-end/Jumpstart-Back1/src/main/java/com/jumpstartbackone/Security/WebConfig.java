package com.jumpstartbackone.Security;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path mealUploadDir = Paths.get("./meals/images");
        String mealUploadPath = mealUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/**")
                .addResourceLocations("file:/" + mealUploadPath + "/");
    }
}