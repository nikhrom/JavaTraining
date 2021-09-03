package com.github.nikhrom.javatraining.core.serialization;

import com.github.nikhrom.javatraining.core.functional.Person;

import java.io.*;
import java.nio.file.Path;

public class SerializationDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Path path = Path.of("resources", "student.out");

        //writeObject(path);

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toFile()))) {

            Object object = in.readObject();

            System.out.println(object );

        }



    }

    private static void writeObject(Path path) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            Person sergey = new Person("Sergey", 26);

            out.writeObject(sergey);
        }
    }

}
