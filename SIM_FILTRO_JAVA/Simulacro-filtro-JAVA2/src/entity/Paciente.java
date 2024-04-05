package entity;

import java.util.Date;

public class Paciente {
    private int id;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private String documento_identidad;

    public Paciente() {
    }

    public Paciente(String nombre, String apellido, String fecha_nacimiento, String documento_identidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.documento_identidad = documento_identidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDocumento_identidad() {
        return documento_identidad;
    }

    public void setDocumento_identidad(String documento_identidad) {
        this.documento_identidad = documento_identidad;
    }

    @Override
    public String toString() {
        return "Paciente" +
                " nombre = '" + nombre + '\'' +
                ", apellido = '" + apellido + '\'' +
                ", fecha_nacimiento = '" + fecha_nacimiento + '\'' +
                ", documento_identidad = '" + documento_identidad + '\'' +
                '}';
    }
}