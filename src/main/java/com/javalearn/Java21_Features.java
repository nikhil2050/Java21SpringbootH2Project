package com.javalearn;

public class Java21_Features {
    
    public static void main(String[] args) throws InterruptedException {

        // Test 1: Record Patterns (Java 21)
        Point point = new Point(10, 20);

        // Test 2: Pattern matching for switch (Java 21)
        System.out.println("🔀 Pattern Matching Demo:");
        System.out.println("  " + formatObject(42));
        System.out.println("  " + formatObject("Hello"));
        System.out.println("  " + formatObject(point));
        System.out.println("  " + formatObject(null));

        // Test 3: String formatting with text blocks (Preview in Java 21)
        demonstrateStringFormatting();

        // Test 4: Virtual Threads (Java 21)
        demonstrateVirtualThreads();
        
        // Test 5: Sequenced Collections (Java 21)
        demonstrateSequencedCollections();
    }


    // 1. Record Patterns (Java 21)
    record Point(int x, int y) {}
    
    // 2. Pattern Matching for switch (Java 21)
    static String formatObject(Object obj) {
        return switch (obj) {
            case Integer i -> "Integer: " + i;
            case String s -> "String: " + s;
            case Point(int x, int y) -> "Point at (" + x + ", " + y + ")";
            case null -> "null value";
            default -> "Unknown type";
        };
    }
    
    // 3. String Templates (Preview in Java 21)
    static void demonstrateStringFormatting() {
        String name = "Java 21";
        int version = 21;
        
        // Using text blocks (Java 15+, stable in 21)
        String message = """
                ╔════════════════════════════════╗
                ║  Welcome to %s!           ║
                ║  Version: %d                   ║
                ╚════════════════════════════════╝
                """.formatted(name, version);
        
        System.out.println(message);
    }
    
    // 4. Virtual Threads (Java 21)
    static void demonstrateVirtualThreads() throws InterruptedException {
        System.out.println("\n🧵 Virtual Threads Demo:");
        
        // Create and start virtual threads
        Thread vThread1 = Thread.ofVirtual().start(() -> System.out.println("  → Virtual Thread 1: " + Thread.currentThread()));
        
        Thread vThread2 = Thread.ofVirtual().start(() -> System.out.println("  → Virtual Thread 2: " + Thread.currentThread()));
        
        vThread1.join();
        vThread2.join();
        
        System.out.println("  ✓ Both virtual threads completed!");
    }
    
    // 5. Sequenced Collections (Java 21)
    static void demonstrateSequencedCollections() {
        System.out.println("\n📋 Sequenced Collections Demo:");
        
        var list = new java.util.ArrayList<String>();
        list.add("First");
        list.add("Middle");
        list.add("Last");
        
        System.out.println("  First element: " + list.getFirst());
        System.out.println("  Last element: " + list.getLast());
        System.out.println("  Reversed: " + list.reversed());
    }
    
}