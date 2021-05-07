package recursosEstudiante;

import java.sql.SQLException;
import java.util.Scanner;

public class Create {
    Create () throws SQLException{
        Scanner leer = new Scanner(System.in);
        Estudiante est  = new Estudiante(); //objeto person de la clase persona
        System.out.println("\n<< CREAR REGISTRO >>");
        
        System.out.println("Carnet: ");
        est.setCarnetEstudiante(leer.next());
        System.out.println("Nombre: ");
        est.setNomEstudiante(leer.next());
        System.out.println("Apellidos:");
        est.setApeEstudiante(leer.next());
        System.out.println("Edad: ");
        est.setEdadEstudiante(leer.nextInt());
        
        String tabla = "tb_estudiante";
        String camposTabla = "carnet_estudiante, nom_estudiante, ape_estudiante, edad_estudiante";
        String valoresCampos = "'" + est.getCarnetEstudiante()+ "','" + est.getNomEstudiante()+ "','" + est.getApeEstudiante()+ "','" + est.getEdadEstudiante() + "'" ;
        
        //instancia de la conexion CRUD
        ConexionCRUD utilirias  = new ConexionCRUD();
        //SE ENVIAN LOS PARAMETROS NECESARIOS PARA GUARDAR EL REGISTRO AL METODO GUARDAR REGISTRO
        ConexionCRUD.guardarRegistros(tabla, camposTabla, valoresCampos);
        MenuPrincipal.desplegarMenu();
        
    }
}
