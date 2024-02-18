/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import org.itson.bdavanzadas.proyecto.dtos.TransferenciaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Transferencia;

/**
 *
 * @author JoseH
 */
public interface ITransferenciaDAO {
    Transferencia realizarTransferencia(TransferenciaDTO transferenciaNueva) throws PersistenciaException;
}
