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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.proyecto.conexion.Conexion;
import org.itson.bdavanzadas.proyecto.conexion.IConexion;
import static org.itson.bdavanzadas.proyecto.daos.ClienteDAO.logger;
import org.itson.bdavanzadas.proyecto.dtos.TransferenciaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Transferencia;

/**
 *
 * @author JoseH
 */
public class TransferenciaDAO implements ITransferenciaDAO{
    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(Conexion.class.getName());

    public TransferenciaDAO(IConexion conexion) {
        this.conexionBD = conexion;
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
                comandoOperacion.setTimestamp(1, Timestamp.valueOf(transferenciaNueva.getFechaHora()));
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
