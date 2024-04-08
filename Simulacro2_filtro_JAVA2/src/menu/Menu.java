package menu;

import controller.AvionController;

import javax.swing.*;

public class Menu {

    AvionController objAvionController = new AvionController ();
    public void mostrarMenu() {
        String[] opciones = {"Aviones", "Vuelos", "Pasajeros", "Reservaciones", "Salir del sistema"};
        int seleccion;
        int select;
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
                        select = Integer.parseInt(JOptionPane.showInputDialog(null, "Sistema de aerolinea\n" +
                                "1. Añadir Aviones\n"+ "2. Listar Aviones\n"+ "3. Eliminar Aviones\n"+ "4. Salir del sistema"));
                        switch (select){
                            case 1:
                                AvionController.insert();
                                break;
                            case 2:
                                AvionController.getAll();
                                break;
                            case 3:
                                AvionController.delete();
                                break;
                        }
                    } while (select != 4);
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
