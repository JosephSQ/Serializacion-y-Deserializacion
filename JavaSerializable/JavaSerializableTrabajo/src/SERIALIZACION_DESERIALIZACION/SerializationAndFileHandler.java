package SERIALIZACION_DESERIALIZACION;


/*
Clase principal para serialización, deserialización y manejo de archivos de texto.
Main incluida
*/
import SERIALIZACION_DESERIALIZACION.Person;
import java.io.*;
import java.util.*;

public class SerializationAndFileHandler {
    private static final String ARCHIVO_BINARIO = "personas.ser";
    private static final String ARCHIVO_TEXTO = "personas.txt";

    public static void main(String[] args) {
        List<Person> personas = Arrays.asList(
            new Person("Carmenza", 25, "Bogotá"),
            new Person("Sancho", 30, "Medellín"),
            new Person("Ramiro", 37, "Cali")
        );

        serializarPersonas(personas);
        List<Person> personasDeserializadas = deserializarPersonas();
        escribirArchivoTexto(personasDeserializadas);
        leerArchivoTexto();
    }

    //Serializa una lista de objetos Person en binario//
    public static void serializarPersonas(List<Person> personas) {
        try (ObjectOutputStream binario = new ObjectOutputStream(new FileOutputStream(ARCHIVO_BINARIO))) {
            binario.writeObject(personas);
            System.out.println("Personas serializadas en " + ARCHIVO_BINARIO);
        } catch (IOException e) {
            System.err.println("Error al serializar: " + e.getMessage());
        }
    }

    //Deserializa una lista de objetos Person//
    @SuppressWarnings("unchecked")
    public static List<Person> deserializarPersonas() {
        try (ObjectInputStream texto = new ObjectInputStream(new FileInputStream(ARCHIVO_BINARIO))) {
            List<Person> personas = (List<Person>) texto.readObject();
            System.out.println("Personas deserializadas desde " + ARCHIVO_BINARIO + ", En:");
            return personas;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    //Escribe la información de personas en un archivo de texto//
    public static void escribirArchivoTexto(List<Person> personas) {
        try (BufferedWriter transcripcion = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ARCHIVO_TEXTO), "UTF-8"))) {
            for (Person p : personas) {
                transcripcion.write(p.toString());
                transcripcion.newLine();
            }
            System.out.println("El archivo de texto " + ARCHIVO_TEXTO);
        } catch (IOException e) {
            System.err.println("Error al escribir texto: " + e.getMessage());
        }
    }

    //Lee y muestra el contenido del archivo de texto//
    public static void leerArchivoTexto() {
        System.out.println("\nContenido del archivo de texto:");
        try (BufferedReader Lector = new BufferedReader(new InputStreamReader(new FileInputStream(ARCHIVO_TEXTO), "UTF-8"))) {
            String linea;
            while ((linea = Lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer texto: " + e.getMessage());
        }
    }
}
