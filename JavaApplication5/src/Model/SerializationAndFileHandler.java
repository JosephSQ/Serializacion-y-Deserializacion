package Model;

import ViewModel.Person;

import java.io.*;
import java.util.ArrayList;

public class SerializationAndFileHandler {
    private static final String FILE_NAME = "personas.ser";

    public static void guardarPersona(Person persona) {
        ArrayList<Person> personas = cargarPersonas();
        personas.add(persona);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(personas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Person> cargarPersonas() {
        File archivo = new File(FILE_NAME);
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
