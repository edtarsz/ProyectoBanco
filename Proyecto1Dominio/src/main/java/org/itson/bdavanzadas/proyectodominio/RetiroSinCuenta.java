/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyectodominio;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase que representa un retiro sin asociación a una cuenta específica. Extiende la clase Operacion.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class RetiroSinCuenta extends Operacion {

    // Atributos adicionales para un retiro sin cuenta
    String idCuenta;
    String folio;
    String contraseñaRetiro;
    String estado;

    // Constructor predeterminado de retiro sin cuenta
    public RetiroSinCuenta() {
    }

    // Constructor con parámetros para inicializar un retiro sin cuenta con valores específicos
    public RetiroSinCuenta(int idOperacion, LocalDateTime fechaHora, float monto, String folio, String contraseñaRetiro, String idCuenta, String estado) {
        super(idOperacion, fechaHora, monto);
        this.folio = folio;
        this.contraseñaRetiro = contraseñaRetiro;
        this.idCuenta = idCuenta;
        this.estado = estado;
    }

    // Constructor con parámetros más simples para crear un retiro sin cuenta
    public RetiroSinCuenta(String folio, String contraseñaRetiro) {
        this.folio = folio;
        this.contraseñaRetiro = contraseñaRetiro;
    }

    // Métodos getters para obtener valores de los atributos
    public String getIdCuenta() {
        return idCuenta;
    }

    public String getFolio() {
        return folio;
    }

    public String getContraseñaRetiro() {
        return contraseñaRetiro;
    }

    // Métodos setters para establecer valores a los atributos
    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setContraseñaRetiro(String contraseñaRetiro) {
        this.contraseñaRetiro = contraseñaRetiro;
    }

    // Método hashCode para generar un código hash basado en los atributos específicos de RetiroSinCuenta
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.folio);
        hash = 89 * hash + Objects.hashCode(this.contraseñaRetiro);
        return hash;
    }

    // Método equals para comparar la igualdad de objetos basándose en los atributos específicos de RetiroSinCuenta
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

    // Método toString para obtener una representación de cadena del objeto RetiroSinCuenta
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RetiroSinCuenta{");
        sb.append("folio=").append(folio);
        sb.append(", contraseñaRetiro=").append(contraseñaRetiro);
        sb.append('}');
        return sb.toString();
    }
}
