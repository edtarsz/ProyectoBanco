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
 *
 * @author Eduardo Talavera Ramos
 */
public class Conexion implements IConexion {

    final String cadenaConexion;
    final String user;
    final String password;
    static final Logger logger = Logger.getLogger(Conexion.class.getName());

    public Conexion(String cadenaConexion, String user, String password) {
        this.cadenaConexion = cadenaConexion;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection obtenerConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(cadenaConexion, user, password);
        logger.log(Level.INFO, "Conexion establecida {0}", cadenaConexion);
        return conexion;
    }
}
