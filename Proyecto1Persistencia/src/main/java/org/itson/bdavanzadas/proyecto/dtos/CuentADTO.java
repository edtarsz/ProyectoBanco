/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import java.util.Date;
import java.util.Random;
import org.itson.bdavanzadas.proyecto.excepciones.ValidacionDTOException;

/**
 *
 * @author Ramosz
 */
public class CuentaDTO {

    String numCuenta;
    int idCliente;
    float saldo;
    Date fechaApertura;
    String estado;

    public String getNumCuenta() {
        return numCuenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public float getSaldo() {
        return saldo;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public String getEstado() {
        return estado;
    }

    public void setNumCuenta() {
        Random random = new Random();
        int min = 100000000;
        int max = 999999999;

        int randomNumber = random.nextInt(max - min + 1) + min;
        this.numCuenta = Integer.toString(randomNumber);
    }

    public void setIdCliente(int idCliente) {
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

    public boolean esValido() throws ValidacionDTOException {
        if (this.numCuenta.isEmpty() || this.numCuenta == null) {
            throw new ValidacionDTOException("Número de cuenta inválido");
        }
        if (Long.toString(this.idCliente).isEmpty() || Long.toString(this.idCliente) == null) {
            throw new ValidacionDTOException("Cliente inválido");
        }
        if (this.saldo < 0 || Float.toString(this.saldo).isEmpty() || Float.toString(this.saldo) == null) {
            throw new ValidacionDTOException("Saldo inválido");
        }
        if (this.fechaApertura == null) {
            throw new ValidacionDTOException("Fecha de apertura inválido");
        }
        return true;
    }
}
