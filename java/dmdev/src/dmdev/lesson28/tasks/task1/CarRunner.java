package dmdev.lesson28.tasks.task1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CarRunner {

    public static void main(String[] args) {
        Car car = new Car("Toyota", "Corolla");

        System.out.println(generateInsert(car));


    }

    private static String generateInsert(Car car){
        String template = "INSERT INTO %s.%s (%s) VALUES (%s)";
        Table table = car.getClass().getAnnotation(Table.class);

        Field[] declaredFields = car.getClass().getDeclaredFields();

        String columns = Arrays.stream(declaredFields)
                .filter(field -> field.isAnnotationPresent(Column.class))
                .sorted(Comparator.comparing(Field::getName))
                .map(field -> field.getAnnotation(Column.class).name())
                .collect(Collectors.joining(", "));


        String values = Arrays.stream(declaredFields)
                .filter(field -> field.isAnnotationPresent(Column.class))
                .sorted(Comparator.comparing(Field::getName))
                .map(field -> getMethodName(field))
                .map(method -> {
                    try {
                        return method.invoke(car);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(value -> "'" + value + "'")
                .collect(Collectors.joining(", "));


        return String.format(template, table.scheme(), table.table(), columns, values);
    }

    private static Method getMethodName(Field field){
        try {
            String name = field.getName();
            return Car.class.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
        } catch (NoSuchMethodException e) {
            throw  new RuntimeException(e);
        }
    }

}
