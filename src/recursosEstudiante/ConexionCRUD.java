package recursosEstudiante;

import java.sql.*;

public class ConexionCRUD {
    /*ruta de la base de datos el servidor 127.0.0.1m el puerto 306 
    y el nomobre de la base de daos db_recursos_humanos*/ 
    private final String servidor = "jdbc:mysql://127.0.0.1:3306/estudiante";
    //Nombre del usuario (root por defecto) de la base de datos
    private final String usuario = "root";
    // clave del usuario de la bse de datos
    private final String clave = "";
    //libreria de mysql
    private final String driverConector = "com.mysql.jdbc.Driver";
    //opjeto de la clase connection del paquete java.sql
    private static Connection conexion;
    
    public ConexionCRUD(){
        try{
            Class.forName(driverConector); //levantar el driver
            //establecer conexion
            conexion= DriverManager.getConnection(servidor, usuario, clave);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Conecion fallida! : " + e.getMessage());
        }
    }
    public Connection  getConnection(){
        return conexion;
    }
    //metodo guardar
    public static void guardarRegistros(String tabla, String campoTabla, String valoresCampos) {
        //cargar conexion
        ConexionCRUD conectar = new ConexionCRUD();
        Connection cone = conectar.getConnection();
        try{
            //Definir la sentencia sql
            String sqlQueryStmt = "INSERT INTO " + tabla  + "(" + campoTabla + ") VALUES (" + valoresCampos + ");";
            //Establecemos la comunicacion entre nuestra aplicacion java y la base de datos
            Statement stmt;
            stmt = cone.createStatement();
            stmt.executeUpdate(sqlQueryStmt);//Ejecutar la sentencia sql
            //cerrar el Statement y la conexion: se cierran en orden inverso de como se han abierto
            stmt.close();
            cone.close();
            System.out.println("Registro guardado correctamente!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
        
    //metodo eliminar y actualizar
    public void actualizarEliminarRegistro(String tabla, String valoresCamposNuevos, String condicion){
        //cargar la conexion
        ConexionCRUD conectar = new ConexionCRUD();
        Connection cone = conectar.getConnection();
        try{
            Statement stmt;
            String sqlQueryStmt;
            //verificar que vaorescamposnuevos venga vacia y asi seleccionar si es borrar o actualizar registro
            if(valoresCamposNuevos.isEmpty()){
                sqlQueryStmt = "DELETE FROM " + tabla + " WHERE " + condicion + ";";
            }else{
                sqlQueryStmt = "UPDATE " + tabla + " SET " + valoresCamposNuevos + " WHERE " + condicion + ";";
            }
            stmt = cone.createStatement();
            stmt.executeUpdate(sqlQueryStmt);
            stmt.close();
            cone.close();
        }catch(SQLException ex){
            System.out.println("Ha ocurrido el siguiente error: " + ex.getMessage());
        }
    }
    
     //metodo para consultas
    public static void desplegarRegistros(String tablaBuscar, String camposBuscar, String condicionBuscar) throws SQLException {
        //cargar la conexion
        ConexionCRUD conectar = new ConexionCRUD();
        Connection cone = conectar.getConnection();
        try{
            Statement stmt;
            String sqlQueryStmt;
            if(condicionBuscar.equals("")){
                sqlQueryStmt = "SELECT " + camposBuscar + " FROM " + tablaBuscar + ";";
            }else{
                sqlQueryStmt = "SELECT " + camposBuscar + " FROM " + tablaBuscar + " WHERE " + condicionBuscar; 
            }
            stmt = cone.createStatement();
            stmt.executeQuery(sqlQueryStmt); 
            //le indicamos que ejecute la consulta de la tabla y le pasamos por argumentos nuestra sentencia
            try(ResultSet miResultSet = stmt.executeQuery(sqlQueryStmt)){
                if(miResultSet.next()){ //ubica el cursor en la primera fila de la tabla de resultado
                    ResultSetMetaData metaData =  miResultSet.getMetaData();
                    int numColumnas = metaData.getColumnCount(); //obtiene el numero de columnas de la consulta
                    System.out.println("<< REGISTROS ALMACENADOS >>");
                    System.out.println();
                    for(int i =1; i<= numColumnas; i++){
                        //muestra los titulos de las colimnas y %-s/t indica la separacion entre columnas
                        System.out.printf("%-20s\t" , metaData.getColumnName(i));
                    }
                    System.out.println();
                    do{
                        for(int i=1; i<=numColumnas; i++){
                            System.out.printf("%-20s\t" , miResultSet.getObject(i));
                        }
                        System.out.println();
                    }while(miResultSet.next());
                    System.out.println();
                }else{
                    System.out.println("No se han encontrado registros");
                }
                miResultSet.close();
            }finally{
                stmt.close();
                cone.close();
            }
        }catch(SQLException ex){
            System.out.println("Ha ocurrido el siguiente error: " + ex.getMessage());
        }
    }
}
