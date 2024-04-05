package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico =(Medico) object;
        Especialidad objEspecialidad = new Especialidad();

        try{
            String sql = "INSERT INTO medico(nombre,apellido, id_especialidad) VALUES(?,?,?);";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellido());
            objPrepare.setInt(3,objMedico.getId_especialidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objMedico.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Medico Creado correctamente");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding Medico " + e.getMessage() + "No se puede añadir el medico");
        }
        ConfigDB.closeConnection();
        return objMedico;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listMedico = new ArrayList<>();

        try{
            String sql = "SELECT * FROM medico INNER JOIN especialidad ON especialidad.id = medico.id_especialidad ORDER BY medico.id ASC;";
            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepareStatement.executeQuery();

            while (objResult.next()){
                Medico objMedico = new Medico();
                Especialidad objEspecialidad = new Especialidad();

                objMedico.setId(objResult.getInt("id"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellido(objResult.getString("apellido"));
                objMedico.setId_especialidad(objResult.getInt("id_especialidad"));

                objEspecialidad.setId(objResult.getInt("especialidad.id"));
                objEspecialidad.setNombre(objResult.getString("especialidad.nombre"));
                objEspecialidad.setDescripcion(objResult.getString("especialidad.descripcion"));

                objMedico.setObjEspecialidad(objEspecialidad);
                listMedico.add(objMedico);

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listMedico;
    }

    public static List<Object> findEspecialidad() {
        List<Object> listMedicos = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = null;

        try{
            String sql = "SELECT * FROM medico WHERE medico.id_especialidad = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,"id");

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()) {
                objMedico = new Medico();
                objMedico.setId(objResult.getInt("id"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellido(objResult.getString("apellido"));
                objMedico.setId_especialidad(objResult.getInt("id_especialidad"));

                listMedicos.add(objMedico);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return listMedicos;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = (Medico) object;
        boolean isUpdate = false;
        try{
            String sql = "UPDATE medico SET id_especialidad=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            // objPrepare.setString(1, objMedico.getNombre());
            // objPrepare.setString(2, objMedico.getApellido());
            objPrepare.setInt(1, objMedico.getId_especialidad());

            int filasAfectadas = objPrepare.executeUpdate();


            if (filasAfectadas > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "El medico, " + objMedico.getNombre() + " Fue actualizado correctamente");

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    public Object findById (int id) {
        Connection objConnection = ConfigDB.openConnection();
        Medico objMedico = null;

        try{
            String sql = "SELECT * FROM medico WHERE id = ? ;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                objMedico = new Medico();
                objMedico.setId(objResult.getInt("id"));
                objMedico.setNombre(objResult.getString("nombre"));
                objMedico.setApellido(objResult.getString("apellido"));

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objMedico;
    }

    @Override
    public boolean delete(Object object) {
        Medico objMedico = (Medico) object;
        boolean isDeleted = false;

        Connection objconnection = ConfigDB.openConnection();

        try{
            String sql = "DELETE FROM medico WHERE id = ?;";
            PreparedStatement objPrepare = objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1, objMedico.getId());

            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "El medico " + objMedico.getNombre() + " se elimino Correctamente");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
}
