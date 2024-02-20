/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import org.itson.bdavanzadas.proyecto.dtos.RetiroSinCuentaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.RetiroSinCuenta;

/**
 * IRetiroDAO es una interfaz que define los métodos para solicitar, procesar y consultar retiros sin cuenta. Además, se utiliza la interfaz ICuentaDAO para interactuar con la información de cuentas en la base de datos.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public interface IRetiroDAO {

    /**
     * Solicita un retiro sin cuenta, registrando la solicitud y validando la información proporcionada.
     *
     * @param retiro Objeto RetiroSinCuentaDTO que contiene la información del retiro a solicitar.
     * @param cuentaDAO Instancia de ICuentaDAO utilizada para interactuar con la información de cuentas en la base de datos.
     * @return Objeto RetiroSinCuenta con la información del retiro solicitado.
     * @throws PersistenciaException Si ocurre un error al interactuar con la base de datos o si la solicitud de retiro es inválida.
     */
    RetiroSinCuenta solicitarRetiro(RetiroSinCuentaDTO retiro, ICuentaDAO cuentaDAO) throws PersistenciaException;

    /**
     * Procesa un retiro sin cuenta, actualizando la información correspondiente en la base de datos.
     *
     * @param retiro Objeto RetiroSinCuenta que contiene la información del retiro a procesar.
     * @return `true` si el retiro se procesó correctamente, `false` si ocurrió un error.
     * @throws PersistenciaException Si ocurre un error al interactuar con la base de datos o si el retiro no puede ser procesado.
     */
    boolean procesarRetiro(RetiroSinCuenta retiro) throws PersistenciaException;

    /**
     * Consulta la información de un retiro sin cuenta en base a la información proporcionada.
     *
     * @param retiroSinCuenta Objeto RetiroSinCuenta con la información del retiro a consultar.
     * @return Objeto RetiroSinCuenta con la información del retiro consultado.
     * @throws PersistenciaException Si ocurre un error al interactuar con la base de datos o si el retiro no puede ser consultado.
     */
    RetiroSinCuenta consultarRetiro(RetiroSinCuenta retiroSinCuenta) throws PersistenciaException;
}
