package github.nikhrom.javatraining.advanced_hibernate.util;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import github.nikhrom.javatraining.advanced_hibernate.converter.BirthdayConverter;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.registerTypeOverride(new JsonBinaryType());
        configuration.addAttributeConverter(BirthdayConverter.class);
        configuration.configure();

        return configuration.buildSessionFactory();
    }

}
