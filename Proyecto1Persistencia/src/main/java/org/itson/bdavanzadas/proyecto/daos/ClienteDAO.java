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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.proyecto.conexion.Conexion;
import org.itson.bdavanzadas.proyecto.conexion.IConexion;
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
                    idGenerado.getLong(1),
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
        String sentenciaSQL = "SELECT idCliente, usuario, nombre, contraseña FROM clientes;";
        List<Cliente> listaClientes = new LinkedList<>();

        try (Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL); ResultSet resultados = comando.executeQuery()) {

            while (resultados.next()) {
                Long idCliente = resultados.getLong("idCliente");
                String usuario = resultados.getString("usuario");
                String nombre = resultados.getString("nombre");
                String contraseña = resultados.getString("contraseña");

                // Desencriptación
                char[] contraseñaArray = contraseña.toCharArray();
                for (int i = 0; i < contraseñaArray.length; i++) {
                    contraseñaArray[i] -= 5;
                }

                contraseña = new String(contraseñaArray);

                Cliente cliente = new Cliente(idCliente, usuario, nombre, contraseña);
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
        INSERT INTO cuentas(numCuenta, idCliente, saldo, fechaApertura)
        VALUES (?, ?, ?, ?);""";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setInt(1, cuentaNueva.getNumCuenta());
            comando.setLong(2, cuentaNueva.getIdCliente());
            comando.setFloat(3, cuentaNueva.getSaldo());
            comando.setDate(4, new java.sql.Date(cuentaNueva.getFechaApertura().getTime()));

            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} cuentas", numeroRegistrosInsertados);

            ResultSet idGenerado = comando.getGeneratedKeys();
            idGenerado.next();
            Cuenta cuenta = new Cuenta(
                    cuentaNueva.getNumCuenta(),
                    cuentaNueva.getIdCliente(),
                    cuentaNueva.getSaldo(),
                    cuentaNueva.getFechaApertura());
            return cuenta;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo guardar la cuenta", ex);
            throw new PersistenciaException("No se pudo guardar la cuenta", ex);
        }
    }

    @Override
    public List<Cuenta> consultarCuentas(Cliente cliente) throws PersistenciaException {
        String sentenciaSQL = "SELECT numCuenta, saldo FROM Cuentas WHERE idCliente = ?;";
        List<Cuenta> listaCuentas = new LinkedList<>();

        try (Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL)) {

            comando.setLong(1, cliente.getIdCliente());

            try (ResultSet resultados = comando.executeQuery()) {
                while (resultados.next()) {
                    int numCuenta = resultados.getInt("numCuenta");
                    float saldo = resultados.getFloat("saldo");

                    Cuenta cuenta = new Cuenta(cliente.getIdCliente(), numCuenta, saldo);
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
    public Transferencia realizarTransferencia(TransferenciaDTO transferenciaNueva) throws PersistenciaException {
        String sentenciaSQLOperacion = """
        INSERT INTO operaciones(fechaHora, monto)
        VALUES (?, ?);""";

        String sentenciaSQLTransferencia = """
        INSERT INTO transferencias(idOperacion, idCuenta, idCuentaDestino)
        VALUES (?, ?, ?);""";

        String actualizarSaldoOrigenSQL = "UPDATE cuentas SET saldo = saldo - ? WHERE numCuenta = ?;";

        String actualizarSaldoDestinoSQL = "UPDATE cuentas SET saldo = saldo + ? WHERE numCuenta = ?;";

        try (Connection conexion = this.conexionBD.obtenerConexion()) {
            try (PreparedStatement comandoOperacion = conexion.prepareStatement(sentenciaSQLOperacion, Statement.RETURN_GENERATED_KEYS)) {
                comandoOperacion.setDate(1, new java.sql.Date(transferenciaNueva.getFechaHora().getTime()));
                comandoOperacion.setInt(2, (int) transferenciaNueva.getMonto());
                comandoOperacion.executeUpdate();

                try (ResultSet generatedKeys = comandoOperacion.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idOperacionGenerado = generatedKeys.getInt(1);

                        try (PreparedStatement comandoTransferencia = conexion.prepareStatement(sentenciaSQLTransferencia)) {
                            comandoTransferencia.setInt(1, idOperacionGenerado);
                            comandoTransferencia.setInt(2, transferenciaNueva.getIdCuenta());
                            comandoTransferencia.setInt(3, transferenciaNueva.getIdCuentaDestino());
                            comandoTransferencia.executeUpdate();
                        }

                        try (PreparedStatement comandoActualizarOrigen = conexion.prepareStatement(actualizarSaldoOrigenSQL)) {
                            comandoActualizarOrigen.setInt(1, (int) transferenciaNueva.getMonto());
                            comandoActualizarOrigen.setInt(2, transferenciaNueva.getIdCuenta());
                            comandoActualizarOrigen.executeUpdate();
                        }

                        try (PreparedStatement comandoActualizarDestino = conexion.prepareStatement(actualizarSaldoDestinoSQL)) {
                            comandoActualizarDestino.setInt(1, (int) transferenciaNueva.getMonto());
                            comandoActualizarDestino.setInt(2, transferenciaNueva.getIdCuentaDestino());
                            comandoActualizarDestino.executeUpdate();
                        }

                        Transferencia transferencia = new Transferencia(
                                transferenciaNueva.getIdCuenta(),
                                transferenciaNueva.getIdCuentaDestino(),
                                idOperacionGenerado,
                                transferenciaNueva.getFechaHora(),
                                transferenciaNueva.getMonto()
                        );

                        return transferencia;
                    } else {
                        throw new PersistenciaException("No se pudo obtener el ID de la operación generada");
                    }
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo realizar la transferencia", ex);
            throw new PersistenciaException("No se pudo realizar la transferencia", ex);
        }
    }
}
