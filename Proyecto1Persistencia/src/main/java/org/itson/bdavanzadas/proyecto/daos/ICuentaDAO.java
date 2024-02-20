/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cuenta;

/**
 * ICuentaDAO es una interfaz que define el método para obtener información de una cuenta desde la base de datos.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public interface ICuentaDAO {

    /**
     * Obtiene la información de una cuenta en base al número de cuenta proporcionado.
     *
     * @param numCuenta Número de cuenta de la cuenta que se desea obtener.
     * @return Objeto Cuenta con la información de la cuenta correspondiente.
     * @throws PersistenciaException Si ocurre un error al interactuar con la base de datos o si la cuenta no existe.
     */
    Cuenta obtenerCuenta(String numCuenta) throws PersistenciaException;

    boolean obtenerCuentaBoolean(String numCuenta) throws PersistenciaException;
}
