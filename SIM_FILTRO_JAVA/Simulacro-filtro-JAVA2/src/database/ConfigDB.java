package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    static Connection objConnection = null;
    public static Connection openConnection () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://bwt4h6w3gzindeduw1e7-mysql.services.clever-cloud.com:3306/bwt4h6w3gzindeduw1e7";
            String user = "uypjdimciu1lr7jv";
            String password = "95q2WmSOP99Z7l2fAYIW";

            objConnection = (Connection) DriverManager.getConnection(url, user,password);
            System.out.println("Conexion establecida correctamente");
        }catch (ClassNotFoundException e) {
            System.out.println("Error, Drivers no instalado");
        } catch (SQLException e){
            System.out.println("ERROR --> No se pudo cestablecer conexion con la dase de datos" + e.getMessage());
        }
        return objConnection;
    }

    public static void closeConnection () {
        try {
            if (objConnection != null ) objConnection.close();
        } catch (SQLException e) {
            System.out.println();
        }
    }
}
