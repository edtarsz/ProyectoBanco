/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.proyecto.conexion.Conexion;
import org.itson.bdavanzadas.proyecto.conexion.IConexion;
import org.itson.bdavanzadas.proyecto.dtos.ClienteActualizadoDTO;
import org.itson.bdavanzadas.proyecto.dtos.ClienteDTO;
import org.itson.bdavanzadas.proyecto.dtos.CuentaDTO;
import org.itson.bdavanzadas.proyecto.dtos.TransferenciaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cliente;
import org.itson.bdavanzadas.proyectodominio.Cuenta;
import org.itson.bdavanzadas.proyectodominio.Transferencia;

/**
 *
 * @author Ramosz
 */
public class ClienteDAO implements IClienteDAO {

    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(Conexion.class.getName());

    public ClienteDAO(IConexion conexion) {
        this.conexionBD = conexion;
    }

    @Override
    public Cliente agregar(ClienteDTO clienteNuevo) throws PersistenciaException {
        String sentenciaSQL = """
        INSERT INTO clientes(Usuario, Nombre, ApellidoPaterno, ApellidoMaterno, Contraseña, FechaNacimiento, CodigoPostal, NumExterior, Calle, Colonia, Ciudad, Edad)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);""";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, clienteNuevo.getUsuario());
            comando.setString(2, clienteNuevo.getNombre());
            comando.setString(3, clienteNuevo.getApellidoPaterno());
            comando.setString(4, clienteNuevo.getApellidoMaterno());
            comando.setString(5, clienteNuevo.getContraseña());
            comando.setString(6, clienteNuevo.getFechaNacimiento());
            comando.setString(7, clienteNuevo.getCodigoPostal());
            comando.setString(8, clienteNuevo.getNumExterior());
            comando.setString(9, clienteNuevo.getCalle());
            comando.setString(10, clienteNuevo.getColonia());
            comando.setString(11, clienteNuevo.getCiudad());
            comando.setInt(12, clienteNuevo.getEdad());
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} socios", numeroRegistrosInsertados);

            ResultSet idGenerado = comando.getGeneratedKeys();
            idGenerado.next();

            Cliente cliente = new Cliente(
                    idGenerado.getInt(1),
                    clienteNuevo.getUsuario(),
                    clienteNuevo.getNombre(),
                    clienteNuevo.getApellidoPaterno(),
                    clienteNuevo.getApellidoMaterno(),
                    clienteNuevo.getContraseña(),
                    clienteNuevo.getFechaNacimiento(),
                    clienteNuevo.getCodigoPostal(),
                    clienteNuevo.getNumExterior(),
                    clienteNuevo.getCalle(),
                    clienteNuevo.getColonia(),
                    clienteNuevo.getCiudad(),
                    clienteNuevo.getEdad());
            return cliente;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo guardar el cliente", ex);
            throw new PersistenciaException("No se pudo guardar el cliente", ex);
        }
    }

    @Override
    public List<Cliente> consultar() throws PersistenciaException {
        String sentenciaSQL = "SELECT idCliente, Usuario, Nombre, ApellidoPaterno, ApellidoMaterno, Contraseña, FechaNacimiento, CodigoPostal, NumExterior, Calle, Colonia, Ciudad FROM clientes";

        List<Cliente> listaClientes = new LinkedList<>();

        try (Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL); ResultSet resultados = comando.executeQuery()) {

            while (resultados.next()) {
                int idCliente = resultados.getInt("idCliente");
                String usuario = resultados.getString("Usuario");
                String nombre = resultados.getString("Nombre");
                String apellidoPaterno = resultados.getString("ApellidoPaterno");
                String apellidoMaterno = resultados.getString("ApellidoMaterno");
                String contraseña = resultados.getString("Contraseña");
                String fechaNacimiento = resultados.getString("FechaNacimiento");
                String codigoPostal = resultados.getString("CodigoPostal");
                String numExterior = resultados.getString("NumExterior");
                String calle = resultados.getString("Calle");
                String colonia = resultados.getString("Colonia");
                String ciudad = resultados.getString("Ciudad");

                // Desencriptación
                char[] contraseñaArray = contraseña.toCharArray();
                for (int i = 0; i < contraseñaArray.length; i++) {
                    contraseñaArray[i] -= 5;
                }

                contraseña = new String(contraseñaArray);

                Cliente cliente = new Cliente(idCliente, usuario, nombre, apellidoPaterno, apellidoMaterno, contraseña, fechaNacimiento, codigoPostal, numExterior, calle, colonia, ciudad);
                listaClientes.add(cliente);
            }

            logger.log(Level.INFO, "Se consultaron {0} socios", listaClientes.size());
            return listaClientes;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudieron consultar los socios", ex);
            throw new PersistenciaException("No se pudieron consultar los socios", ex);
        }
    }

    @Override
    public Cuenta agregarCuenta(CuentaDTO cuentaNueva) throws PersistenciaException {
        String sentenciaSQL = """
        INSERT INTO cuentas(numCuenta, idCliente, saldo, fechaApertura, estado)
        VALUES (?, ?, ?, ?, ?);""";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, cuentaNueva.getNumCuenta());
            comando.setInt(2, cuentaNueva.getIdCliente());
            comando.setFloat(3, cuentaNueva.getSaldo());
            comando.setDate(4, new java.sql.Date(cuentaNueva.getFechaApertura().getTime()));
            comando.setString(5, cuentaNueva.getEstado());

            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} cuentas", numeroRegistrosInsertados);

            ResultSet idGenerado = comando.getGeneratedKeys();
            idGenerado.next();
            Cuenta cuenta = new Cuenta(
                    cuentaNueva.getNumCuenta(),
                    cuentaNueva.getIdCliente(),
                    cuentaNueva.getSaldo(),
                    cuentaNueva.getFechaApertura(),
                    cuentaNueva.getEstado());
            return cuenta;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo guardar la cuenta", ex);
            throw new PersistenciaException("No se pudo guardar la cuenta", ex);
        }
    }

    @Override
    public List<Cuenta> consultarCuentas(Cliente cliente) throws PersistenciaException {
        String sentenciaSQL = "SELECT numCuenta, saldo, estado FROM Cuentas WHERE idCliente = ?;";
        List<Cuenta> listaCuentas = new LinkedList<>();

        try (Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL)) {

            comando.setLong(1, cliente.getIdCliente());

            try (ResultSet resultados = comando.executeQuery()) {
                while (resultados.next()) {
                    String numCuenta = resultados.getString("numCuenta");
                    float saldo = resultados.getFloat("saldo");
                    String estado = resultados.getString("estado");

                    Cuenta cuenta = new Cuenta(cliente.getIdCliente(), numCuenta, saldo, estado);
                    listaCuentas.add(cuenta);
                }
            }

            logger.log(Level.INFO, "Se consultaron {0} cuentas para el cliente con ID {1}", new Object[]{listaCuentas.size(), cliente.getIdCliente()});
            return listaCuentas;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudieron consultar las cuentas para el cliente con ID " + cliente.getIdCliente(), ex);
            throw new PersistenciaException("No se pudieron consultar las cuentas", ex);
        }
    }

    @Override
    public Cliente actualizar(long idCliente, ClienteActualizadoDTO clienteActualizado) throws PersistenciaException {
        String sentenciaSQL = """
            UPDATE clientes
            SET Usuario = ?, Nombre = ?, ApellidoPaterno = ?, ApellidoMaterno = ?, Contraseña = ?, 
                FechaNacimiento = ?, CodigoPostal = ?, NumExterior = ?, Calle = ?, Colonia = ?, Ciudad = ?, Edad = ?
            WHERE idCliente = ?;""";

        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setString(1, clienteActualizado.getUsuario());
            comando.setString(2, clienteActualizado.getNombre());
            comando.setString(3, clienteActualizado.getApellidoPaterno());
            comando.setString(4, clienteActualizado.getApellidoMaterno());
            comando.setString(5, clienteActualizado.getContraseña());
            comando.setString(6, clienteActualizado.getFechaNacimiento());
            comando.setString(7, clienteActualizado.getCodigoPostal());
            comando.setString(8, clienteActualizado.getNumExterior());
            comando.setString(9, clienteActualizado.getCalle());
            comando.setString(10, clienteActualizado.getColonia());
            comando.setString(11, clienteActualizado.getCiudad());
            comando.setInt(12, clienteActualizado.getEdad());
            comando.setLong(13, idCliente);

            int numeroRegistrosActualizados = comando.executeUpdate();

            if (numeroRegistrosActualizados == 0) {
                logger.log(Level.SEVERE, "No se pudo actualizar el cliente, ninguna fila afectada");
                throw new PersistenciaException("No se pudo actualizar el cliente, ninguna fila afectada");
            }

            logger.log(Level.INFO, "Se actualizó el cliente con ID {0}", idCliente);

            Cliente cliente = new Cliente(
                    clienteActualizado.getUsuario(),
                    clienteActualizado.getNombre(),
                    clienteActualizado.getApellidoPaterno(),
                    clienteActualizado.getApellidoMaterno(),
                    clienteActualizado.getContraseña(),
                    clienteActualizado.getFechaNacimiento(),
                    clienteActualizado.getCodigoPostal(),
                    clienteActualizado.getNumExterior(),
                    clienteActualizado.getCalle(),
                    clienteActualizado.getColonia(),
                    clienteActualizado.getCiudad(),
                    clienteActualizado.getEdad());
            return cliente;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo actualizar el cliente", ex);
            throw new PersistenciaException("No se pudo actualizar el cliente", ex);
        }
    }

    @Override
    public void cambiarEstadoCuenta(String numCuenta, String nuevoEstado) throws PersistenciaException {
        String sentenciaSQL = "UPDATE cuentas SET estado = ? WHERE numCuenta = ?";

        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setString(1, nuevoEstado);
            comando.setString(2, numCuenta);

            int numeroRegistrosActualizados = comando.executeUpdate();
            if (numeroRegistrosActualizados > 0) {
                logger.log(Level.INFO, "Se cambió el estado de la cuenta con número de cuenta {0} a {1}", new Object[]{numCuenta, nuevoEstado});
            } else {
                logger.log(Level.WARNING, "No se encontró la cuenta con número de cuenta {0}", numCuenta);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo cambiar el estado de la cuenta", ex);
            throw new PersistenciaException("No se pudo cambiar el estado de la cuenta", ex);
        }
    }

}
