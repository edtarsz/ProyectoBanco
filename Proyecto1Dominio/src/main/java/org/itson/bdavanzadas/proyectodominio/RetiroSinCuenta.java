/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyectodominio;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Ramosz
 */
public class RetiroSinCuenta extends Operacion {
    String idCuenta;
    String folio;
    String contraseñaRetiro;
    String estado;

    
    public RetiroSinCuenta(){
        
    }
    
    public RetiroSinCuenta(int idOperacion, LocalDateTime fechaHora, float monto, String folio, String contraseñaRetiro, String idCuenta, String estado) {
        super(idOperacion, fechaHora, monto);
        this.idOperacion = idOperacion;
        this.folio = folio;
        this.contraseñaRetiro = contraseñaRetiro;
        this.idCuenta = idCuenta;
        this.estado = estado;
    }

    public RetiroSinCuenta(String folio, String contraseñaRetiro) {
        this.folio = folio;
        this.contraseñaRetiro = contraseñaRetiro;
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

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setContraseñaRetiro(String contraseñaRetiro) {
        this.contraseñaRetiro = contraseñaRetiro;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.folio);
        hash = 89 * hash + Objects.hashCode(this.contraseñaRetiro);
        return hash;
    }

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
        final RetiroSinCuenta other = (RetiroSinCuenta) obj;
        if (!Objects.equals(this.folio, other.folio)) {
            return false;
        }
        return Objects.equals(this.contraseñaRetiro, other.contraseñaRetiro);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RetiroSinCuenta{");
        sb.append("folio=").append(folio);
        sb.append(", contrase\u00f1aRetiro=").append(contraseñaRetiro);
        sb.append('}');
        return sb.toString();
    }

}
