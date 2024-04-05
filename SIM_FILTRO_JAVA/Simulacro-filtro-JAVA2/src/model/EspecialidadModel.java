package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;

import javax.swing.*;
import java.io.ObjectInputFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EspecialidadModel implements CRUD {
    public Object insert (Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = (Especialidad) object;
        try{
            String sql = "Insert into especialidad(nombre,descripcion) value (?,?);";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1,objEspecialidad.getNombre());
            objPrepare.setString(2,objEspecialidad.getDescripcion());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()) {
                objEspecialidad.setId(objResult.getInt(1));
            }
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Especialidad creada correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Conexion" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objEspecialidad;
    }


    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listEspecialidad = new ArrayList<>();

        try {
            String sql = "SELECT * FROM especialidad ORDER BY especialidad.id ASC;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Especialidad objEspecialidad = new Especialidad();

                objEspecialidad.setId(objResult.getInt("id"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                System.out.println(objResult.getInt("id"));
                listEspecialidad.add(objEspecialidad);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquosoton ERROR");
        }

        ConfigDB.closeConnection();
        return listEspecialidad;
    }

    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Especialidad objEspecialidad = null;
        try {
            String sql = "SELECT * FROM especialidad WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objEspecialidad = new Especialidad();
                objEspecialidad.setId(objResult.getInt("id"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objEspecialidad;
    }
    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
       Especialidad objEspecialidad = (Especialidad) object;
       boolean isDeleted = false;

       Connection objConnecion = ConfigDB.openConnection();

       try {
           String sql = "DELETE FROM especialidad WHERE id = ?;";

           PreparedStatement objPrepare = objConnecion.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
           objPrepare.setInt(1,objEspecialidad.getId());

           int filasAfectadas = objPrepare.executeUpdate();

           if (filasAfectadas > 0) {
               isDeleted = true;
               JOptionPane.showMessageDialog(null, "Se elimino coprrectamente ");
           }
       }catch (Exception e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
       ConfigDB.closeConnection();
       return isDeleted;
    }
}
