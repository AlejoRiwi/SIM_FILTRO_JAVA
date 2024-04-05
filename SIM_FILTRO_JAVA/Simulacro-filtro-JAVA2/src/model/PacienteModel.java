
package model;

import database.CRUD;
import database.ConfigDB;
import entity.Paciente;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) object;

        try{
            String sql = "INSERT INTO paciente (nombre,apellido,fecha_nacimiento,d_identidad) VALUES(?,?,?,?)";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellido());
            objPrepare.setDate(3, Date.valueOf(objPaciente.getFecha_nacimiento()));
            objPrepare.setString(4, objPaciente.getDocumento_identidad());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objPaciente.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "El paciente fue creado correctamente");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objPaciente;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listPacientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM paciente";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResutl = objPrepare.executeQuery();

            while (objResutl.next()) {
                Paciente objPaciente = new Paciente();
                objPaciente.setId(objResutl.getInt("id"));
                objPaciente.setNombre(objResutl.getString("nombre"));
                objPaciente.setApellido(objResutl.getString("apellido"));
                objPaciente.setFecha_nacimiento(objResutl.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResutl.getString("d_identidad"));

                listPacientes.add(objPaciente);
            }
        }catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listPacientes;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) object;
        boolean isUpdate = false;

        try{
            String sql = "UPDATE paciente SET nombre=?, apellido=? ,fecha_nacimiento=?, d_identidad=? WHERE id=?; ";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellido());
            objPrepare.setDate(3,Date.valueOf(objPaciente.getFecha_nacimiento()));
            objPrepare.setString(4,objPaciente.getDocumento_identidad());
            objPrepare.setInt(5,objPaciente.getId());

            int filasAfectadas = objPrepare.executeUpdate();
            if (filasAfectadas > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "El Paciente, " + objPaciente.getNombre() + " Fue actualizado " +
                        "correctamente");
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Paciente objPaciente = (Paciente) object;
        boolean isDeleted = false;
        try{
            String sql = "DELETE FROM paciente WHERE id =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objPaciente.getId());

            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Registro Eliminado Correctamente");
            }

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
}
