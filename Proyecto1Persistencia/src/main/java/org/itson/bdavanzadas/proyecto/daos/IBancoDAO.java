/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import java.util.List;
import org.itson.bdavanzadas.proyecto.dtos.ClienteDTO;
import org.itson.bdavanzadas.proyecto.dtos.CuentaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cliente;
import org.itson.bdavanzadas.proyectodominio.Cuenta;

/**
 *
 * @author Ramosz
 */
public interface IBancoDAO {

    Cliente agregar(ClienteDTO clienteNuevo) throws PersistenciaException;

    Cuenta agregarCuenta(CuentaDTO cuentaNueva) throws PersistenciaException;

    List<Cliente> consultar() throws PersistenciaException;
};
