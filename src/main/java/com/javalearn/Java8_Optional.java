package com.javalearn;

import java.util.Optional;

/**
 * Optional Class
 * A container object that may or may not contain a non-null value. Helps avoid NullPointerException.
 */
public class Java8_Optional {

    public static String strHello = "Hello";
    public static String strNull = null;
    public static Optional<String> obj = null;

    public static void main(String[] args) {
        obj = Optional.of(strHello);
        if(obj.isPresent()) {
            System.out.println("Value is present: " + obj.get());
        } else {
            System.out.println("Value is not present");
        }

        /*
         * Optional.of(null) - Throws NullPointerException
         */
//        obj = Optional.of(strNull);
//        if(obj.isPresent()) {
//            System.out.println("Value is present: " + obj.get());
//        } else {
//            System.out.println("Value is not present");
//        }

        obj = Optional.ofNullable(strHello);
        if(obj.isPresent()) {
            System.out.println("Value is present: " + obj.get());
        } else {
            System.out.println("Value is not present");
        }

        obj = Optional.ofNullable(strNull);
        if(obj.isPresent()) {
            System.out.println("Value is present: " + obj.get());
        } else {
            System.out.println("Value is not present");
        }
        // ------------------------------------------------------

        // Throws NoSuchElementException if empty
        Optional<String> empty = Optional.empty();
        String value = empty.get();

        // orElse() - provide default value if Optional is empty
        Optional<String> optional = Optional.empty();
        value = optional.orElse("Default Value");
        System.out.println(value); // Output: Default Value

        // orElseThrow() - throw exception if Optional is empty
        Optional<String> optional2 = Optional.empty();
        value = optional2.orElseThrow(() -> new IllegalArgumentException("Value not found"));

        // map() - transform the value if present
        Optional<String> optional3 = Optional.of("Hello");
        Optional<Integer> length = optional3.map(String::length);
        System.out.println(length.orElse(0)); // Output: 5

        // flatMap() - transform the value and return an Optional
        Optional<String> optional4 = Optional.of("Hello");
        Optional<Integer> length4 = optional4.flatMap(s -> Optional.of(s.length()));
        System.out.println(length4.orElse(0)); // Output: 5

        // filter() - return Optional if value matches the predicate
        Optional<String> optional5 = Optional.of("Hello");
        Optional<String> result = optional5.filter(s -> s.length() > 3);
        System.out.println(result.orElse("Not matched")); // Output: Hello


    }
}
