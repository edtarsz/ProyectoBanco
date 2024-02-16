/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import org.itson.bdavanzadas.proyecto.excepciones.ValidacionDTOException;
import org.itson.bdavanzadas.proyectodominio.Operacion;

/**
 *
 * @author JoseH
 */
public class TransferenciaDTO extends Operacion{
    int idCuenta;
    int idCuentaDestino;
    int idCliente;
    
    public int getIdCuenta() {
        return idCuenta;
    }

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public boolean esValido() throws ValidacionDTOException {
        if (Integer.toString(this.idCuentaDestino).isEmpty() || Integer.toString(this.idCuentaDestino) == null) {
            throw new ValidacionDTOException("Número de cuenta inválido");
        }
        return true;
    }
}
