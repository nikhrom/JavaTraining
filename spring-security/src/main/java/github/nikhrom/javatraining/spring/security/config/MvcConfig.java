package github.nikhrom.javatraining.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebMvc
@ComponentScan("github.nikhrom.javatraining.spring.security")
public class MvcConfig{

    @Bean
    public ThymeleafViewResolver viewResolver(ISpringTemplateEngine engine){
        var resolver = new ThymeleafViewResolver();
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resolver.setTemplateEngine(engine);
        resolver.setOrder(1);
        return resolver;
    }


    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver resolver){
        var engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        engine.setEnableSpringELCompiler(true);
        engine.addDialect(new SpringSecurityDialect());
        return engine;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        var resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("/WEB-INF/template/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resolver.setCacheable(true);
        return resolver;
    }
}
