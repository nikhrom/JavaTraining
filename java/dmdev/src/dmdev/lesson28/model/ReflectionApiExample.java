package dmdev.lesson28.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionApiExample {

    public static void main(String[] args) {
        testConstructor();

        User user = new User(25, "Ivan");
        testFields(user);
        System.out.println(testMethods(user));
    }

    private static Object testMethods(User user){
        try {
            Method getName = User.class.getMethod("getName");
            return getName.invoke(user);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void testConstructor(){
        try {
            Constructor<User> constructor = User.class.getConstructor(int.class, String.class);
            User petr = constructor.newInstance(5, "Petr");
            System.out.println(petr);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static void testFields(Object object){
        Field[] declaredFields = object.getClass().getSuperclass().getDeclaredFields();
        for(Field declaresField: declaredFields){
            try {
                declaresField.setAccessible(true);
                Object value = declaresField.get(object);
                System.out.println(value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private class Test1{

    }

    private static class Test3{

    }

    private enum Test2{
        ONE
    }

}
