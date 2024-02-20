/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
// Paquete que contiene la interfaz de conexión
package org.itson.bdavanzadas.proyecto.conexion;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interfaz que define el "contrato" para obtener conexiones a la base de datos.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public interface IConexion {

    /**
     * Método para obtener una conexión a la base de datos.
     *
     * @return Una instancia de la interfaz Connection que representa la conexión a la base de datos.
     * @throws SQLException Si ocurre un error durante la obtención de la conexión.
     */
    Connection obtenerConexion() throws SQLException;
}
