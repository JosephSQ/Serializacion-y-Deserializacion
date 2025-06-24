package ViewModel;

import java.io.Serializable;

public abstract class Person implements Serializable {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract String getCurrentActivity();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " (" + getClass().getSimpleName() + "), edad: " + age;
    }
}

