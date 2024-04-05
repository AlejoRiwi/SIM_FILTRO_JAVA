package controller;

import entity.Especialidad;
import entity.Medico;
import model.EspecialidadModel;
import model.MedicoModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class MedicoController {
    EspecialidadController objEspeController = new EspecialidadController();
    Especialidad objEspecialidad = new Especialidad();
     EspecialidadModel objEspecialidadModel = new EspecialidadModel();
    MedicoModel objMedicoModel;

    public MedicoController() {
        objMedicoModel = new MedicoModel();
    }

    /* public void create () {
        Medico objMedico = new Medico();


        String nombre = JOptionPane.showInputDialog(null, "Escribe el nombre del medico");
        String apellido = JOptionPane.showInputDialog(null, "Escribe el apellido del medico");
        int id_especialidad = Integer.parseInt(JOptionPane.showInputDialog(objEspeController.getAll(this.objEspecialidadModel.findAll()) + "Ingresa el identificador de la especialidad"));

        objMedico.setNombre(nombre);
        objMedico.setApellido(apellido);
        objMedico.setId_especialidad(id_especialidad);

        objMedico = (Medico) this.objMedicoModel.insert(objMedico);
        // JOptionPane.showMessageDialog(null, objMedico.toString());
    }*/

    // Refactorizando Create
    public static void create() {
        String nombre = JOptionPane.showInputDialog(null, "Escribe el nombre del medico");
        String apellido = JOptionPane.showInputDialog(null, "Escribe el apellido del medico");

         Object [] opcionesEspecialidades = Utils.listToArray(EspecialidadController.instanciaModel().findAll());

         Especialidad objEspecialidad = (Especialidad) JOptionPane.showInputDialog(null,"Selecciona una especialidad","",
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 opcionesEspecialidades,
                 opcionesEspecialidades[0]);

         instanceModel().insert(new Medico(nombre, apellido, objEspecialidad.getId(), objEspecialidad));
    }

    public static MedicoModel instanceModel() {
        return new MedicoModel();
    }

    public static void findAll() {
        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll (List<Object> listObject){
        String list = "-- Lista de Medicos --\n";
        for (Object obj : listObject) {
            Medico objMedico = (Medico) obj;

            list += objMedico.toString() + "\n";
        }
        return list;
    }

    /* public void findName () {
        String listMedicos = "--- Lista de Medicos ---\n";
        String isName = JOptionPane.showInputDialog(null, "Ingresa el nombre de la especialidad");
        String list = this.getAll(this.objMedicoModel.findName(isName));

        JOptionPane.showMessageDialog(null, list);
    }*/

    /* public void findById() {
        String listaMedicos = this.getAll(this.objMedicoModel.findAll());
        int isId;
        int confirmacion = 1;
        isId = Integer.parseInt(JOptionPane.showInputDialog(listaMedicos + "Ingresa el id de la especialidad a buscar"));

        Medico objMedico = (Medico) this.objMedicoModel.findById(isId);

        if (objMedico == null) {
            JOptionPane.showMessageDialog(null, "La especialidad no fue encontrada");
        } else {
            confirmacion = JOptionPane.showConfirmDialog(null, "La especialidad que estas buscando es: " + objEspecialidad.getNombre());
            if (confirmacion == 0) {
                this.objMedicoModel.findById(isId));
            }
        }
    }*/

    /*
    public List<Medico> findEspecialidad(int especialidadId) {
        return MedicoModel.findEspecialidad(especialidadId);
    }

    public void findEspecialidad() {
        // get all available specialties
        List<Object> objEspecialidad = EspecialidadModel.findAll();


        if (!objEspecialidad.isEmpty()) {
            // show all specialities
            StringBuilder options = new StringBuilder("Select a speciality:\n");
            for (Especialidad speciality : especialidad) {
                options.append(speciality.getId()).append(". ").append(speciality.getnombre()).append("\n");
            }
            int especialidadSeleccionada = Integer.parseInt(JOptionPane.showInputDialog(null, options.toString()));

            // Search for medics by selected specialty
            List<Object> medics = MedicoModel.findEspecialidad(especialidadSeleccionada);

            // show medics
            StringBuilder result = new StringBuilder("Medics found for selected speciality: \n");
            for (Medico medic : medico) {
                result.append(medic.getNombre()).append(" ").append(medic.getApellido()).append("\n");
            }
            JOptionPane.showMessageDialog(null, result.toString());
        } else {
            JOptionPane.showMessageDialog(null, "There are no specialities available.");
        }
    }   */


   /* public void delete() {
        String listMedicos = this.getAll(this.objMedicoModel.findAll());
        int confirmacion = 1;
        int isDeleted = Integer.parseInt(JOptionPane.showInputDialog(listMedicos + "Ingresa el identificador del " +
                "medico que quieres eliminar"));
        Medico objMedico = (Medico) this.objMedicoModel.findById(isDeleted);

        if (objMedico == null){
            JOptionPane.showMessageDialog(null, "Medico no encontrado");
        } else {
            confirmacion = JOptionPane.showConfirmDialog(null,
                    "Esta seguro de que queire eliminar al medico " + objMedico.getNombre());
            if (confirmacion == 0) {
                this.objMedicoModel.delete(objMedico);
            }
        }
    } */

    // Refactorizacion Delete
    public static void delete() {
        Object[] opciones = Utils.listToArray(instanceModel().findAll());

        Medico objMedico = (Medico) JOptionPane.showInputDialog(
                null,
                "Seleccione el medico que quiere eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        instanceModel().delete(objMedico);
    }

    public static void update(){
        Object[] opciones = Utils.listToArray(instanceModel().findAll());
        String nombre, apellido;

        Medico objMedico = (Medico) JOptionPane.showInputDialog(
                null,
                "Seleccione el medico que quiere actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        nombre = objMedico.getNombre();
        apellido = objMedico.getApellido();
        JOptionPane.showMessageDialog(null,nombre + " " + apellido);

        Object [] opcionesEspecialidades = Utils.listToArray(EspecialidadController.instanciaModel().findAll());
        Especialidad objEspecialidad = (Especialidad) JOptionPane.showInputDialog(null, "Selecciona la especialidad del medico agregada", "", JOptionPane.QUESTION_MESSAGE,null,opcionesEspecialidades, opcionesEspecialidades[0]);
        instanceModel().update(new Medico(nombre,apellido, objEspecialidad.getId(), objEspecialidad));
    }
}

