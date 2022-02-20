package github.nikhrom.javatraining.spring.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
            .withUser(userBuilder.username("sasha")
                .password("sasha")
                .roles("IT")
            )
            .withUser(userBuilder.username("ivan")
                .password("ivan")
                .roles("HR")
            )
            .withUser(userBuilder.username("nikita")
                .password("nikita")
                .roles("IT", "HR")
            );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("IT", "HR")
                .antMatchers("/hr").hasAnyRole("HR")
                .antMatchers("/it").hasAnyRole("IT")
        .and().formLogin().permitAll();

    }
}
