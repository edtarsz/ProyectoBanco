/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import org.itson.bdavanzadas.proyecto.dtos.RetiroSinCuentaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.RetiroSinCuenta;

/**
 *
 * @author JoseH
 */
public interface IRetiroDAO {
    RetiroSinCuenta solicitarRetiro(RetiroSinCuentaDTO retiro, ICuentaDAO cuentaDAO) throws PersistenciaException;
    void procesarRetiro(RetiroSinCuenta retiro) throws PersistenciaException;
}
