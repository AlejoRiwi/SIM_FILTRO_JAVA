package controller;

import entity.Avion;
import model.AvionModel;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class AvionController {
    AvionModel objAvionModel;

    public AvionController() {
        this.objAvionModel = new AvionModel();
    }

    public static void insert() {
        String modelo = JOptionPane.showInputDialog(null, "Ingresa el modelo del avion");
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la capacidad del avion"));
        instanciaModel().insert(new Avion(modelo, capacidad));
    }

    public static AvionModel instanciaModel() {
        return new AvionModel();
    }

    public static void getAll() {
        String list = getAll(instanciaModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> lisObject) {
        String list = " --- Lista de aviones --- ";
        for (Object obj : lisObject) {
            Avion objAvion = (Avion) obj;
            list += objAvion.toString() + "\n";
        }
        return list;
    }

    public static void delete() {
        Object[] opciones = Utils.lisToArray(instanciaModel().findAll());
        Avion objAvion = (Avion) JOptionPane.showInputDialog(null, "Selecciona el avion que quieres eliminar", "",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        instanciaModel().delete(objAvion);
    }
}
