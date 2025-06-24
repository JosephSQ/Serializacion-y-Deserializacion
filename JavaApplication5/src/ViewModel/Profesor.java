package ViewModel;

import java.util.Random;

public class Profesor extends Person {
    private static final String[] ACTIVITIES = {
        "Corrigiendo exámenes", "Preparando clase",
        "Leyendo artículos", "Tomando café", "Evaluando trabajos"
    };

    public Profesor(String name, int age) {
        super(name, age);
    }

    @Override
    public String getCurrentActivity() {
        return name + " de " + age + " años está: " + ACTIVITIES[new Random().nextInt(ACTIVITIES.length)];
    }
}
