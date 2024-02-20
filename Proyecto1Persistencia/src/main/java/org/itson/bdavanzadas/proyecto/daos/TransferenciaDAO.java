/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.proyecto.conexion.Conexion;
import org.itson.bdavanzadas.proyecto.conexion.IConexion;
import org.itson.bdavanzadas.proyecto.dtos.TransferenciaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cuenta;
import org.itson.bdavanzadas.proyectodominio.Transferencia;

/**
 *
 * @author JoseH
 */
public class TransferenciaDAO implements ITransferenciaDAO {

    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(Conexion.class.getName());

    public TransferenciaDAO(IConexion conexion) {
        this.conexionBD = conexion;
    }

    @Override
    public Transferencia realizarTransferencia(TransferenciaDTO transferenciaNueva, Cuenta cuenta) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.obtenerConexion()) {
            float saldoFloat = cuenta.getSaldo();
            BigDecimal saldo = new BigDecimal(String.valueOf(saldoFloat));

            String llamadaSP = "{CALL RealizarTransferencia(?, ?, ?, ?, ?)}";
            try (CallableStatement callableStatement = conexion.prepareCall(llamadaSP)) {
                callableStatement.setTimestamp(1, Timestamp.valueOf(transferenciaNueva.getFechaHora()));
                callableStatement.setBigDecimal(2, saldo);
                callableStatement.setInt(3, transferenciaNueva.getIdCuenta());
                callableStatement.setInt(4, transferenciaNueva.getIdCuentaDestino());
                callableStatement.setBigDecimal(5, BigDecimal.valueOf(transferenciaNueva.getMonto()));
                callableStatement.execute();

                Transferencia transferencia = new Transferencia(
                        transferenciaNueva.getIdCuenta(),
                        transferenciaNueva.getIdCuentaDestino(),
                        0,
                        transferenciaNueva.getFechaHora(),
                        transferenciaNueva.getMonto()
                );

                return transferencia;
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo realizar la transferencia", ex);
            throw new PersistenciaException("No se pudo realizar la transferencia debido a: " + ex.getMessage(), ex);
        }
    }
}
