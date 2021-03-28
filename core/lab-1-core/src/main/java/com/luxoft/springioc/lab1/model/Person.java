package com.luxoft.springioc.lab1.model;

public interface Person {
    void setName(String name);
    String getName();
    void sayHello(Person person);
}