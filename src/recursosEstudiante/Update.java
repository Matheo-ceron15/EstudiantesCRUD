package recursosEstudiante;

import java.sql.SQLException;
import java.util.Scanner;

public class Update {
    Update() throws SQLException{
        Scanner leer = new Scanner(System.in);
        Estudiante est = new Estudiante();
        ConexionCRUD utilerias = new ConexionCRUD();
        System.out.println("\n<< ACTUALIZAR REGISTROS >>");
        
        System.out.println("Ingresar id del a modificar: ");
        est.setIdEstudiante(leer.nextInt());
        //ingreso de datos a actualizar 
        String tablaBuscar = "tb_estudiante";
        String camposBuscar = "id_estudiante,carnet_estudiante, nom_estudiante, ape_estudiante, edad_estudiante";
        String condicion = "id_estudiante = " + est.getIdEstudiante();
        utilerias.desplegarRegistros(tablaBuscar, camposBuscar, condicion);
        
        System.out.println("Carnet: ");
        est.setCarnetEstudiante(leer.next());
        System.out.println("Nombre: ");
        est.setNomEstudiante(leer.next());
        System.out.println("Apellido: ");
        est.setApeEstudiante(leer.next());
        System.out.println("Edad: ");
        est.setEdadEstudiante(leer.nextInt());
        
        String tabla ="tb_estudiante";
        String camposValoresNuevos = "carnet_estudiante = '" + est.getCarnetEstudiante() + "', nom_estudiante = '" + est.getNomEstudiante() + 
                                     "', ape_estudiante = '" + est.getApeEstudiante() + "', edad_estudiante = '"+ est.getEdadEstudiante() + "'";
        
        utilerias.actualizarEliminarRegistro(tabla, camposValoresNuevos, condicion);
        System.out.println("Modificado correctamente");
        MenuPrincipal.desplegarMenu();
    }
}
