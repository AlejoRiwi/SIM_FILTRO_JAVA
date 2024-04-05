package entity;

import java.util.Date;

public class Cita {
    private int id;
    private String fecha_cita;
    private String hora_cita;
    private String motivo;

    private int id_paciente;
    private int id_medico;

    private Paciente objPaciente;
    private Medico objMedico;

    public Cita() {
    }

    public Cita(String fecha_cita, String hora_cita, String motivo, int id_paciente, int id_medico, Paciente objPaciente, Medico objMedico) {
        this.fecha_cita = fecha_cita;
        this.hora_cita = hora_cita;
        this.motivo = motivo;
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
        this.objPaciente = objPaciente;
        this.objMedico = objMedico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(String hora_cita) {
        this.hora_cita = hora_cita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public Paciente getObjPaciente() {
        return objPaciente;
    }

    public void setObjPaciente(Paciente objPaciente) {
        this.objPaciente = objPaciente;
    }

    public Medico getObjMedico() {
        return objMedico;
    }

    public void setObjMedico(Medico objMedico) {
        this.objMedico = objMedico;
    }

    @Override
    public String toString() {
        return "Cita \n" +
                "fecha_cita='" + fecha_cita + '\'' +
                ", hora_cita='" + hora_cita + '\'' +
                ", motivo='" + motivo + '\'' +
                ", objPaciente=" + objPaciente.getNombre() +
                ", objMedico=" + objMedico.getNombre() +
                '}';
    }
}
