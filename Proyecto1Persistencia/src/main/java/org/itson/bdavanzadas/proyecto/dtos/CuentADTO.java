/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import java.util.Random;
import org.itson.bdavanzadas.proyecto.excepciones.ValidacionDTOException;

/**
 *
 * @author Ramosz
 */
public class CuentaDTO {

    int numCuenta;
    Long idCliente;
    float saldo;
    String fechaApertura;

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

    public boolean esValido() throws ValidacionDTOException {
        if (Integer.toString(this.numCuenta).isEmpty() || Integer.toString(this.numCuenta) == null) {
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
