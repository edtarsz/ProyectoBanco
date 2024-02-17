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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.proyecto.conexion.Conexion;
import org.itson.bdavanzadas.proyecto.conexion.IConexion;
import static org.itson.bdavanzadas.proyecto.daos.ClienteDAO.logger;
import org.itson.bdavanzadas.proyecto.dtos.RetiroSinCuentaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cuenta;
import org.itson.bdavanzadas.proyectodominio.RetiroSinCuenta;

/**
 *
 * @author Ramosz
 */
public class CuentaDAO implements ICuentaDAO {

    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(Conexion.class.getName());

    public CuentaDAO(IConexion conexion) {
        this.conexionBD = conexion;
    }

    public Cuenta obtenerCuenta(int numCuenta) throws PersistenciaException {
        String sentenciaSQL = "SELECT * FROM cuentas WHERE numCuenta = ?";
        Cuenta cuenta = null;

        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setInt(1, numCuenta);

            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                cuenta = new Cuenta(
                        resultado.getInt("numCuenta"),
                        resultado.getLong("idCliente"),
                        resultado.getFloat("saldo"),
                        resultado.getDate("fechaApertura")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al obtener información de la cuenta", ex);
        }

        return cuenta;
    }
    
    @Override
    public RetiroSinCuenta realizarRetiro(RetiroSinCuentaDTO retiro) throws PersistenciaException {
        String sentenciaSQL = """
        CALL  RealizarRetiro (?, ?, ?, ?, ?, ?)
                                       """;
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setDate(1, new java.sql.Date(retiro.getFechaHora().getTime()));
            comando.setFloat(2, obtenerCuenta(retiro.getIdCuenta()).getSaldo());
            comando.setInt(3, retiro.getIdCuenta());
            comando.setFloat(4, retiro.getMonto());
            comando.setString(5, retiro.getFolio());
            comando.setString(6, retiro.getContraseñaRetiro());
            
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se hicieron {0} retiros", numeroRegistrosInsertados);

            RetiroSinCuenta retiroSin = new RetiroSinCuenta(retiro.getIdCuenta(), retiro.getFechaHora(), retiro.getMonto(),retiro.getFolio(), retiro.getContraseñaRetiro());
            return retiroSin;
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo realizar el retiro", ex);
            throw new PersistenciaException("No se pudo realizar el retiro", ex);
        }
    }
}
