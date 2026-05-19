package com.example.session10.model.entity;

public class Employee {
    private long id;
    private String fullname;
    private double salary;

    public Employee() {
    }

    public Employee(long id, String fullname, double salary) {
        this.id = id;
        this.fullname = fullname;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
