package github.nikhrom.javatraining.spring.security.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.nio.charset.StandardCharsets;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException{
        var webContext = new AnnotationConfigWebApplicationContext();

        webContext.setConfigLocation("github.nikhrom.javatraining.spring.security.config");
        servletContext.addListener(new ContextLoaderListener(webContext));

        var dispatcherServlet = servletContext.addServlet("dispatcherServlet",
                new DispatcherServlet(webContext));

        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

        var encodingFilter = servletContext.addFilter("encodingFilter",
                new CharacterEncodingFilter(StandardCharsets.UTF_8.name()));

        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
    }
}
