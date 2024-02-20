/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import java.util.Date;
import java.util.Random;
import org.itson.bdavanzadas.proyecto.excepciones.ValidacionDTOException;

/**
 * DTO (Data Transfer Object) para representar la información de una cuenta.
 *
 * Esta clase contiene atributos y métodos para representar la información básica de una cuenta. También incluye validaciones para asegurar la integridad de los datos.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class CuentaDTO {

    // Atributos que representan la información de la cuenta
    String numCuenta;
    int idCliente;
    float saldo;
    Date fechaApertura;
    String estado;

    // Métodos para obtener el número de cuenta
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

    // Método para generar un número de cuenta aleatorio único (en la base de datos se mantiene único)
    public void setNumCuenta() {
        Random random = new Random();
        int min = 100000000;
        int max = 999999999;

        int randomNumber = random.nextInt(max - min + 1) + min;

        // Convierte el número aleatorio a String y lo establece como el número de cuenta
        this.numCuenta = Integer.toString(randomNumber);
    }

    // Métodos para establecer los demás atributos
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

    // Método para validar la integridad de los datos
    public boolean esValido() throws ValidacionDTOException {
        // Validaciones para cada atributo, comprueba que no sean nulos y el saldo no sea negativo.
        if (this.numCuenta == null) {
            throw new ValidacionDTOException("Número de cuenta inválido");
        }
        if (Long.toString(this.idCliente) == null) {
            throw new ValidacionDTOException("Cliente inválido");
        }
        if (this.saldo < 0) {
            throw new ValidacionDTOException("Saldo inválido");
        }
        if (this.fechaApertura == null) {
            throw new ValidacionDTOException("Fecha de apertura inválido");
        }
        if (this.estado == null || this.estado.length() > 20) {
            throw new ValidacionDTOException("Estado inválido: debe tener 20 caracteres o menos");
        }
        // Si todas las validaciones son exitosas, retorna verdadero
        return true;
    }
}
