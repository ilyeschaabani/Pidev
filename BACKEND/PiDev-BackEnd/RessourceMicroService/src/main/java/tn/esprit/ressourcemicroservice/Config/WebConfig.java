package tn.esprit.ressourcemicroservice.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // L'URL de ton application Angular en développement
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

     @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve files from 'uploads' folder located at the project root
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:./uploads/");
    }
}