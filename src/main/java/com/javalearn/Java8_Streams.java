package com.javalearn;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8_Streams {

    public static final String STR = "ilovejavatechie";
    public static final int[] NUM_ARRAY = {5, 9, 11, 2, 8, 21, 1};

    public static void main(String[] args) {
        createStreams();
        System.out.println("--------\n** intermediateOperations");
        intermediateOperations();
        System.out.println("--------\n** terminalOperations");
        terminalOperations();
    }

    public static void createStreams() {
        // From Collection
        List<Integer> list      = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream  = list.stream();

        List<String> list2      = Arrays.asList("Hi", "Hello");
        Stream<String> stream2  = list2.stream();

        // From Array
        int[] arr = {1, 2, 3, 4, 5};
        IntStream stream3 = Arrays.stream(arr);

        // Using Stream.of()
        Stream<String> stream4 = Stream.of("A", "B", "C");

    }
    public static void intermediateOperations() {
        Integer[] INTEGER_ARRAY = {5, 9, 94, 11, 72, 86, 2, 8, 2, 21, 7, 56, 60, 8};

        // filter, map, distinct, sorted, skip, limit
        List<Integer> result1 = Arrays.asList(INTEGER_ARRAY)
                .stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n*2)
                .distinct()
                .sorted()   // .sorted(Comparator.comparingInt(String::length))
                .skip(1)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Result1 :: " + result1);     // [4, 16, 112, 120, 144, 172, 188] -> skip(1),limit(3) -> [16, 112, 120]

        // Using FlatMap()
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(5, 6)
        );
        List<Integer> flatList = nestedList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
    public static void terminalOperations() {
        Integer[] INTEGER_ARRAY = {15,13,14,12,11,16,17,18,19,20,15,20};
        List<Integer> list = Arrays.asList(INTEGER_ARRAY);

        // 1a. To List
        List<Integer> oList = list.stream()
                .collect(Collectors.toList());
        System.out.println(oList);              // [15, 13, 14, 12, 11, 16, 17, 18, 19, 20, 15, 20]

        // 1b. To Set
        Set<Integer> oSet = list.stream()
                .collect(Collectors.toSet());
        System.out.println(oSet);               // [16, 17, 18, 19, 20, 11, 12, 13, 14, 15]

        // 1c. To Map
        Map<Integer, Integer> oMap = list.stream()      // Handle when there are 2 IDs (collision). The logic will keep existing value only
                .collect(Collectors.toMap(
                        n -> n,
                        n -> n*2,
                        (existing, replacement) -> existing)
                );
        System.out.println(oMap);               // {16=32, 17=34, 18=36, 19=38, 20=40, 11=22, 12=24, 13=26, 14=28, 15=30}

        // 2. forEach() - Perform action on each element
        System.out.print("forEach() :: ");
        list.stream()
                .forEach(n -> System.out.print(n + "\t"));

        list = Arrays.asList(1, 2, 3, 4, 6);

        // 3a. reduce() - Sum - Combine elements to single value
        int sum = list.stream().reduce(0, Integer::sum);
        System.out.print("\nSum :: " + sum);            // 16

        // 3a. reduce() - Multiply - Combine elements to single value
        int product = list.stream().reduce(1, (a, b) -> a * b);
        System.out.print("\nProduct :: " + product);    // 144

        // 3b. reduce() - Max - Combine elements to single value
        int max = list.stream()
                .reduce(Integer::max)
                .orElse(0);
        System.out.print("\nMax :: " + max);            // 6

        // 4. count() - Count elements
        long count = list.stream().count();
        System.out.println("\nCount :: " + count);      // 5

        // 5a. findFirst() - Get first element
        Optional<Integer> firstItem = list.stream().findFirst();
        System.out.print("\nFirst Item :: " + firstItem.get()); // 1

        // 5b. findAny() - Get any element
        Optional<Integer> anyItem = list.stream().findAny();
        System.out.println("\nAny Item :: " + anyItem.get());   // 1

        // 6a. anyMatch - returns true if any element matches
        boolean hasEven = list.stream().anyMatch(n -> n == 6);
        System.out.println("\nHas Even :: " + hasEven);         // true

        // 6b. allMatch - returns true if all elements match
        boolean allPositive = list.stream().allMatch(n -> n > 0);
        System.out.println("All Positive :: " + allPositive);   // true

        // 6c. noneMatch - returns true if no element matches
        boolean noNegative = list.stream().noneMatch(n -> n < 0);
        System.out.println("No Negative :: " + noNegative);   // true
    }

    public static void test2() {

/*      Map<String, List<String>> map = Arrays.asList(str.split("")).stream()
                                .collect(Collectors.groupingBy(c -> c));
        System.out.println(map);    // {a=[a, a], c=[c], t=[t], e=[e, e, e], v=[v, v], h=[h], i=[i, i], j=[j], l=[l], o=[o]}
*/

        // Count occurrence of each character in string
        // ilovejavatechie -> {i=2, l=1, o=1, v=2, e=3, j=1, a=2, t=1, c=1, h=1}
        Map<String, Long> map = Arrays.asList(STR.split("")).stream()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        System.out.println("map::\t\t\t" +map);

        // Find all unique elements from given string
        // ilovejavatechie -> [l, o, j, t, c, h]
        List<String> uniqueList = map.entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .map(x -> x.getKey())  // Map.Entry::getKey
                .collect(Collectors.toList());
        System.out.println("uniqueList::\t" +uniqueList);

        // Find all duplicate element from given string
        // ilovejavatechie -> [i, v, e, a]
        List<String> dupList = map.entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .map(x -> x.getKey())  // Map.Entry::getKey
                .collect(Collectors.toList());
        System.out.println("dupList::\t\t" + dupList);

        // Find SECOND non-repeat char from given string
        // ilovejavatechie -> o     (as i is repeated ahead)
        Optional<String> secondNonRepeatChar = map.entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .map(x -> x.getKey())   // Map.Entry::getKey
                .skip(1)
                .findFirst();
        System.out.println("Second non-repeat char::" + secondNonRepeatChar.get());

        // Find LAST non-repeat char from given string
        // ilovejavatechie -> h
        Optional<String> lastNonRepeatChar = map.entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .map(x -> x.getKey())   // Map.Entry::getKey
                .reduce((a,b) -> b);
        System.out.println("Last non-repeat char::\t" + lastNonRepeatChar.get());

        // ################################################################################

        // Find Longest string in given array
        // "java", "techie", "springboot", "microservices" -> "microservices"
        Optional<String> longestString = Arrays.stream(new String[]{"java", "techie", "springboot", "microservices"})
                .sorted(Comparator.comparing(e -> e.length()))
                .reduce((a,b) -> b);    // OR reduce((a,b) -> a.length > b.length ? a : b)
        System.out.println("longestString:: \t" + longestString.get());

        // ################################################################################

        // Find Second Highest number in arrays
        Optional<Integer> secondHighestNo = Arrays.stream(NUM_ARRAY)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        System.out.println("SecondHighest num::\t\t" + secondHighestNo.get());

        // Find all elements from array which start with 1
        // 5, 9, 11, 2, 8, 21, 1 -> 11, 1
        List<Integer> startWith1List = Arrays.stream(NUM_ARRAY)
                .boxed()
                .filter(x -> String.valueOf(x).startsWith("1"))
                .collect(Collectors.toList());
        System.out.println(startWith1List);

        // Print 2nd, 3rd element of array
        // 5, 9, 11, 2, 8, 21, 1 -> 9, 11
        List<Integer> someNums = Arrays.stream(NUM_ARRAY)
                .boxed()
                .skip(1)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(someNums);

    }

    // Count characters in string
    public static void test1() {
        String s = "ilovejavatechie";
        long count = Arrays.asList(s.split("")).stream().count();
        System.out.println(count);
    }
}
