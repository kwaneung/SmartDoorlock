package com.example.smartdoorlock;

public class singleton {
    private static final singleton instance = new singleton();
    public String URI;
    public String role;

    private singleton() {}

    public static singleton getInstance() {
        return instance;
    }
}
