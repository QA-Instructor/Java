package com.qa;

// a single method interface
// this can be used as a parameter type to a method and can store a lambda expression
// Calling the interface's method, runs the lambda expression

public interface StringFunction {
    String run(String str);
}
