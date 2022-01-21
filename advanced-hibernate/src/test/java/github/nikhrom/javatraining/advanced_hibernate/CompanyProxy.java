package github.nikhrom.javatraining.advanced_hibernate;

import github.nikhrom.javatraining.advanced_hibernate.entity.Company;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.hibernate.proxy.ProxyConfiguration;
import org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor;

public class CompanyProxy extends Company
        implements HibernateProxy, ProxyConfiguration
{

    private ByteBuddyInterceptor interceptor;

    @Override
    public Object writeReplace() {
        return null;
    }

    @Override
    public LazyInitializer getHibernateLazyInitializer() {
        return interceptor;
    }

    @Override
    public void $$_hibernate_set_interceptor(Interceptor interceptor) {

    }
}
