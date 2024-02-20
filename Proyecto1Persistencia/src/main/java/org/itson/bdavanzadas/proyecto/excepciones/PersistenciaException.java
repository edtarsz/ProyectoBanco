/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.excepciones;

/**
 * Excepción personalizada para manejar errores relacionados con la persistencia de datos.
 *
 * Esta clase extiende la clase Exception, proporcionando opciones adicionales para manejar mensajes de error y excepciones secundarias.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class PersistenciaException extends Exception {

    /**
     * Constructor predeterminado de la excepción.
     */
    public PersistenciaException() {
    }

    /**
     * Constructor que permite especificar un mensaje personalizado.
     *
     * @param message Mensaje de error personalizado.
     */
    public PersistenciaException(String message) {
        super(message);
    }

    /**
     * Constructor que permite especificar un mensaje y una causa.
     *
     * @param message Mensaje de error personalizado.
     * @param cause Causa de la excepción.
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor que permite especificar solo la causa de la excepción.
     *
     * @param cause Causa de la excepción.
     */
    public PersistenciaException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor que permite especificar un mensaje, una causa, y opciones adicionales.
     *
     * @param message Mensaje de error personalizado.
     * @param cause Causa de la excepción.
     * @param enableSuppression Indica si la supresión de la excepción está habilitada o deshabilitada.
     * @param writableStackTrace Indica si el seguimiento de la pila debe ser escribible.
     */
    public PersistenciaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
