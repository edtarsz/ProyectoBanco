/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import java.util.List;
import org.itson.bdavanzadas.proyecto.dtos.ClienteDTO;
import org.itson.bdavanzadas.proyecto.dtos.CuentaDTO;
import org.itson.bdavanzadas.proyecto.dtos.OperacionDTO;
import org.itson.bdavanzadas.proyecto.dtos.TransferenciaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cliente;
import org.itson.bdavanzadas.proyectodominio.Cuenta;
import org.itson.bdavanzadas.proyectodominio.Operacion;
import org.itson.bdavanzadas.proyectodominio.Transferencia;

/**
 *
 * @author Ramosz
 */
public interface IBancoDAO {

    Cliente agregar(ClienteDTO clienteNuevo) throws PersistenciaException;

    Cuenta agregarCuenta(CuentaDTO cuentaNueva) throws PersistenciaException;

    List<Cliente> consultar() throws PersistenciaException;
    
    List<Cuenta> consultarCuentas(Cliente cliente) throws PersistenciaException;
    
    public Operacion agregarOperacion(OperacionDTO operacionNueva) throws PersistenciaException;
    
    public Transferencia realizarTransferencia(TransferenciaDTO transferencia) throws PersistenciaException;
};
