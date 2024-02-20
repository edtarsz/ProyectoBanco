/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la interfaz IConexion y proporciona métodos para establecer y obtener conexiones a la base de datos.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class Conexion implements IConexion {

    // Atributos de la clase
    final String cadenaConexion;
    final String user;
    final String password;
    static final Logger logger = Logger.getLogger(Conexion.class.getName());

    // Constructor de la clase que inicializa los atributos
    public Conexion(String cadenaConexion, String user, String password) {
        this.cadenaConexion = cadenaConexion;
        this.user = user;
        this.password = password;
    }

    // Implementación del método obtenerConexion de la interfaz IConexion
    @Override
    public Connection obtenerConexion() throws SQLException {

        // Establece la conexión utilizando la clase DriverManager de JDBC
        Connection conexion = DriverManager.getConnection(cadenaConexion, user, password);

        // Registra un mensaje de información utilizando el Logger
        logger.log(Level.INFO, "Conexion establecida {0}", cadenaConexion);

        // Devuelve la conexión establecida
        return conexion;
    }
}
