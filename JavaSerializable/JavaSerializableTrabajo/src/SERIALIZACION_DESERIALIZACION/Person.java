package SERIALIZACION_DESERIALIZACION;

/*
Clase Person que implementa Serializable.
Representa una persona con nombre, edad y dirección.
*/
import java.io.*;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private int edad;
    private String direccion;

    public Person(String nombre, int edad, String direccion) {
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getDireccion() { return direccion; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    public String toString() {
        return nombre + ", " + edad + ", " + direccion;
    }
}
