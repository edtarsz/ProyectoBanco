/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import org.itson.bdavanzadas.proyecto.excepciones.ValidacionDTOException;

/**
 *
 * @author Ramosz
 */
public class ClienteNuevoDTO {

    int idCliente;
    int edad;
    String nombre;
    String apellidoPaterno;
    String apellidoMaterno;
    String contraseña;
    String fechaNacimiento;
    String codigoPostal;
    String numExterior;
    String calle;
    String colonia;
    String ciudad;

    public int getIdCliente() {
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

    public void setIdCliente(int idCliente) {
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

    public boolean esValido() throws ValidacionDTOException {
        if (this.nombre == null
                || this.nombre.isBlank()
                || this.nombre.trim().length() > 50) {
            throw new ValidacionDTOException("Teléfono de socio inválido");
        }
        if (this.apellidoMaterno == null
                || this.apellidoMaterno.isBlank()
                || this.apellidoMaterno.trim().length() > 50) {
            throw new ValidacionDTOException("Teléfono de socio inválido");
        }
        if (this.apellidoPaterno == null
                || this.apellidoPaterno.isBlank()
                || this.apellidoPaterno.trim().length() > 50) {
            throw new ValidacionDTOException("Teléfono de socio inválido");
        }
        if (this.contraseña == null
                || this.contraseña.isBlank()
                || this.contraseña.trim().length() > 20) {
            throw new ValidacionDTOException("Teléfono de socio inválido");
        }
        if (this.fechaNacimiento == null) {
            throw new ValidacionDTOException("Teléfono de socio inválido");
        }
        if (this.codigoPostal == null
                || this.codigoPostal.isBlank()
                || this.codigoPostal.trim().length() > 10) {
            throw new ValidacionDTOException("Teléfono de socio inválido");
        }
        if (this.numExterior == null
                || this.numExterior.isBlank()
                || this.numExterior.trim().length() > 10) {
            throw new ValidacionDTOException("Teléfono de socio inválido");
        }
        if (this.calle == null
                || this.calle.isBlank()
                || this.calle.trim().length() > 30) {
            throw new ValidacionDTOException("Teléfono de socio inválido");
        }
        if (this.colonia == null
                || this.colonia.isBlank()
                || this.colonia.trim().length() > 30) {
            throw new ValidacionDTOException("Teléfono de socio inválido");
        }
        if (this.ciudad == null
                || this.ciudad.isBlank()
                || this.ciudad.trim().length() > 20) {
            throw new ValidacionDTOException("Teléfono de socio inválido");
        }
        if (this.edad < 18) {
            throw new ValidacionDTOException("Edad de cliente inválido");
        }
        return true;
    }
}
