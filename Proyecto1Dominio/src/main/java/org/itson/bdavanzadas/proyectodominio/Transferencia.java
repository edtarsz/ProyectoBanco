/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyectodominio;

import java.time.LocalDateTime;

/**
 * Clase que representa una operación de transferencia entre cuentas en el sistema. Extiende de la clase Operacion.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class Transferencia extends Operacion {

    // Atributos adicionales para la clase Transferencia
    int idCuenta;
    int idCuentaDestino;

    // Constructor con parámetros para inicializar la transferencia con valores específicos
    public Transferencia(int idCuenta, int idCuentaDestino, int idOperacion, LocalDateTime fechaHora, float monto) {
        super(idOperacion, fechaHora, monto);
        this.idCuenta = idCuenta;
        this.idCuentaDestino = idCuentaDestino;
    }

    // Métodos getters para obtener valores de los atributos
    public int getIdCuenta() {
        return idCuenta;
    }

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    // Método hashCode para generar un código hash basado en los atributos
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.idCuenta;
        hash = 53 * hash + this.idCuentaDestino;
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
        final Transferencia other = (Transferencia) obj;
        if (this.idCuenta != other.idCuenta) {
            return false;
        }
        return this.idCuentaDestino == other.idCuentaDestino;
    }
}
