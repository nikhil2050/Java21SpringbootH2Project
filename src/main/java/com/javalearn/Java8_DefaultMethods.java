package com.javalearn;

/**
 * Default Methods
 * Methods in interfaces with a body (implementation). Allow adding new functionality to interfaces without breaking existing implementations.
 *
 * ADVANTAGES:
 * Backward compatibility
 * Add functionality without modifying implementing classes
 * Multiple default method inheritance (with care)
 */
public class Java8_DefaultMethods {

    public static void main(String[] args) {
        Car c = new Car();
        c.drive();          // Car.drive()
        c.display();        // Default implementation
    }
}

interface Vehicle {
    default void display() {
        System.out.println("Default implementation");
    }
}
class Car implements Vehicle {
    public void drive() {
        System.out.println("Car.drive()");
    }
}
