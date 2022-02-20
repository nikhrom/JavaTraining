package github.nikhrom.javatraining.advanced_hibernate.dao;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class QPredicate {
    private final List<Predicate> predicates = new ArrayList<>();

    public static QPredicate builder(){
        return new QPredicate();
    }


    public <T> QPredicate add(T object, Function<T, Predicate> predicateFunction){
        if(object != null){
            predicates.add(predicateFunction.apply(object));
        }
        return this;
    }

    public Predicate buildAnd(){
        return ExpressionUtils.allOf(predicates);
    }

    public Predicate buildOr(){
        return ExpressionUtils.anyOf(predicates);
    }
}
