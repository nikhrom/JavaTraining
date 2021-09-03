//Дан класс Person с полями firstName, lastName, age.
//Вывести имя самого старшего человека, у которого длина имени
//не превышает 15 символов.

package com.github.nikhrom.javatraining.core.functional;

import java.util.Comparator;
import java.util.List;


public class Task5 {

    public static void main(String[] args) {

        List<Person> persons = List.of(
                new Person("Nikita", "Hromov", 20),
                new Person("Abdulsamedovash", "Jaj", 1),
                new Person("Sasha", "Hromov", 23),
                new Person("Ivan", "Ivanov", 99)
        );

        
        persons.stream()
                .filter(person -> person.getFirstName().length() <= 15)
                .max(Comparator.comparingInt(Person::getAge))
                .ifPresent(System.out::println);

    }

}
