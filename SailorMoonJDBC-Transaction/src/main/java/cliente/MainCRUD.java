package cliente;

import datos.CRUDSailorScoutsImplDAO;
import datos.iCRUDSailorScoutsDAO;
import dominio.Planetas;
import dominio.SailorScouts;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainCRUD {

    public static void main(String[] args) throws SQLException {

        iCRUDSailorScoutsDAO dao = new CRUDSailorScoutsImplDAO();

        String nombre, apellido, planeta = null;
        int id_sailor = 0;

        int x = 0;
        Scanner sc = new Scanner(System.in);
        
        while (x == 0) {
            System.out.println("---------------------------------------");
            System.out.println("Ingresá la opción a realizar en la DB: ");
            System.out.println("1- Listar todas las sailors");
            System.out.println("2- Listar planetas unicos");
            System.out.println("3- Agregar sailor");
            System.out.println("4- Modificar sailor");
            System.out.println("5- Eliminar sailor");
            System.out.println("6- Salir");
            System.out.println("---------------------------------------");
            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: 
                    System.out.println("----------------------------");
                    System.out.println("Lista de todas las sailors: ");
                    System.out.println("----------------------------");
                    List<SailorScouts> sailors = dao.select();

                    for (SailorScouts sailor : sailors) {
                        System.out.println("ID: " + sailor.getId_sailor()
                                + "\n Nombre: " + sailor.getNombre()
                                + "\n Apellido: " + sailor.getApellido()
                                + "\n Planeta: " + sailor.getPlaneta());
                    }

                    break;
                case 2:
                    System.out.println("----------------------------");
                    System.out.println("Lista de planetas unicos: ");
                    System.out.println("----------------------------");
                    List<Planetas> planetas = dao.selectAllPlanets();

                    for (Planetas p : planetas) {
                        System.out.println(p);
                    }

                    break;
                case 3: 
                    System.out.println("-----------------------------------------------");
                    System.out.println("Agrega los datos del nuevo registro de sailor: ");
                    System.out.println("-----------------------------------------------");
                    System.out.println("Nombre: ");
                    nombre = sc.nextLine();
                    System.out.println("Apellido: ");
                    apellido = sc.nextLine();
                    System.out.println("Planeta: ");
                    planeta = sc.nextLine();

                    SailorScouts sailorInsert = new SailorScouts();
                    sailorInsert.setNombre(nombre);
                    sailorInsert.setApellido(apellido);
                    sailorInsert.setPlaneta(planeta);

                    dao.insert(sailorInsert);
                    break;
                case 4: 
                    System.out.println("---------------------------------------------");
                    System.out.println("Modificacion de datos de registro de sailor: ");
                    System.out.println("---------------------------------------------");
                    System.out.println("ID de la sailor a modificar: ");
                    id_sailor = Integer.parseInt(sc.nextLine());
                    System.out.println("Nombre: ");
                    nombre = sc.nextLine();
                    System.out.println("Apellido: ");
                    apellido = sc.nextLine();
                    System.out.println("Planeta: ");
                    planeta = sc.nextLine();

                    SailorScouts sailorUpdate = new SailorScouts();
                    sailorUpdate.setId_sailor(id_sailor);
                    sailorUpdate.setNombre(nombre);
                    sailorUpdate.setApellido(apellido);
                    sailorUpdate.setPlaneta(planeta);

                    if (dao.checkID(sailorUpdate)) {
                        dao.update(sailorUpdate);
                    } else {
                        System.out.println("ID NO EXISTENTE EN LA BASE DE DATOS");
                    }

                    break;
                case 5: 
                    System.out.println("-----------------");
                    System.out.println("Eliminar sailor: ");
                    System.out.println("-----------------");
                    System.out.println("ID de la sailor a eliminar: ");
                    id_sailor = sc.nextInt();

                    SailorScouts sailorDelete = new SailorScouts();
                    sailorDelete.setId_sailor(id_sailor);

                    if (dao.checkID(sailorDelete)) {
                        dao.delete(sailorDelete);
                    } else {
                        System.out.println("ID NO EXISTENTE EN LA BASE DE DATOS");
                    }

                    break;
                case 6:
                    System.out.println("Gracias por pasar!");
                    x = 1;
                    break;
                default:
                    System.out.println("Ups, opcion ingresada invalida");
            }
        }
    }
}
