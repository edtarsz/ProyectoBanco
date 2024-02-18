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
public class RetiroSinCuentaDTO extends Operacion{
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

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    
    public String getFolio() {
        return folio;
    }

    public String getContraseñaRetiro() {
        return contraseñaRetiro;
    }

    public void setFolio() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        this.folio = sb.toString();
    }

    public void setContraseñaRetiro() {
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 3; i++){
            sb.append(random.nextInt(10));
        }
        
        
        for (int i = 0; i < 5; i++) {
            char randomChar = (char) ('a' + random.nextInt(26));
            sb.append(randomChar);
        }

        this.contraseñaRetiro = sb.toString();
    }
    
    
    
    
}
