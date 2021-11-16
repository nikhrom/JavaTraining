package github.nikhrom.javatraining.aop.introduction.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("github.nikhrom.javatraining.aop.introduction")
@EnableAspectJAutoProxy
public class Config {
}
