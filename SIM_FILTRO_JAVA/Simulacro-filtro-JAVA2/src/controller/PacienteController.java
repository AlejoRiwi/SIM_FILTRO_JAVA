
package controller;

import entity.Medico;
import entity.Paciente;
import model.PacienteModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class PacienteController {
    public static void insert() {
        String nombre = JOptionPane.showInputDialog(null, "Ingresa el nombre del paciente");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido del paciente");
        String fecha_nacimiento = JOptionPane.showInputDialog(null, "Ingresa la fecha de nacimiento en el formato Año - mes - dia");
        String documento_identidad = JOptionPane.showInputDialog(null, "Ingresa el numero de documento de identidad");

        instanciaModel().insert(new Paciente(nombre,apellido,fecha_nacimiento,documento_identidad));
    }

    public static PacienteModel instanciaModel () {
        return new PacienteModel();
    }

    public static void getAll() {
        String list = getAll(instanciaModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list) {
        String listString = "Lista de Registro";

        for (Object temp : list) {
            Paciente objPaciente = (Paciente) temp;
            listString += objPaciente.toString() + "\n";
        }
        return listString;
    }

    public static void delete() {
        Object [] opciones =Utils.listToArray(instanciaModel().findAll());
        Paciente objPaciente = (Paciente) JOptionPane.showInputDialog(
                null,
                "Seleccione el paciente que quiere eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        instanciaModel().delete(objPaciente);
    }

    public static void update() {
        Object [] opciones = Utils.listToArray(instanciaModel().findAll());
        Paciente objPaciente = (Paciente) JOptionPane.showInputDialog(
                null,
                "Seleccione el paciente que quiere actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        objPaciente.setNombre(JOptionPane.showInputDialog(null,"ingresa el nuevo nombre ->", objPaciente.getNombre()));
        objPaciente.setApellido(JOptionPane.showInputDialog(null,"ingresa el nuevo apellido ->",
                objPaciente.getApellido()));
        objPaciente.setFecha_nacimiento(JOptionPane.showInputDialog(null,"ingresa la nueva fecha de naciemiento " +
                        "YYYY-MM-DD" +
                        " ->",
                objPaciente.getFecha_nacimiento()));
        objPaciente.setDocumento_identidad(JOptionPane.showInputDialog(null,"ingresa el nuevo numero de documento ->",
                objPaciente.getDocumento_identidad()));

        instanciaModel().update(objPaciente);

    }
}
