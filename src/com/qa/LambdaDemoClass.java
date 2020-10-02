package com.qa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaDemoClass {

    public static void main(String[] args) {
        StringFunction exclaim = (s) -> s + "!"; // assign lambda to an interface variable
        StringFunction ask = (s) -> s + "?"; // assign lambda to an interface variable
        printFormatted("Hello", exclaim);
        printFormatted("Hello. Are you there", ask);
//
//        StringFunction swearWord = s -> s + "@*#X&!";
//        printFormatted("You little ", swearWord);
//
//        demoReplaceAll();
//
//        demoStreams();

    }

    // this method’s 2nd parameter is a single-method interface
    public static void printFormatted(String str, StringFunction format) {
        String result = format.run(str); // calling the interface method, runs the lambda
        System.out.println(result);
    }

    public static void demoReplaceAll(){
        List<Integer> intList = Arrays.asList(1,2,3,4,5);

        // 1. old school
        List<Integer> tempList = new ArrayList<Integer>();

        for (int x = 0; x < intList.size(); x++){
            tempList.add(intList.get(x) + 1);
        }
        intList = tempList;

        // 2. with lambdas
//        intList.replaceAll(i -> i + 1);
//        intList.replaceAll(i -> i * 2); // whatever logic we want

        System.out.println(intList);
    }

    public static void demoStreams(){
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        // maps
        List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7);
        Stream<Integer> newList = intList.stream().map(i -> i * 2);
//        newList.forEach(System.out::println);
        newList.forEach(s -> System.out.print(s + " "));

        Stream<Boolean> isEven = intList.stream().map(i -> i % 2 == 0);
        isEven.forEach(s -> System.out.print(s + " "));

        // filter
        Stream<Integer> isEvenFiltered = intList.stream().filter(i -> i % 2 == 0);
        isEvenFiltered.forEach(s -> System.out.print(s + " "));

        // map and filter combined
        System.out.println("\n");
        intList.stream()
                .map(i -> i * i)
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);

// turn the stream into a List by using COLLECT
        List<Integer> m = intList.stream()
                .map(i -> i * i)
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(m);

        // produce summary stats with summarizingInt
        String total = intList.stream()
                .map(i -> i * i)
                .filter(i -> i % 2 == 0)
                .collect(Collectors.summarizingInt(i -> i))
                .toString();
        System.out.println("Statistics: " + total);

        // reduce
        // folds / accumulator
        String[] arrList = {"a", "b", "b", "c","c", "c", "d"};
        List<String> list = new ArrayList<>(Arrays.asList(arrList));
        String concatenated = list.stream()
                .reduce("", String::concat);

        System.out.println(concatenated);

    }

}
