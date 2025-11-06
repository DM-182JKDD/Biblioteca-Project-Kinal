package org.joseescobar.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Controla la única conexión a la base de datos (Patrón Singleton).
 * * @author Jose Daniel Escobar Macario
 */
public class Conexion {
    
    private static Conexion instancia = null;
    private Connection conexion;
    
    // Variables de configuración de la BD
    private final String cadenaDeConexionJDBC = "com.mysql.cj.jdbc.Driver";
    private final String usuario = "IN4AM";
    private final String contrasenia = "Kinal@2025AM";
    private final String urlBD = "jdbc:mysql://localhost:3306/bd_biblioteca?useSSL=false&allowPublicKeyRetrieval=true";

    private Conexion() {
        abrirConexion();
    }//Conexion
    
    private void abrirConexion() {
        try {
            Class.forName(cadenaDeConexionJDBC);
            conexion = DriverManager.getConnection(urlBD, usuario, contrasenia);
        } catch (Exception error) {
            System.err.println("Error al conectar con la base de datos:");
            error.printStackTrace();
        }//try catch
    }//abrirConexion

    public synchronized static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }//if
        return instancia;
    }//getInstancia
    
    public Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                abrirConexion();
            }//if
        } catch (SQLException e) {
            System.err.println("Error al verificar el estado de la conexión:");
            e.printStackTrace();
        }//try catch
        return conexion;
    }//getConexion

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión a la BD cerrada correctamente.");
            }//if
        } catch (SQLException error) {
            System.err.println("Error al cerrar la conexión:");
            error.printStackTrace();
        }//try catch
    }//cerrarConexion
}//class