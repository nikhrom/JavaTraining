package github.nikhrom.javatraining.spring.boot.springboot.mapper;

@FunctionalInterface
public interface Mapper<F, T> {
    T mapFrom(F object);
}
