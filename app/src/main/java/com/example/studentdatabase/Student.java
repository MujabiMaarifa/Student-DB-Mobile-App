package com.example.studentdatabase;

public class Student {
    private int id;
    private String name;
    private String course;
    private int year;

    public Student(int id, String name, String course, int year) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.year = year;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + course + " | " + year;
    }
}
