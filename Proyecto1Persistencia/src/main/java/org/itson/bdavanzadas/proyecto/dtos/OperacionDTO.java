/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import java.util.Date;
import org.itson.bdavanzadas.proyecto.excepciones.ValidacionDTOException;

/**
 *
 * @author JoseH
 */
public class OperacionDTO {
    Date fechaHora;
    float monto;
    
    public Date getFechaHora() {
        return fechaHora;
    }

    public float getMonto() {
        return monto;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
     public boolean esValido() throws ValidacionDTOException {
        if (Float.toString(this.monto).isEmpty() || Float.toString(this.monto) == null) {
            throw new ValidacionDTOException("Número de cuenta inválido");
        }
        return true;
    } 
}
