package github.nikhrom.javatraining.spring.mvc_hibernate.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Setter;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/application.properties")
public class HibernateConfig {

    private Environment environment;

    private static final String JDBC_DRIVER_KEY = "jdbc.driver";
    private static final String JDBC_URL_KEY = "jdbc.url";
    private static final String DATABASE_PASSWORD_KEY = "database.password";
    private static final String DATABASE_USERNAME_KEY = "database.username";

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean(destroyMethod = "close")
    @SneakyThrows
    ComboPooledDataSource dataSource(){
        var dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(environment.getProperty(JDBC_DRIVER_KEY));
        dataSource.setJdbcUrl(environment.getProperty(JDBC_URL_KEY));
        dataSource.setPassword(environment.getProperty(DATABASE_PASSWORD_KEY));
        dataSource.setUser(environment.getProperty(DATABASE_USERNAME_KEY));
        return dataSource;
    }

    @Bean
    LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        var sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("github.nikhrom.javatraining.spring.mvc_hibernate");
        var properties = new Properties();
        properties.setProperty("hibernate.dialect", PostgreSQL10Dialect.class.getCanonicalName());
        properties.setProperty("show.sql", "true");
        properties.setProperty("hibernate.connection.pool_size", "10");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

    @Bean
    HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        var manager = new HibernateTransactionManager(sessionFactory);
        return manager;
    }
}
