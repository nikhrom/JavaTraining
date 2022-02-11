package github.nikhrom.javatraining.advanced_hibernate.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);

}
