/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyectodominio;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Ramosz
 */
public class Transferencia extends Operacion {

    int idCuenta;
    int idCuentaDestino;

    public Transferencia(int idCuenta, int idCuentaDestino, int idOperacion, LocalDateTime fechaHora, float monto) {
        super(idOperacion, fechaHora, monto);
        this.idCuenta = idCuenta;
        this.idCuentaDestino = idCuentaDestino;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.idCuenta;
        hash = 53 * hash + this.idCuentaDestino;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transferencia other = (Transferencia) obj;
        if (this.idCuenta != other.idCuenta) {
            return false;
        }
        return this.idCuentaDestino == other.idCuentaDestino;
    }
}
