package controller;

import entity.Cita;
import entity.Medico;
import entity.Paciente;
import jdk.jshell.execution.Util;
import model.CitaModel;
import model.PacienteModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class CitaController {
    public static void insert() {
        String fecha_cita = JOptionPane.showInputDialog("Ingresa la fecha de la cita, YYYY-MM-DD");
        String hora_cita = JOptionPane.showInputDialog("Ingresa la hora de la cita, HH:MM:SS");
        String motivo = JOptionPane.showInputDialog("Ingresa el motivo de la cita");

        Object[] opcionesPaciente = Utils.listToArray(PacienteController.instanciaModel().findAll());
        Paciente pacienteSeleccionado = (Paciente) JOptionPane.showInputDialog(
                null,
                "Seleccione el paciente que quiere Agregar a la lista",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesPaciente,
                opcionesPaciente[0]
        );
        Object[] opcionesMedico = Utils.listToArray(MedicoController.instanceModel().findAll());
        Medico medicoSeleccionado = (Medico) JOptionPane.showInputDialog(
                null,
                "Seleccione el medico que quiere inscribir al paciente",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesMedico,
                opcionesMedico[0]
        );

        intanceModel().insert(new Cita(fecha_cita, hora_cita, motivo, pacienteSeleccionado.getId(), medicoSeleccionado.getId(), pacienteSeleccionado, medicoSeleccionado));
    }

    public static CitaModel intanceModel() {
        return new CitaModel();
    }

    public static void getAll() {
        String listaString = getAll(intanceModel().findAll());
        JOptionPane.showMessageDialog(null, listaString);
    }

    public static String getAll(List<Object> list) {
        String listaString = "Lista de Registro de citas";

        for (Object temp : list) {
            Cita objCita = (Cita) temp;
            listaString += objCita.toString() + "\n";
        }

        return listaString;
    }

    public static void delete() {
        Object[] opciones = Utils.listToArray(intanceModel().findAll());

        Cita objCita = (Cita) JOptionPane.showInputDialog(
                null,
                "Seleccione la cita que quiere eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        intanceModel().delete(objCita);
    }

    public static void update() {
        Object[] opciones = Utils.listToArray(intanceModel().findAll());
        String nombre;
        Medico objMedico = new Medico();

        Cita objCita = (Cita) JOptionPane.showInputDialog(
                null,
                "Seleccione la cita que quiere actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        nombre = objCita.getObjMedico().getNombre();
        JOptionPane.showMessageDialog(null, "La cita a acutalizar es del paciente " + nombre);
        objCita.setFecha_cita(JOptionPane.showInputDialog(null, "ingresa la nueva fecha para agendar la cita Formato (YYYY-MM-DD", objCita.getFecha_cita()));
        objCita.setHora_cita(JOptionPane.showInputDialog(null, "Ingresa la nueva hora para agendar, Formato(HH:MM:SS)", objCita.getHora_cita()));
        Object[] opcionesMedicos = Utils.listToArray(MedicoController.instanceModel().findAll());
        objCita.setObjMedico((Medico) JOptionPane.showInputDialog(
                null,
                "Seleccione el medico que quiere actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesMedicos,
                opcionesMedicos[0]
        ));
        objCita.setMotivo(JOptionPane.showInputDialog(null, "Ingresa el motivo actualizado de la cita ", objCita.getMotivo()));

        objCita.setId_medico(objCita.getObjMedico().getId());

        intanceModel().update(objCita);
    }
}
