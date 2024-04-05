
package Menu;

import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;

import javax.swing.*;

public class Menu {
    EspecialidadController objEspeController = new EspecialidadController();
    MedicoController objMedicoController = new MedicoController();
    public  void mostrarMenu() {
        String[] opciones = {"Nueva Especialidad", "Nuevo Medico", "Nuevo Paciente","Nueva Cita","Salir"};
        int seleccion;
        do {
            seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Bienvenido al sistema",
                    "Menú de Inicio",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (seleccion) {
                case 0:
                    // Lógica para crear una especialidad nueva

                    int option = 0;
                    do {
                        option = Integer.parseInt(JOptionPane.showInputDialog("""
                            0. para crear una especialidad nueva
                            1. para mostrar todas las especialidades
                            2. para eliminar una especialidad
                            3. para volver al menu principal
                            """));

                        switch (option){
                            case 0:
                                objEspeController.insert();
                                break;
                            case 1 :
                                objEspeController.getAll();
                                break;
                            case 2:
                                objEspeController.delete();
                                break;
                        }
                    }while(option != 3);
                    break;
                case 1:
                    // Lógica para crear un Medico
                    do {
                        option = Integer.parseInt(JOptionPane.showInputDialog("""
                            0. para crear un medico
                            1. para mostrar todos los medicos
                            2. para mostrar todos los medicos x especialidad
                            3. para actualizar la especialidad del medico
                            4. para eliminar un medico
                            5. para volver al menu principal
                            """));

                        switch (option){
                            case 0:
                                MedicoController.create();
                                break;
                            case 1 :
                                MedicoController.findAll();
                                break;
                            case 2:
                                // MedicoController.findEspecialidad();
                                break;
                            case 3:
                                MedicoController.update();
                                break;
                            case 4:
                                MedicoController.delete();
                                break;
                        }
                    }while(option != 5);
                    break;
                case 2:
                    do {
                        option = Integer.parseInt(JOptionPane.showInputDialog("""
                            0. para crear un paciente
                            1. para mostrar todos los pacientes
                            2. para buscar paciente x cedula
                            3. para actualizar los datos del paciente
                            4. para eliminar un paciente
                            5. para volver al menu principal
                            """));

                        switch (option){
                            case 0:
                                PacienteController.insert();
                                break;
                            case 1:
                                PacienteController.getAll();
                                break;
                            case 3:
                                PacienteController.update();
                                break;
                            case 4:
                                PacienteController.delete();
                                break;
                        }
                    }while(option != 5);
                    break;
                case 3:
                    do {
                        option = Integer.parseInt(JOptionPane.showInputDialog("""
                            0. para crear una cita
                            1. para mostrar todas las citas
                            2. para buscar citas x
                            3. para actualizar los datos de la cita
                            4. para eliminar una cita
                            5. para volver al menu principal
                            """));

                        switch (option){
                            case 0:
                                System.out.println("ingresamos a la entidad cita ");
                                CitaController.insert();
                                break;
                            case 1:
                                CitaController.getAll();
                                break;
                            case 3:
                                CitaController.update();
                                break;
                            case 4:
                                CitaController.delete();
                                break;
                        }
                    }while(option != 5);
                    break;
                case 4:
                    // Salir
                    JOptionPane.showMessageDialog(null, "Adiós");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
                    break;
            }
        }while(seleccion != 5);

    }
}
