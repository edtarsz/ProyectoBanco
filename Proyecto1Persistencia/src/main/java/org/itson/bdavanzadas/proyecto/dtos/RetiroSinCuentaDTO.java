/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import java.security.SecureRandom;
import java.util.Random;
import org.itson.bdavanzadas.proyectodominio.Operacion;

/**
 *
 * @author JoseH
 */
public class RetiroSinCuentaDTO extends Operacion {

    String idCuenta;
    String folio;
    String contraseñaRetiro;
    String estado;

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

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

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
    
    public void setContraseñaRetiro(String contraseñaRetiro){
        this.contraseñaRetiro = contraseñaRetiro;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFolio() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        this.folio = sb.toString();
    }

}
