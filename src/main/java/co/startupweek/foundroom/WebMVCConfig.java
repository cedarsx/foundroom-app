package co.startupweek.foundroom;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMVCConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/swagger/**")
                .addResourceLocations("file:/web/swagger/");
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/swagger/", "/swagger/index.html");
        registry.addRedirectViewController("/swagger", "/swagger/index.html");
    }

}