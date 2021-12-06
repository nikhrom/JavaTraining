package github.nikhrom.javatraining.spring.mvc.introduction.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "github.nikhrom.javatraining.spring.mvc.introduction")
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public ThymeleafViewResolver viewResolver(ISpringTemplateEngine engine){
        var resolver = new ThymeleafViewResolver();
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resolver.setTemplateEngine(engine);
        resolver.setOrder(1);
        return resolver;
    }


    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver resolver,
                                               MessageSource messageSource){
        var engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        engine.setEnableSpringELCompiler(true);
        engine.setMessageSource(messageSource);
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

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        var messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasename("classpath:translations");
        messageSource.setDefaultLocale(Locale.ENGLISH);
        messageSource.setFallbackToSystemLocale(false);

        return messageSource;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    };

    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }
}
