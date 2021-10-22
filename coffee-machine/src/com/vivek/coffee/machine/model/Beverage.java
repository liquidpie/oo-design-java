package com.vivek.coffee.machine.model;

public class Beverage {

    private final String name;

    public Beverage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
