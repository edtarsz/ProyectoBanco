/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyectodominio;

import java.util.Date;

/**
 *
 * @author Ramosz
 */
public class Transferencia extends Operacion {

    int idCuenta;
    int idCuentaDestino;
    int idCliente;

    public Transferencia(int idCuenta, int idCuentaDestino, int idOperacion, Date fechaHora, float monto) {
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

    public int getIdCliente() {
        return idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.idCuenta;
        hash = 29 * hash + this.idCuentaDestino;
        hash = 29 * hash + this.idCliente;
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
        if (this.idCuentaDestino != other.idCuentaDestino) {
            return false;
        }
        return this.idCliente == other.idCliente;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transferencia{");
        sb.append("idCuenta=").append(idCuenta);
        sb.append(", idCuentaDestino=").append(idCuentaDestino);
        sb.append(", idCliente=").append(idCliente);
        sb.append('}');
        return sb.toString();
    }

}
