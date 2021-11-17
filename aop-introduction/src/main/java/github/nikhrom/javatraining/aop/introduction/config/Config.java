package github.nikhrom.javatraining.aop.introduction.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("github.nikhrom.javatraining.aop.introduction")
@EnableAspectJAutoProxy
@PropertySource("classpath:application.properties")
public class Config {
}
