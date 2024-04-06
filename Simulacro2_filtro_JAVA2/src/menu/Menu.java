package menu;

import controller.AvionController;

import javax.swing.*;

public class Menu {

    AvionController objAvionController = new AvionController ();
    public void mostrarMenu() {
        String[] opciones = {"Aviones", "Vuelos", "Pasajeros", "Reservaciones", "Salir del sistema"};
        String[] opAvion = {"Crear un nuevo avión", "Listar aviones existentes", "Eliminar aviones", "Volver al menú " +
                "principal"};
        int seleccion;
        do {
            seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Sistema de aerolinea\n" +
                            "Seleccione la consulta a realizar dando click sobre su respectivo boton",
                    "Menu de inicio",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );
            switch (seleccion) {
                case 0:
                    System.out.println("Menu de aviones");
                    do {
                        seleccion = JOptionPane.showOptionDialog(
                                null,
                                "Sistema de aerolinea\n" +
                                        "Seleccione la consulta a realizar dando click sobre su respectivo boton",
                                "Menu de Aviones",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                opAvion,
                                opAvion[0]
                        );
                        switch (seleccion){
                            case 0:
                                AvionController.insert();
                                break;
                            case 1:
                                AvionController.getAll();
                                break;
                            case 2:
                                AvionController.delete();
                                break;
                        }
                    } while (seleccion != 4);
                    break;
                case 1:
                    System.out.println("Menu de vuelos");
                    break;
                case 2:
                    System.out.println("menu de pasajeros");
                    break;
                case 3:
                    System.out.println("Menu de reservas");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Vuelve pronto");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida");
                    break;
            }
        } while (seleccion != 5);
    }
}
