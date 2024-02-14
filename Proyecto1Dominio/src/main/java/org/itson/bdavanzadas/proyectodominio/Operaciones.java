/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyectodominio;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Ramosz
 */
public class Operaciones {

    int idOperacion;
    Date fechaHora;
    float monto;

    public Operaciones() {
    }

    public Operaciones(int idOperacion, Date fechaHora, float monto) {
        this.idOperacion = idOperacion;
        this.fechaHora = fechaHora;
        this.monto = monto;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public float getMonto() {
        return monto;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idOperacion;
        hash = 89 * hash + Objects.hashCode(this.fechaHora);
        hash = 89 * hash + Float.floatToIntBits(this.monto);
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
        final Operaciones other = (Operaciones) obj;
        if (this.idOperacion != other.idOperacion) {
            return false;
        }
        if (Float.floatToIntBits(this.monto) != Float.floatToIntBits(other.monto)) {
            return false;
        }
        return Objects.equals(this.fechaHora, other.fechaHora);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Operaciones{");
        sb.append("idOperacion=").append(idOperacion);
        sb.append(", fechaHora=").append(fechaHora);
        sb.append(", monto=").append(monto);
        sb.append('}');
        return sb.toString();
    }

}
