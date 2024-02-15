/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyectodominio;

import java.util.Objects;

/**
 *
 * @author Ramosz
 */
public class Cliente {

    private Long idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String contraseña;
    private String fechaNacimiento;
    private String codigoPostal;
    private String numExterior;
    private String calle;
    private String colonia;
    private String ciudad;
    private int edad;

    public Cliente() {
    }

    public Cliente(Long idCliente, String nombre, String contraseña) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String contraseña, String fechaNacimiento, String codigoPostal, String numExterior, String calle, String colonia, String ciudad, int edad) {
        this.edad = edad;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.codigoPostal = codigoPostal;
        this.numExterior = numExterior;
        this.calle = calle;
        this.colonia = colonia;
        this.ciudad = ciudad;
    }

    public Cliente(Long idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String contraseña, String fechaNacimiento, String codigoPostal, String numExterior, String calle, String colonia, String ciudad, int edad) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.codigoPostal = codigoPostal;
        this.numExterior = numExterior;
        this.calle = calle;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.edad = edad;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getNumExterior() {
        return numExterior;
    }

    public String getCalle() {
        return calle;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setNumExterior(String numExterior) {
        this.numExterior = numExterior;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.idCliente);
        hash = 83 * hash + Objects.hashCode(this.nombre);
        hash = 83 * hash + Objects.hashCode(this.apellidoPaterno);
        hash = 83 * hash + Objects.hashCode(this.apellidoMaterno);
        hash = 83 * hash + Objects.hashCode(this.contraseña);
        hash = 83 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 83 * hash + Objects.hashCode(this.codigoPostal);
        hash = 83 * hash + Objects.hashCode(this.numExterior);
        hash = 83 * hash + Objects.hashCode(this.calle);
        hash = 83 * hash + Objects.hashCode(this.colonia);
        hash = 83 * hash + Objects.hashCode(this.ciudad);
        hash = 83 * hash + this.edad;
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
        final Cliente other = (Cliente) obj;
        if (this.edad != other.edad) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidoPaterno, other.apellidoPaterno)) {
            return false;
        }
        if (!Objects.equals(this.apellidoMaterno, other.apellidoMaterno)) {
            return false;
        }
        if (!Objects.equals(this.contraseña, other.contraseña)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        if (!Objects.equals(this.codigoPostal, other.codigoPostal)) {
            return false;
        }
        if (!Objects.equals(this.numExterior, other.numExterior)) {
            return false;
        }
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        if (!Objects.equals(this.colonia, other.colonia)) {
            return false;
        }
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        return Objects.equals(this.idCliente, other.idCliente);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("idCliente=").append(idCliente);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellidoPaterno=").append(apellidoPaterno);
        sb.append(", apellidoMaterno=").append(apellidoMaterno);
        sb.append(", contrase\u00f1a=").append(contraseña);
        sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append(", codigoPostal=").append(codigoPostal);
        sb.append(", numExterior=").append(numExterior);
        sb.append(", calle=").append(calle);
        sb.append(", colonia=").append(colonia);
        sb.append(", ciudad=").append(ciudad);
        sb.append(", edad=").append(edad);
        sb.append('}');
        return sb.toString();
    }

}
