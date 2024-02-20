/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import org.itson.bdavanzadas.proyecto.excepciones.ValidacionDTOException;
import org.itson.bdavanzadas.proyectodominio.Operacion;

/**
 * DTO (Data Transfer Object) para representar la información de una transferencia.
 *
 * Esta clase hereda de la clase Operacion y agrega atributos y métodos específicos para una transferencia. También incluye validaciones para asegurar la integridad de los datos.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class TransferenciaDTO extends Operacion {

    // Atributos adicionales para una transferencia
    int idCuenta;
    int idCuentaDestino;

    // Métodos para obtener los atributos específicos de una transferencia
    public int getIdCuenta() {
        return idCuenta;
    }

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    // Métodos para establecer los atributos específicos de una transferencia
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    // Método para validar la integridad de los datos
    public boolean esValido() throws ValidacionDTOException {
        // Validaciones para cada atributo, comprueba que los atributos no estén vacíos ni nulos.
        if (Integer.toString(this.idCuenta).isBlank() || Integer.toString(this.idCuenta) == null || Integer.toString(this.idCuenta) == "") {
            throw new ValidacionDTOException("Número de cuenta inválido");
        }
        if (Integer.toString(this.idCuentaDestino).isBlank() || Integer.toString(this.idCuentaDestino) == null || Integer.toString(this.idCuentaDestino) == "") {
            throw new ValidacionDTOException("Número de cuenta destino inválido");
        }
        // Si todas las validaciones son exitosas, retornar verdadero
        return true;
    }
}
