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
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cliente;

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
        String sentenciaSQL = "SELECT nombre, contraseña FROM clientes;";
        List<Cliente> listaClientes = new LinkedList<>();

        try (Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL); ResultSet resultados = comando.executeQuery()) {

            while (resultados.next()) {
                String nombre = resultados.getString("nombre");
                String contraseña = resultados.getString("contraseña");

                Cliente cliente = new Cliente(nombre, contraseña);
                listaClientes.add(cliente);
            }

            logger.log(Level.INFO, "Se consultaron {0} socios", listaClientes.size());
            return listaClientes;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudieron consultar los socios", ex);
            throw new PersistenciaException("No se pudieron consultar los socios", ex);
        }
    }
}
