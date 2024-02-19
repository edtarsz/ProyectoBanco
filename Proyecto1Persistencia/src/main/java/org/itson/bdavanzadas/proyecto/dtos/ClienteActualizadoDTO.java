/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import java.security.SecureRandom;
import org.itson.bdavanzadas.proyecto.excepciones.ValidacionDTOException;

/**
 *
 * @author Ramosz
 */
public class ClienteActualizadoDTO {

    String usuario;
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
    int edad;

    public String getUsuario() {
        return usuario;
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

    public int getEdad() {
        return edad;
    }

    public void setUsuario(String nombre) {
        if (nombre != null && nombre.length() >= 3) {
            StringBuilder sb = new StringBuilder();
            SecureRandom secureRandom = new SecureRandom();
            int longitud = 5;

            for (int i = 0; i < longitud; i++) {
                int digitoAleatorio = secureRandom.nextInt(10);
                sb.append(digitoAleatorio);
            }

            String primerosTresCaracteres = nombre.substring(0, Math.min(nombre.length(), 3));
            this.usuario = primerosTresCaracteres + sb.toString();
        } else {
            throw new IllegalArgumentException("Nombre no válido para generar el usuario");
        }
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

    public void setContraseña(char[] contraseña) {
        for (int i = 0; i < contraseña.length; i++) {
            contraseña[i] += 5;
        }
        this.contraseña = new String(contraseña);
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

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean esValido() throws ValidacionDTOException {
        return true;
    }

}