/*
 * Este archivo contiene la implementación del DAO (Data Access Object) para realizar transferencias entre cuentas.
 * Se utiliza una conexión a la base de datos proporcionada por un objeto de tipo IConexion.
 */
package org.itson.bdavanzadas.proyecto.daos;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
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
 * Clase TransferenciaDAO que implementa la interfaz ITransferenciaDAO para realizar transferencias entre cuentas en la base de datos. Esta clase utiliza una conexión a la base de datos proporcionada por un objeto de tipo IConexion.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class TransferenciaDAO implements ITransferenciaDAO {

    // Atributos de la clase
    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(Conexion.class.getName());

    /**
     * Constructor de la clase TransferenciaDAO que recibe una conexión a la base de datos.
     *
     * @param conexion Objeto de tipo IConexion que proporciona la conexión a la base de datos.
     */
    public TransferenciaDAO(IConexion conexion) {
        this.conexionBD = conexion;
    }

    /**
     * Método para realizar una transferencia entre cuentas.
     *
     * @param transferenciaNueva Objeto TransferenciaDTO con los datos de la transferencia.
     * @param cuenta Cuenta desde la cual se realiza la transferencia.
     * @return Objeto Transferencia con los detalles de la transferencia realizada.
     * @throws PersistenciaException Si ocurre un error al realizar la transferencia en la base de datos.
     */
    @Override
    public Transferencia realizarTransferencia(TransferenciaDTO transferenciaNueva, Cuenta cuenta) throws PersistenciaException {
        try (Connection conexion = this.conexionBD.obtenerConexion()) {
            // Convierte el saldo de la cuenta a BigDecimal
            float saldoFloat = cuenta.getSaldo();
            BigDecimal saldo = new BigDecimal(String.valueOf(saldoFloat));

            // Llama al procedimiento almacenado para realizar la transferencia
            String llamadaSP = "{CALL RealizarTransferencia(?, ?, ?, ?, ?)}";
            try (CallableStatement callableStatement = conexion.prepareCall(llamadaSP)) {
                callableStatement.setTimestamp(1, Timestamp.valueOf(transferenciaNueva.getFechaHora()));
                callableStatement.setBigDecimal(2, saldo);
                callableStatement.setInt(3, transferenciaNueva.getIdCuenta());
                callableStatement.setInt(4, transferenciaNueva.getIdCuentaDestino());
                callableStatement.setBigDecimal(5, BigDecimal.valueOf(transferenciaNueva.getMonto()));
                callableStatement.execute();

                // Crea y retorna el objeto Transferencia con los datos necesarios
                Transferencia transferencia = new Transferencia(
                        transferenciaNueva.getIdCuenta(),
                        transferenciaNueva.getIdCuentaDestino(),
                        0, // No se tiene información sobre el ID de la transferencia en el código proporcionado
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
