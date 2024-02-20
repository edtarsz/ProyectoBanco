/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyectodominio;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

/**
 * Clase que representa una Cuenta en el sistema.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class Cuenta {

    // Atributos de la clase Cuenta
    String numCuenta;
    String estado;
    int idCliente;
    float saldo;
    Date fechaApertura;

    // Constructor predeterminado de cuenta
    public Cuenta() {
    }

    // Constructor con parámetros de información básica de la cuenta
    public Cuenta(int idCliente, float saldo, Date fechaApertura) {
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
    }

    // Constructor con parámetros incluyendo el número de cuenta y el estado
    public Cuenta(int idCliente, String numCuenta, float saldo, String estado) {
        this.idCliente = idCliente;
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.estado = estado;
    }

    // Constructor con parámetros incluyendo el número de cuenta, el ID del cliente y la fecha de apertura
    public Cuenta(String numCuenta, int idCliente, float saldo, Date fechaApertura, String estado) {
        this.numCuenta = numCuenta;
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
        this.estado = estado;
    }

    // Métodos getters para obtener valores de los atributos
    public String getEstado() {
        return estado;
    }

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

    // Método setter para generar un número de cuenta aleatorio
    public void setNumCuenta() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        this.numCuenta = sb.toString();
    }

    // Métodos setters para establecer valores a los atributos
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

    // Método hashCode para generar un código hash basado en los atributos
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.numCuenta);
        hash = 59 * hash + Objects.hashCode(this.estado);
        hash = 59 * hash + Objects.hashCode(this.idCliente);
        hash = 59 * hash + Float.floatToIntBits(this.saldo);
        hash = 59 * hash + Objects.hashCode(this.fechaApertura);
        return hash;
    }

    // Método equals para comparar la igualdad de objetos basándose en los atributos
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

    // Método toString para obtener una representación de cadena del objeto Cuenta
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
