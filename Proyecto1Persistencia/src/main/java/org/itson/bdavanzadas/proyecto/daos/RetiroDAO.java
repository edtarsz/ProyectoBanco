/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.proyecto.conexion.Conexion;
import org.itson.bdavanzadas.proyecto.conexion.IConexion;
import static org.itson.bdavanzadas.proyecto.daos.CuentaDAO.logger;
import org.itson.bdavanzadas.proyecto.dtos.RetiroSinCuentaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.RetiroSinCuenta;

/**
 *
 * @author JoseH
 */
public class RetiroDAO implements IRetiroDAO{
    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(Conexion.class.getName());
    long horaRetiro;

    public RetiroDAO(IConexion conexion) {
        this.conexionBD = conexion;
    }
    
    @Override
    public RetiroSinCuenta solicitarRetiro(RetiroSinCuentaDTO retiro, ICuentaDAO cuentaDAO) throws PersistenciaException {
        String sentenciaSQL = """
        CALL  SolicitarRetiro (?, ?, ?, ?, ?, ?)
                                       """;
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS);) {
            comando.setDate(1, new java.sql.Date(retiro.getFechaHora().getTime()));
            comando.setFloat(2, cuentaDAO.obtenerCuenta(retiro.getIdCuenta()).getSaldo());
            comando.setInt(3, retiro.getIdCuenta());
            comando.setFloat(4, retiro.getMonto());
            comando.setString(5, retiro.getFolio());
            comando.setString(6, retiro.getContraseñaRetiro());
            
            int numeroRegistrosInsertados = comando.executeUpdate();
            logger.log(Level.INFO, "Se hicieron {?} retiros", numeroRegistrosInsertados);

            RetiroSinCuenta retiroSin = new RetiroSinCuenta(retiro.getIdCuenta(), retiro.getFechaHora(), retiro.getMonto(),retiro.getFolio(), retiro.getContraseñaRetiro());
            horaRetiro = System.currentTimeMillis();
            return retiroSin;
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo solicitar el retiro", ex);
            throw new PersistenciaException("No se pudo solicitar el retiro", ex);
        }
    }
    
    @Override
     public void procesarRetiro(RetiroSinCuenta retiro) throws PersistenciaException{
         if (System.currentTimeMillis() > horaRetiro + 600000) {
             String sentenciaSQLNoCobrado = """
        UPDATE retirosincuentas SET estado = "No Cobrado" where idOperacion = ?;
                                       """;
             try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQLNoCobrado, Statement.RETURN_GENERATED_KEYS);) {
                 comando.setInt(1, retiro.getIdOperacion());
                 comando.executeUpdate();
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

             } catch (SQLException ex) {
                 logger.log(Level.SEVERE, "No se pudo realizar el retiro", ex);
                 throw new PersistenciaException("No se pudo realizar el retiro", ex);
             }
         }
     }

}
