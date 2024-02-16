/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import java.sql.Connection;
import java.sql.Date;
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
public class BancoDAO implements IBancoDAO {

    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(Conexion.class.getName());

    public BancoDAO(IConexion conexion) {
        this.conexionBD = conexion;
    }

    @Override
    public Cliente agregar(ClienteDTO clienteNuevo) throws PersistenciaException {
        String sentenciaSQL = """
        INSERT INTO clientes(Nombre, ApellidoPaterno, ApellidoMaterno, Contraseña, FechaNacimiento, CodigoPostal, NumExterior, Calle, Colonia, Ciudad, Edad)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);""";
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, clienteNuevo.getNombre());
            comando.setString(2, clienteNuevo.getApellidoMaterno());
            comando.setString(3, clienteNuevo.getApellidoPaterno());
            comando.setString(4, clienteNuevo.getContraseña());
            comando.setString(5, clienteNuevo.getFechaNacimiento());
            comando.setString(6, clienteNuevo.getCodigoPostal());
            comando.setString(7, clienteNuevo.getNumExterior());
            comando.setString(8, clienteNuevo.getCalle());
            comando.setString(9, clienteNuevo.getColonia());
            comando.setString(10, clienteNuevo.getCiudad());
            comando.setInt(11, clienteNuevo.getEdad());
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} socios", numeroRegistrosInsertados);

            ResultSet idGenerado = comando.getGeneratedKeys();
            idGenerado.next();
            Cliente cliente = new Cliente(
                    idGenerado.getLong(1),
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
        String sentenciaSQL = "SELECT idCliente, nombre, contraseña FROM clientes;";
        List<Cliente> listaClientes = new LinkedList<>();

        try (Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL); ResultSet resultados = comando.executeQuery()) {

            while (resultados.next()) {
                Long idCliente = resultados.getLong("idCliente");
                String nombre = resultados.getString("nombre");
                String contraseña = resultados.getString("contraseña");

                Cliente cliente = new Cliente(idCliente, nombre, contraseña);
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
            comando.setString(4, cuentaNueva.getFechaApertura());

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
    
    public Operacion agregarOperacion(OperacionDTO operacionNueva) throws PersistenciaException{
        String sentenciaSQL = """
        INSERT INTO operaciones(fechaHora, monto)
        VALUES (?, ?);""";
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setDate(1, (Date) operacionNueva.getFechaHora());
            comando.setFloat(2, operacionNueva.getMonto());

            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se agregaron {0} operaciones", numeroRegistrosInsertados);

            ResultSet idGenerado = comando.getGeneratedKeys();
            idGenerado.next();
            Operacion operacion = new Operacion(idGenerado.getInt(1), operacionNueva.getFechaHora(), operacionNueva.getMonto());
            return operacion;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo guardar la operación", ex);
            throw new PersistenciaException("No se pudo guardar la operación", ex);
        }
    }
    
    @Override
     public Transferencia realizarTransferencia(TransferenciaDTO transferenciaNueva) throws PersistenciaException {
        String sentenciaSQL = """
        INSERT INTO transferencias(idOperacion, idCuenta, idCuentaDestino, idCliente)
        VALUES (?, ?, ?);""";
        try (
            Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setInt(1, transferenciaNueva.getIdOperacion());
            comando.setInt(2, transferenciaNueva.getIdCuenta());
            comando.setInt(3, transferenciaNueva.getIdCuentaDestino());
            comando.setInt(4, transferenciaNueva.getIdCliente());

            String sentenciaSQL1 = """
        SELECT * FROM cuentas c  
                                   INNER JOIN operaciones o on c.idCuenta = o.idCuenta
                                   INNER JOIN cuentas cd on o.idCuentaDestino = cd.idCuenta
                                   where idOperacion = ?""";
            
            try (PreparedStatement statement = conexion.prepareStatement(sentenciaSQL)) {
                statement.setInt(1, transferenciaNueva.getIdOperacion()); // Asegúrate de reemplazar tuIdOperacion con el valor apropiado

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    Cuenta cuentaOrigen = (Cuenta) resultSet.getObject("c.idCuenta");
                    cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() + transferenciaNueva.getMonto());
                    
                    Cuenta cuentaDestino = (Cuenta) resultSet.getObject("c.idCuenta");
                    cuentaDestino.setSaldo(cuentaDestino.getSaldo() - transferenciaNueva.getMonto());
                }
            } catch (SQLException e) {
                e.printStackTrace(); 
            }
            
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se realizó la transferencia", numeroRegistrosInsertados);

            Transferencia transferencia = new Transferencia(transferenciaNueva.getIdCuenta(), transferenciaNueva.getIdCuentaDestino(), transferenciaNueva.getIdCliente(), transferenciaNueva.getIdOperacion(), transferenciaNueva.getFechaHora(), transferenciaNueva.getMonto());
            return transferencia;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo realizar la transferencia", ex);
            throw new PersistenciaException("No se pudo realizar la transferencia", ex);
        }
    }
     
     
    
    }

