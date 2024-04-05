package controller;

import entity.Especialidad;
import model.EspecialidadModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class EspecialidadController {

    EspecialidadModel objEspecialidadModel;

    public EspecialidadController() {
        this.objEspecialidadModel = new EspecialidadModel();
    }

    /*public void insert () {
        Especialidad objEspecialidad = new Especialidad();
        String nombre = JOptionPane.showInputDialog(null, "Ingresa el nombre de la Especialización");
        String descripcion = JOptionPane.showInputDialog(null, "Ingresa la descripcion de la Especialización");

        objEspecialidad.setNombre(nombre);
        objEspecialidad.setDescripcion(descripcion);

        objEspecialidad = (Especialidad) this.objEspecialidadModel.insert(objEspecialidad);

        JOptionPane.showMessageDialog(null, objEspecialidad.toString());

    }*/

    // Refactorin
    public static void insert() {
        String nombre = JOptionPane.showInputDialog(null, "Ingresa el nombre de la Especialización");
        String descripcion = JOptionPane.showInputDialog(null, "Ingresa la descripcion de la Especialización");

        instanciaModel().insert(new Especialidad(nombre, descripcion));
    }
    public static EspecialidadModel instanciaModel() {
        return new EspecialidadModel();
    }


    public static void getAll () {
        String list = getAll(instanciaModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll (List<Object> listObject) {
        String list = "-- Lista de Especialidades --\n \n";
        for(Object obj : listObject) {
            Especialidad objEspecialidad = (Especialidad) obj;

            list += objEspecialidad.toString() + "\n";

        }
        return list;
    }

    /* public void delete () {
        String listEspecialidades = this.getAll(this.objEspecialidadModel.findAll());
        int confirmacion = 1;
        int isDeleted = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidades + "\nIngresa el identificador de la Especialidad a eliminar"));

        Especialidad objEspecialidad = (Especialidad) this.objEspecialidadModel.findById(isDeleted);

        if(objEspecialidad == null) {
            JOptionPane.showMessageDialog(null, "La especialidad no fue encontrada");
        }else {
            confirmacion = JOptionPane.showConfirmDialog(null, "Esta seguro que quieres eliminar esta especialidad ?\n" + objEspecialidad.toString());

            if (confirmacion == 0){
                this.objEspecialidadModel.delete(objEspecialidad);
            }
        }
    }*/

    // Refactorizacion de Eliminar

    public static  void delete (){
        Object[] opciones = Utils.listToArray(instanciaModel().findAll());
        Especialidad objSeleccion = (Especialidad) JOptionPane.showInputDialog(null, "Selecciona una especialidad", "", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        instanciaModel().delete(objSeleccion);
    }
}