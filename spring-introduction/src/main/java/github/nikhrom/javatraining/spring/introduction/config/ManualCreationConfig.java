package github.nikhrom.javatraining.spring.introduction.config;

import github.nikhrom.javatraining.spring.introduction.Person;
import github.nikhrom.javatraining.spring.introduction.pet.Cat;
import github.nikhrom.javatraining.spring.introduction.pet.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ManualCreationConfig {

    @Bean
    public Pet catBean(){
        return new Cat();
    }

    @Bean
    public Person personBean() {
        return new Person(catBean());
    }

}
