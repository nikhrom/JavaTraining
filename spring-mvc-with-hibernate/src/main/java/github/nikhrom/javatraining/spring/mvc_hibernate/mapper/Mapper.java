package github.nikhrom.javatraining.spring.mvc_hibernate.mapper;

@FunctionalInterface
public interface Mapper<F, T>{
    T mapFrom(F object);
}
