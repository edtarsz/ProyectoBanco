/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import org.itson.bdavanzadas.proyectodominio.Operacion;

/**
 *
 * @author Ramosz
 */
public class TransferenciaDTO extends Operacion {

    int idCuenta;
    int idCuentaDestino;

    public int getIdCuenta() {
        return idCuenta;
    }

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

}
