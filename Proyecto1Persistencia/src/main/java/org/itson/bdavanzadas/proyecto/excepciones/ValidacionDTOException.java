/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.excepciones;

/**
 *
 * @author Eduardo Talavera Ramos
 */
public class ValidacionDTOException extends Exception {

    public ValidacionDTOException() {
    }

    public ValidacionDTOException(String message) {
        super(message);
    }

    public ValidacionDTOException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidacionDTOException(Throwable cause) {
        super(cause);
    }

    public ValidacionDTOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
