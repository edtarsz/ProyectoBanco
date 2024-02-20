/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import org.itson.bdavanzadas.proyecto.dtos.TransferenciaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cuenta;
import org.itson.bdavanzadas.proyectodominio.Transferencia;

/**
 * ITransferenciaDAO es una interfaz que define el método para realizar transferencias entre cuentas.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public interface ITransferenciaDAO {

    /**
     * Realiza una transferencia entre cuentas, registrando la transacción y actualizando los saldos correspondientes.
     *
     * @param transferenciaNueva Objeto TransferenciaDTO que contiene la información de la transferencia a realizar.
     * @param cuenta Cuenta desde la cual se realizará la transferencia.
     * @return Objeto Transferencia con la información de la transferencia realizada.
     * @throws PersistenciaException Si ocurre un error al interactuar con la base de datos o si la transferencia no puede ser realizada.
     */
    Transferencia realizarTransferencia(TransferenciaDTO transferenciaNueva, Cuenta cuenta) throws PersistenciaException;
}
