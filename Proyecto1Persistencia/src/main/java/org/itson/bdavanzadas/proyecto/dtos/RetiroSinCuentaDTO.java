/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import java.security.SecureRandom;
import java.util.Random;
import org.itson.bdavanzadas.proyecto.excepciones.ValidacionDTOException;
import org.itson.bdavanzadas.proyectodominio.Operacion;

/**
 * DTO (Data Transfer Object) para representar la información de un retiro sin cuenta.
 *
 * Esta clase hereda de la clase Operacion y agrega atributos y métodos específicos para un retiro sin cuenta. También incluye validaciones para asegurar la integridad de los datos.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class RetiroSinCuentaDTO extends Operacion {

    // Atributos adicionales para un retiro sin cuenta
    String idCuenta;
    String folio;
    String contraseñaRetiro;
    String estado;

    // Métodos para obtener los atributos específicos de un retiro sin cuenta
    public String getEstado() {
        return estado;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public String getFolio() {
        return folio;
    }

    public String getContraseñaRetiro() {
        return contraseñaRetiro;
    }

    // Métodos para establecer los atributos específicos de un retiro sin cuenta
    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    // Método para generar una contraseña encriptada de forma segura, primero genera la contraseña aleatoria y luego la encripta.
    public void setContraseñaEcriptada() {
        char[] contraseñaArray = new char[8];

        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 3; i++) {
            contraseñaArray[i] = (char) ('0' + random.nextInt(10));
        }

        for (int i = 3; i < 8; i++) {
            contraseñaArray[i] = (char) ('a' + random.nextInt(26));
        }

        for (int i = 0; i < contraseñaArray.length; i++) {
            contraseñaArray[i] += 5;
        }

        this.contraseñaRetiro = new String(contraseñaArray);
    }

    // Método para establecer la contraseña de retiro directamente
    public void setContraseñaRetiro(String contraseñaRetiro) {
        this.contraseñaRetiro = contraseñaRetiro;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método para generar un folio aleatorio utilizando la clase random
    public void setFolio() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        this.folio = sb.toString();
    }

    // Método para validar la integridad de los datos
    public boolean esValido() throws ValidacionDTOException {
        // Validaciones para cada atributo, corroborar que cumplan las condiciones de la base de datos.
        if (this.idCuenta == null) {
            throw new ValidacionDTOException("Número de cuenta inválido");
        }
        if (this.folio.length() > 30) {
            throw new ValidacionDTOException("Saldo inválido");
        }
        if (this.contraseñaRetiro.length() > 30) {
            throw new ValidacionDTOException("Fecha de apertura inválido");
        }
        if (this.estado == null || this.estado.length() > 20) {
            throw new ValidacionDTOException("Estado inválido: debe tener 20 caracteres o menos");
        }
        // Si todas las validaciones son exitosas, retorna verdadero
        return true;
    }
}
