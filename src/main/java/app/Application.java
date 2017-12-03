package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This class represents the application entry point and manages startup parameters.
 */
@SpringBootApplication
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/").allowedOrigins("*");
                registry.addMapping("/user/authenticate").allowedOrigins("*");
                registry.addMapping("/session/getLatest").allowedOrigins("*");
                registry.addMapping("/session/getRange").allowedOrigins("*");
                registry.addMapping("/session/getDetailedRange").allowedOrigins("*");
                registry.addMapping("/session/getDetailedForDate").allowedOrigins("*");
            }
        };
    }
}
