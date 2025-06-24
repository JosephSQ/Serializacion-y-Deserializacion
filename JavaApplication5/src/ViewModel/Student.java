package ViewModel;

import java.util.Random;

public class Student extends Person {
    private static final String[] ACTIVITIES = {
        "Estudiando matemáticas", "Leyendo apuntes",
        "Programando en Java", "Viendo clases virtuales", "Descansando un rato"
    };

    public Student(String name, int age) {
        super(name, age);
    }

    @Override
    public String getCurrentActivity() {
        return name  + " de " + age + " años está: " + ACTIVITIES[new Random().nextInt(ACTIVITIES.length)];
    }
}