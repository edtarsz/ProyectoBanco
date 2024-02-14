/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import org.itson.bdavanzadas.proyecto.dtos.ClienteNuevoDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cliente;

/**
 *
 * @author Ramosz
 */
public interface IBancoDAO {

    Cliente agregar(ClienteNuevoDTO clienteNuevo) throws PersistenciaException;
};
