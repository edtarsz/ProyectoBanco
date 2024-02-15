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

    int numCuenta;
    Long idCliente;
    float saldo;
    String fechaApertura;

    public Cuenta() {
    }

    public Cuenta(Long idCliente, float saldo, String fechaApertura) {
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
    }

    public Cuenta(int numCuenta, Long idCliente, float saldo, String fechaApertura) {
        this.numCuenta = numCuenta;
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public float getSaldo() {
        return saldo;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setNumCuenta() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        this.numCuenta = Integer.parseInt(sb.toString());
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.numCuenta;
        hash = 47 * hash + Objects.hashCode(this.idCliente);
        hash = 47 * hash + Float.floatToIntBits(this.saldo);
        hash = 47 * hash + Objects.hashCode(this.fechaApertura);
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
        if (this.numCuenta != other.numCuenta) {
            return false;
        }
        if (Float.floatToIntBits(this.saldo) != Float.floatToIntBits(other.saldo)) {
            return false;
        }
        if (!Objects.equals(this.fechaApertura, other.fechaApertura)) {
            return false;
        }
        return Objects.equals(this.idCliente, other.idCliente);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cuenta{");
        sb.append("numCuenta=").append(numCuenta);
        sb.append(", idCliente=").append(idCliente);
        sb.append(", saldo=").append(saldo);
        sb.append(", fechaApertura=").append(fechaApertura);
        sb.append('}');
        return sb.toString();
    }

}
