package com.javalearn;

import java.util.*;
import java.util.stream.Collectors;

public class Java8_C1 {
    public static void main(String[] args) {
        test1a();
        test1b();
        test1c();
        test1d();
        test1e();
        test2a();
        test2b();
        test2c();
    }

    // 1a. Group Words by First Letter
    public static void test1a() {
        List<String> words = List.of("apple", "banana", "avocado", "cherry", "blueberry", "apricot");

        Map<Character, List<String>> grouped = words.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0)));

        System.out.println("1a. Grouped Words by First Letter:");
        grouped.forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    // 1b. Find the second-highest length in a given sentence of words
    public static void test1b() {
        String sentence = "Java is a powerful programming language";

        Optional<Integer> second = Arrays.stream(sentence.split(" "))
                .map(String::length)               // word → length
                .distinct()                        // remove duplicate lengths
                .sorted(Comparator.reverseOrder()) // highest first
                .skip(1)                           // skip highest
                .findFirst();                      // grab second highest

        second.ifPresent(System.out::println); // 8
    }

    // 1c. Filter Valid Integers from List of Strings
    public static void test1c() {
        List<String> strings = List.of("123", "abc", "456", "78.9", "0", "-42");

        List<Integer> validIntegers = strings.stream()
                .filter(s -> s.matches("-?\\d+")) // regex for valid integers
                .map(Integer::parseInt)           // convert to Integer
                .collect(Collectors.toList());

        System.out.println("Valid Integers: " + validIntegers); // [123, 456, 0, -42]
    }

    // 1d. Convert Sentence to Hashtag
    public static void test1d() {
        String sentence = "Java is a powerful programming language";

        String hashtag = Arrays.stream(sentence.split(" "))
                .map(String::toLowerCase)         // convert to lowercase
                .map(w -> " #" + w)                // prepend '#'
                .collect(Collectors.joining());   // join without spaces

        System.out.println("Hashtag: " + hashtag); // #java#is#a#powerful#programming#language
    }

    // 1e. Find Duplicate Elements
    public static void test1e() {
        List<String> words = List.of("apple", "banana", "avocado", "cherry", "blueberry", "apricot", "banana", "cherry");

        Map<String, Long> duplicates = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting())); // [apple=1, banana=2, avocado=1, cherry=2, blueberry=1, apricot=1]

        List<String> duplicateWords = duplicates.entrySet().stream()
                .filter(e -> e.getValue() > 1)  // [banana=2, cherry=2]
                .map(Map.Entry::getKey)                              // [banana, cherry]
                .collect(Collectors.toList());

        System.out.println("Duplicate Words: " + duplicateWords); // [banana, cherry]
    }

    // 2a. Sliding Window Sum (sum of current + previous k-1 elements)
    // Given an integer array and an integer k, for each index, calculate the sum of the current element and up to the previous k-1 elements
    // Group Words by First Letter
    public static void test2a() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int k = 3;
        int[] result = new int[arr.length];
        int windowSum = 0;

        for (int i = 0; i < arr.length; i++) {
            windowSum += arr[i]; // Add current element

            if (i >= k) {
                windowSum -= arr[i - k]; // Remove the element that is out of the window
            }

            result[i] = windowSum; // Store the sum for the current index
        }

        System.out.println(Arrays.toString(result));
    }

    // 2b. Merge Two Sorted Arrays
    public static void test2b() {
        int[] a = {1, 3, 5, 7};
        int[] b = {2, 4, 6, 8};
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) result[k++] = a[i++];
            else               result[k++] = b[j++];
        }

        // Copy remaining elements
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        System.out.println(Arrays.toString(result));    // [1, 2, 3, 4, 5, 6, 7, 8]
    }

    // 2c. Reverse a Character Array
    public static void test2c() {
        char[] arr = {'J', 'a', 'v', 'a', '!'};

        int left = 0, right = arr.length - 1;
        while (left < right) {
            char temp = arr[left];
            arr[left]  = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        System.out.println(new String(arr)); // !avaJ

    }
}
