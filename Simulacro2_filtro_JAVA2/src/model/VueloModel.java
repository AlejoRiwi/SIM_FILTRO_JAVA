package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Vuelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VueloModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = (Vuelo) object;
        Avion objAvion = new Avion();

        try {
            String sql = "SELECT * FROM vuelo(destino,fecha_salida,hora_salida, id_avion) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objVuelo.getDestino());
            objPrepare.setDate(2, Date.valueOf(objVuelo.getFecha_salida()));
            objPrepare.setTime(3, Time.valueOf(objVuelo.getHora_salida()));
            objPrepare.setInt(4, objVuelo.getId_avion());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()) {
                objVuelo.setId(objResult.getInt(1));
            }
        }catch (SQLException e) {
            System.out.println("Error > " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objVuelo;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listaVuelos = new ArrayList<>();

        try{
            String sql = "SELECT * FROM vuelo " +
                    "INNER JOIN avion ON avion.id = vuelo.id_avion " +
                    "ORDER BY vuelo.id ASC";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()) {
                Vuelo objVuelo = new Vuelo();
                Avion objAvion = new Avion();

                objVuelo.setId(objResult.getInt("id"));
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId_avion(objResult.getInt("id_avion"));

                objAvion.setId(objResult.getInt("avion.id"));
                objAvion.getModelo(objResult.getString("avion.modelo"));
                objAvion.getCapacidad(objResult.getInt());

                objVuelo.setObjAvion(objAvion);
                listaVuelos.add(objVuelo);
            }
        } catch(SQLException e){
            System.out.println("Error > "  + e.getMessage());
        }
        ConfigDB.closeConnection();
        return listaVuelos;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }
}
