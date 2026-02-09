package com.javalearn;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8_Streams {

    public static final String STR = "ilovejavatechie";
    public static final int[] NUM_ARRAY = {5, 9, 11, 2, 8, 21, 1};

    public static void main(String[] args) {
        test2();
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
