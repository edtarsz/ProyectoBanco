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
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.proyecto.conexion.Conexion;
import org.itson.bdavanzadas.proyecto.conexion.IConexion;
import org.itson.bdavanzadas.proyecto.dtos.RetiroSinCuentaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.RetiroSinCuenta;

/**
 *
 * @author JoseH
 */
public class RetiroDAO implements IRetiroDAO {

    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(Conexion.class.getName());

    public RetiroDAO(IConexion conexion) {
        this.conexionBD = conexion;
    }

    @Override
    public RetiroSinCuenta consultarRetiro(RetiroSinCuenta retiroSinCuenta) throws PersistenciaException {
        RetiroSinCuenta retiro = null;
        String sentenciaSQL = "SELECT * from retirosincuentas;";

        try (Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL)) {
            try (ResultSet resultados = comando.executeQuery()) {
                while (resultados.next()) {
                    String folio = resultados.getString("folio");
                    String contraseña = resultados.getString("contraseñaRetiro");
                    if (folio.equals(retiroSinCuenta.getFolio()) && contraseña.equals(retiroSinCuenta.getContraseñaRetiro())) {
                        retiro = retiroSinCuenta;
                    }
                }
                int numeroRegistrosInsertados = comando.executeUpdate();
                logger.log(Level.INFO, "Se hicieron {?} retiros", numeroRegistrosInsertados);
                return retiro;
            }

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo acceder al retiro", ex);
            throw new PersistenciaException("No se pudo acceder al retiro", ex);
        }
    }

    @Override
    public RetiroSinCuenta solicitarRetiro(RetiroSinCuentaDTO retiro, ICuentaDAO cuentaDAO) throws PersistenciaException {
        String sentenciaSQL = """
        CALL  SolicitarRetiro (?, ?, ?, ?, ?, ?)
                                       """;
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setTimestamp(1, Timestamp.valueOf(retiro.getFechaHora()));
            comando.setFloat(2, cuentaDAO.obtenerCuenta(retiro.getIdCuenta()).getSaldo());
            comando.setInt(3, retiro.getIdCuenta());
            comando.setFloat(4, retiro.getMonto());
            comando.setString(5, retiro.getFolio());
            comando.setString(6, retiro.getContraseñaRetiro());

            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se hicieron {?} retiros", numeroRegistrosInsertados);

            RetiroSinCuenta retiroSin = new RetiroSinCuenta(retiro.getIdCuenta(), retiro.getFechaHora(), retiro.getMonto(), retiro.getFolio(), retiro.getContraseñaRetiro());
            return retiroSin;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo solicitar el retiro", ex);
            throw new PersistenciaException("No se pudo solicitar el retiro", ex);
        }
    }

    @Override
    public void procesarRetiro(RetiroSinCuenta retiro) throws PersistenciaException {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(retiro.getFechaHora(), now);
        long difMinutos = duration.toMinutes();
        if (difMinutos > 10) {
            String sentenciaSQLNoCobrado = """
        UPDATE retirosincuentas SET estado = "No Cobrado" where idOperacion = ?;
                                       """;
            try (
                    Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQLNoCobrado, Statement.RETURN_GENERATED_KEYS);) {
                comando.setInt(1, retiro.getIdOperacion());
                comando.executeUpdate();

                int numeroRegistrosInsertados = comando.executeUpdate();
                logger.log(Level.INFO, "Se modificaron {?} estados de operación", numeroRegistrosInsertados);

            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "No se pudo cambiar el estado del retiro", ex);
                throw new PersistenciaException("No se pudo cambiar el estado del retiro", ex);
            }
        } else {
            String sentenciaSQLCobrado = """
        CALL  RealizarRetiro (?, ?, ?)
                                       """;
            try (
                    Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQLCobrado, Statement.RETURN_GENERATED_KEYS);) {
                comando.setFloat(1, retiro.getMonto());
                comando.setInt(2, retiro.getIdCuenta());
                comando.setInt(3, retiro.getIdOperacion());
                comando.executeUpdate();

                int numeroRegistrosInsertados = comando.executeUpdate();
                logger.log(Level.INFO, "Se hicieron {?} retiros", numeroRegistrosInsertados);

            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "No se pudo realizar el retiro", ex);
                throw new PersistenciaException("No se pudo realizar el retiro", ex);
            }
        }
    }

}
