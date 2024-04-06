package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Reservacion;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvionModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) object;
        try {
            String sql = "INSERT INTO avion (modelo,capacidad) VALUES (?,?);";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objAvion.getModelo());
            objPrepare.setInt(2, objAvion.getCapacidad());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()) {
                objAvion.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "El avion fue creado correctamente");
        } catch (SQLException e) {
            System.out.println("Error -> " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objAvion;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listaAviones = new ArrayList<>();

        try {
            String sql = "SELECT * FROM avion ORDER BY avion.id ASC;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Avion objAvion = new Avion();
                objAvion.setId(objResult.getInt("id"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getInt("capaciodad"));

                listaAviones.add(objAvion);
            }
        } catch (SQLException e) {
            System.out.println("Error ->" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaAviones;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion = (Avion) object;
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM avion WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1,objAvion.getId());

            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas > 0) {
                isDelete = true;
                JOptionPane.showMessageDialog(null, "El avion con matricula " + objAvion.getModelo() + " Fue " +
                        "eliminado correctamente");
            }
        } catch (Exception e) {
            System.out.println("Error -> " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }
}
