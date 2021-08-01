package dmdev.lesson26.atomic;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {

    public static void main(String[] args) {
        AtomicInteger value = new AtomicInteger();
        int integer = value.incrementAndGet();
        System.out.println(value);

        int newValue = value.addAndGet(20);
        System.out.println(newValue);
        Unsafe unsafe = Unsafe.getUnsafe();

        //unsafe.
    }
}
