package entity;

public class Reservacion {
    private int id;
    private String fecha_reservacion;
    private String asiento;
    private int id_pasajero;
    private int id_vuelo;

    private Pasajero objPasajero;
    private Vuelo objVuelo;

    public Reservacion() {
    }

    public Reservacion(int id, String fecha_reservacion, String asiento, int id_pasajero, int id_vuelo, Pasajero objPasajero, Vuelo objVuelo) {
        this.id = id;
        this.fecha_reservacion = fecha_reservacion;
        this.asiento = asiento;
        this.id_pasajero = id_pasajero;
        this.id_vuelo = id_vuelo;
        this.objPasajero = objPasajero;
        this.objVuelo = objVuelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_reservacion() {
        return fecha_reservacion;
    }

    public void setFecha_reservacion(String fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public int getId_pasajero() {
        return id_pasajero;
    }

    public void setId_pasajero(int id_pasajero) {
        this.id_pasajero = id_pasajero;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public Pasajero getObjPasajero() {
        return objPasajero;
    }

    public void setObjPasajero(Pasajero objPasajero) {
        this.objPasajero = objPasajero;
    }

    public Vuelo getObjVuelo() {
        return objVuelo;
    }

    public void setObjVuelo(Vuelo objVuelo) {
        this.objVuelo = objVuelo;
    }

    @Override
    public String toString() {
        return "Reservacion{" +
                "id=" + id +
                ", fecha_reservacion='" + fecha_reservacion + '\'' +
                ", asiento='" + asiento + '\'' +
                ", id_pasajero=" + id_pasajero +
                ", id_vuelo=" + id_vuelo +
                ", objPasajero=" + objPasajero +
                ", objVuelo=" + objVuelo +
                '}';
    }
}
