package recursosEstudiante;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) throws SQLException{
        desplegarMenu();
    }
    public static void desplegarMenu() throws SQLException{
        Scanner opcionSeleccionada =  new Scanner (System.in);
        String opcionMenu;
        // menu a desplegar
        System.out.println("*************************************************");
        System.out.println("|           CRUD DE JAVA EN CONSOLA             |");
        System.out.println("*************************************************");
        System.out.println("|               Opciones                        |");
        System.out.println("|           1. Crear registro                   |");
        System.out.println("|           2. Consultar registros              |");
        System.out.println("|           3. Actualizar registros             |");
        System.out.println("|           4. Eliminar registro                |");
        System.out.println("|           5. salir                            |");
        System.out.println("*************************************************");
        System.out.println("|           Seleccione una opcion               |");
        opcionMenu = opcionSeleccionada.next();
        //despliegue de menu pasada en la opcion seleccionada
        switch(opcionMenu){
            case "1":
                Create create =  new Create();
                break;  
            case "2":
                Read read = new Read();
                break;
            case "3":
                Update update = new Update();
                break;
            case "4":
                Delete  delete = new Delete();
                break;
            case "5":
                System.exit(0);
                break;
            default:
                System.out.println("Seleccion invalidad");
                break;
        }
    }
}
