package com.javalearn;

public class Java17_Features {
	public static void main(String[] args) {

		// Test 1: Text blocks (Java 15+, available in Java 17)
		String t = """
					<p>Hi<p>
					<h1>something</p>
				""";
		System.out.println("Text Block Output:\n" + t);
		
		// Test 2: Switch expressions (Java 14+, available in Java 17)
		System.out.println("\nSwitch Expression Demo:");
		howMany(1);
		howMany(2);
		howMany(67);
		
		// Test 3: Sealed classes demo (Java 17)
		demonstrateSealedClasses();
	}

	static void howMany(int k) {
	    switch (k) {
	        case 1  -> System.out.println("  " + k + " -> one");
	        case 2  -> System.out.println("  " + k + " -> two");
	        default -> System.out.println("  " + k + " -> many");
	    }
	}
	
	// Sealed classes (Java 17)
	static void demonstrateSealedClasses() {
		System.out.println("\nSealed Classes Demo:");
		Shape circle = new Circle(5.0);
		Shape rectangle = new Rectangle(4.0, 6.0);
		
		System.out.println("  Circle area: " + getArea(circle));
		System.out.println("  Rectangle area: " + getArea(rectangle));

		System.out.println("  Circle volume: " + getVolume(circle));
		System.out.println("  Rectangle volume: " + getVolume(rectangle));

	}
	
	static double getArea(Shape shape) {
		if (shape instanceof Circle c) {
			return Math.PI * c.radius() * c.radius();
		} else if (shape instanceof Rectangle r) {
			return r.width() * r.height();
		}
		return 0;
	}
	static double getVolume(Shape shape) {
		if (shape instanceof Circle(double radius)) {							// Can be replaced with record pattern
			return Math.PI * radius * radius * radius;
		} else if (shape instanceof Rectangle(double width, double height)) {	// Can be replaced with record pattern
			return width * height * height;
		}
		return 0;
	}

	// Sealed interface (Java 17)
	sealed interface Shape permits Circle, Rectangle {}
	
	record Circle(double radius) implements Shape {}
	record Rectangle(double width, double height) implements Shape {}
}
