package entity;

public class Medico {
    private int id;
    private String nombre;
    private String apellido;
    private int id_especialidad;
    private Especialidad objEspecialidad;

    public Medico() {
    }

    public Medico(String nombre, String apellido, int id_especialidad, Especialidad objEspecialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id_especialidad = id_especialidad;
        this.objEspecialidad = objEspecialidad;
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

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public Especialidad getObjEspecialidad() {
        return objEspecialidad;
    }

    public void setObjEspecialidad(Especialidad objEspecialidad) {
        this.objEspecialidad = objEspecialidad;
    }

    @Override
    public String toString() {
        return "Medico" +
                ", nombre = '" + nombre + '\'' +
                ", apellido = '" + apellido + '\'' +
                ", especialidad = " + objEspecialidad.getNombre();
    }
}
