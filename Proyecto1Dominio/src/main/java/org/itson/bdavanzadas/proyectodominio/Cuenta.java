/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyectodominio;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Ramosz
 */
public class Cuenta {

    String numCuenta;
    String estado;
    Long idCliente;
    float saldo;
    Date fechaApertura;

    public Cuenta() {
    }

    public Cuenta(Long idCliente, String numCuenta, float saldo) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.idCliente = idCliente;
    }

    public Cuenta(Long idCliente, float saldo, Date fechaApertura) {
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
    }

    public Cuenta(String numCuenta, Long idCliente, float saldo, Date fechaApertura) {
        this.numCuenta = numCuenta;
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
    }

    public String getEstado() {
        return estado;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public float getSaldo() {
        return saldo;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setNumCuenta() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        this.numCuenta = sb.toString();
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.numCuenta);
        hash = 41 * hash + Objects.hashCode(this.estado);
        hash = 41 * hash + Objects.hashCode(this.idCliente);
        hash = 41 * hash + Float.floatToIntBits(this.saldo);
        hash = 41 * hash + Objects.hashCode(this.fechaApertura);
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
        final Cuenta other = (Cuenta) obj;
        if (Float.floatToIntBits(this.saldo) != Float.floatToIntBits(other.saldo)) {
            return false;
        }
        if (!Objects.equals(this.numCuenta, other.numCuenta)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.idCliente, other.idCliente)) {
            return false;
        }
        return Objects.equals(this.fechaApertura, other.fechaApertura);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cuenta{");
        sb.append("numCuenta=").append(numCuenta);
        sb.append(", estado=").append(estado);
        sb.append(", idCliente=").append(idCliente);
        sb.append(", saldo=").append(saldo);
        sb.append(", fechaApertura=").append(fechaApertura);
        sb.append('}');
        return sb.toString();
    }

}
