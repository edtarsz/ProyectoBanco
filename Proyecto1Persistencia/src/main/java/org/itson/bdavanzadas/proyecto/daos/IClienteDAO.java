/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import java.util.List;
import org.itson.bdavanzadas.proyecto.dtos.ClienteActualizadoDTO;
import org.itson.bdavanzadas.proyecto.dtos.ClienteDTO;
import org.itson.bdavanzadas.proyecto.dtos.CuentaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cliente;
import org.itson.bdavanzadas.proyectodominio.Cuenta;

/**
 * IClienteDAO es una interfaz que define los métodos para operaciones de persistencia relacionadas con la entidad Cliente y su relación con la entidad Cuenta.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public interface IClienteDAO {

    /**
     * Agrega un nuevo cliente a la base de datos.
     *
     * @param clienteNuevo Objeto ClienteDTO que contiene la información del nuevo cliente.
     * @return Objeto Cliente creado con la información proporcionada y el ID asignado por la base de datos.
     * @throws PersistenciaException Si ocurre un error al interactuar con la base de datos.
     */
    Cliente agregar(ClienteDTO clienteNuevo) throws PersistenciaException;

    /**
     * Actualiza la información de un cliente en la base de datos.
     *
     * @param idCliente ID del cliente que se desea actualizar.
     * @param clienteActualizado Objeto ClienteActualizadoDTO que contiene la nueva información del cliente.
     * @return Objeto Cliente con la información actualizada.
     * @throws PersistenciaException Si ocurre un error al interactuar con la base de datos.
     */
    Cliente actualizar(long idCliente, ClienteActualizadoDTO clienteActualizado) throws PersistenciaException;

    /**
     * Agrega una nueva cuenta asociada a un cliente en la base de datos.
     *
     * @param cuentaNueva Objeto CuentaDTO que contiene la información de la nueva cuenta.
     * @return Objeto Cuenta creado con la información proporcionada y el ID asignado por la base de datos.
     * @throws PersistenciaException Si ocurre un error al interactuar con la base de datos.
     */
    Cuenta agregarCuenta(CuentaDTO cuentaNueva) throws PersistenciaException;

    /**
     * Cambia el estado de una cuenta en la base de datos.
     *
     * @param numCuenta Número de cuenta de la cuenta cuyo estado se desea cambiar.
     * @param nuevoEstado Nuevo estado que se asignará a la cuenta.
     * @throws PersistenciaException Si ocurre un error al interactuar con la base de datos.
     */
    void cambiarEstadoCuenta(String numCuenta, String nuevoEstado) throws PersistenciaException;

    /**
     * Consulta las cuentas asociadas a un cliente en la base de datos.
     *
     * @param cliente Objeto Cliente para el cual se desean consultar las cuentas.
     * @return Lista de objetos Cuenta asociadas al cliente.
     * @throws PersistenciaException Si ocurre un error al interactuar con la base de datos.
     */
    List<Cuenta> consultarCuentas(Cliente cliente) throws PersistenciaException;

    /**
     * Consulta todos los clientes almacenados en la base de datos.
     *
     * @return Lista de objetos Cliente con la información de todos los clientes almacenados.
     * @throws PersistenciaException Si ocurre un error al interactuar con la base de datos.
     */
    List<Cliente> consultar() throws PersistenciaException;
}
