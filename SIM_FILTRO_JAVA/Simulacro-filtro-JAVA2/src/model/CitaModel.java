package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cita;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) object;

        try {
            String sql = "INSERT INTO cita (id_paciente, id_medico, fecha_cita, hora_cita, motivo) VALUES (?,?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objCita.getId_paciente());
            objPrepare.setInt(2, objCita.getId_medico());
            objPrepare.setDate(3, Date.valueOf(objCita.getFecha_cita()));
            objPrepare.setTime(4, Time.valueOf(objCita.getHora_cita()));
            objPrepare.setString(5, objCita.getMotivo());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objCita.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Se creo la cita exisotamente");
        } catch (SQLException e) {
            System.out.println("Error > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCita;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listaCitas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cita " +
                    "INNER JOIN paciente ON paciente.id = cita.id_paciente" +
                    " INNER JOIN medico ON medico.id = cita.id_medico;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Cita objCita = new Cita();
                Medico objMedico = new Medico();
                Paciente objPaciente = new Paciente();

                objCita.setId(objResult.getInt("cita.id"));
                objCita.setFecha_cita(objResult.getString("cita.fecha_cita"));
                objCita.setHora_cita(objResult.getString("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("cita.motivo"));
                objCita.setId_paciente(objResult.getInt("cita.id_paciente"));
                objCita.setId_medico(objResult.getInt("cita.id_medico"));

                objMedico.setNombre(objResult.getString("medico.nombre"));
                objPaciente.setNombre(objResult.getString("paciente.nombre"));

                objCita.setObjMedico(objMedico);
                objCita.setObjPaciente(objPaciente);

                listaCitas.add(objCita);
            }

        } catch (SQLException e) {
            System.out.println("Error > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listaCitas;
    }

    @Override
    public boolean delete(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) object;
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM cita WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objCita.getId());

            int filasAfectadas = objPrepare.executeUpdate();
            if (filasAfectadas > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Cita eliminada correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Error > " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Cita objCita = (Cita) object;
        boolean isUpdate = false;
        try {
            String sql = "UPDATE cita SET fecha_cita = ?, hora_cita=?, id_medico=?, motivo=?  WHERE id=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setDate(1, Date.valueOf(objCita.getFecha_cita()));
            objPrepare.setTime(2, Time.valueOf(objCita.getHora_cita()));
            objPrepare.setInt(3,objCita.getId_medico());
            objPrepare.setString(4, objCita.getMotivo());
            objPrepare.setInt(5, objCita.getId());
            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "La cita fue actualizada correcetamente");
            }

        } catch (SQLException e) {
            System.out.println("Error > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }
}
