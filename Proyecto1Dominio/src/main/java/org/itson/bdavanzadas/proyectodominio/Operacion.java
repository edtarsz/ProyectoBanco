/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyectodominio;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase que representa una operación financiera en el sistema.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class Operacion {

    // Atributos de la clase Operacion
    int idOperacion;
    LocalDateTime fechaHora;
    float monto;

    // Constructor predeterminado de operacion
    public Operacion() {
    }

    // Constructor con parámetros para inicializar la operación con valores específicos
    public Operacion(int idOperacion, LocalDateTime fechaHora, float monto) {
        this.idOperacion = idOperacion;
        this.fechaHora = fechaHora;
        this.monto = monto;
    }

    // Métodos getters para obtener valores de los atributos
    public int getIdOperacion() {
        return idOperacion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public float getMonto() {
        return monto;
    }

    // Métodos setters para establecer valores a los atributos
    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    // Método hashCode para generar un código hash basado en los atributos
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idOperacion;
        hash = 89 * hash + Objects.hashCode(this.fechaHora);
        hash = 89 * hash + Float.floatToIntBits(this.monto);
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
        final Operacion other = (Operacion) obj;
        if (this.idOperacion != other.idOperacion) {
            return false;
        }
        if (Float.floatToIntBits(this.monto) != Float.floatToIntBits(other.monto)) {
            return false;
        }
        return Objects.equals(this.fechaHora, other.fechaHora);
    }

    // Método toString para obtener una representación de cadena del objeto Operacion
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
