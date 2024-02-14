/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.conexion;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Eduardo Talavera Ramos
 */
public interface IConexion {

    Connection obtenerConexion() throws SQLException;
}
