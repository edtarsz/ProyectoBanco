/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import org.itson.bdavanzadas.proyecto.excepciones.ValidacionDTOException;
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

    public boolean esValido() throws ValidacionDTOException {
        if (Integer.toString(this.idCuenta).isBlank() || Integer.toString(this.idCuenta) == null || Integer.toString(this.idCuenta) == "") {
            throw new ValidacionDTOException("Número de cuenta inválido");
        }
        if (Integer.toString(this.idCuentaDestino).isBlank() || Integer.toString(this.idCuentaDestino) == null || Integer.toString(this.idCuentaDestino) == "") {
            throw new ValidacionDTOException("Número de cuenta inválido");
        }
        return true;
    }
}
