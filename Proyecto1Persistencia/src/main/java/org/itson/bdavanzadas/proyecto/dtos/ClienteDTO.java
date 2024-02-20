/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.dtos;

import java.security.SecureRandom;
import org.itson.bdavanzadas.proyecto.excepciones.ValidacionDTOException;

/**
 * DTO (Data Transfer Object) para representar la información de un cliente.
 *
 * Esta clase contiene atributos y métodos para representar la información básica de un cliente. También incluye validaciones para asegurar la integridad de los datos.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class ClienteDTO {

    // Atributos que representan la información del cliente
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

    // Método para obtener el usuario generado a partir del nombre
    public String getUsuario() {
        return usuario;
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

    // Método para establecer el usuario basado en el nombre, en este metodo se crea un usuario a partir de los primeros 3 caracteres del nombre y los 5 caracteres restantes son una cadena de números aleatorios generados utilizando la clase random.
    public void setUsuario(String nombre) {
        // Validar que el nombre no sea nulo y tenga al menos 3 caracteres
        if (nombre != null && nombre.length() >= 3) {
            // Generar un identificador único utilizando parte del nombre y números aleatorios
            StringBuilder sb = new StringBuilder();
            SecureRandom secureRandom = new SecureRandom();
            int longitud = 5;

            for (int i = 0; i < longitud; i++) {
                int digitoAleatorio = secureRandom.nextInt(10);
                sb.append(digitoAleatorio);
            }

            // Toma los primeros tres caracteres del nombre
            String primerosTresCaracteres = nombre.substring(0, Math.min(nombre.length(), 3));

            // Combina los caracteres del nombre con los números aleatorios generados
            this.usuario = primerosTresCaracteres + sb.toString();
        } else {
            // Lanza una excepción si el nombre no es válido para generar el usuario
            throw new IllegalArgumentException("Nombre no válido para generar el usuario");
        }
    }

    // Métodos para establecer los atributos
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

    // Este metodo es donde se encripta la contraseña a partir de una cadena de caracteres que recibe como atributo, utiliza un método muy sencillo que es aumentar en 5 posiciones el carácter.
    public void setContraseña(char[] contraseña) {
        // Modifica cada carácter de la contraseña sumándole 5 al valor ASCII
        for (int i = 0; i < contraseña.length; i++) {
            contraseña[i] += 5;
        }
        // Convierte el arreglo de caracteres modificado de nuevo a String
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

    // Método para validar la integridad de los datos
    public boolean esValido() throws ValidacionDTOException {
        // Validaciones de longitud para cada atributo y confirmar que no sean nulos
        if (this.nombre.trim().length() > 50) {
            throw new ValidacionDTOException("Nombre inválido");
        }
        if (this.apellidoMaterno.trim().length() > 50) {
            throw new ValidacionDTOException("Apellido materno inválido");
        }
        if (this.apellidoPaterno.trim().length() > 50) {
            throw new ValidacionDTOException("Apellido paterno inválido");
        }
        if (this.contraseña == null || this.apellidoPaterno.trim().length() > 20) {
            throw new ValidacionDTOException("Contraseña inválida");
        }
        if (this.fechaNacimiento == null || this.fechaNacimiento.isBlank()) {
            throw new ValidacionDTOException("Fecha de nacimiento inválido");
        }
        if (this.codigoPostal.trim().length() > 10) {
            throw new ValidacionDTOException("Código postal inválido");
        }
        if (this.numExterior.trim().length() > 10) {
            throw new ValidacionDTOException("Número exterior inválido");
        }
        if (this.calle.trim().length() > 30) {
            throw new ValidacionDTOException("Calle inválida");
        }
        if (this.colonia.trim().length() > 30) {
            throw new ValidacionDTOException("Colonia inválida");
        }
        if (this.ciudad.trim().length() > 20) {
            throw new ValidacionDTOException("Ciudad inválida");
        }
        if (this.edad < 18) {
            throw new ValidacionDTOException("Menor de edad no permitido");
        }
        // Si todas las validaciones son exitosas, retorna verdadero
        return true;
    }
}
