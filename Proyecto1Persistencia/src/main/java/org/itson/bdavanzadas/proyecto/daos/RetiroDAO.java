/*
 * Este archivo contiene la implementación del DAO (Data Access Object) para gestionar operaciones de retiros sin cuenta.
 * Se utilizan consultas SQL para interactuar con la base de datos y realizar operaciones como consultar retiros, solicitar retiros y procesar retiros.
 */
package org.itson.bdavanzadas.proyecto.daos;

import java.sql.*;
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
 * Clase RetiroDAO que implementa la interfaz IRetiroDAO para gestionar operaciones relacionadas con retiros sin cuenta en la base de datos. Esta clase utiliza una conexión a la base de datos proporcionada por un objeto de tipo IConexion.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class RetiroDAO implements IRetiroDAO {

    // Atributos de la clase
    final IConexion conexionBD;
    static final Logger logger = Logger.getLogger(Conexion.class.getName());

    /**
     * Constructor de la clase RetiroDAO que recibe una conexión a la base de datos.
     *
     * @param conexion Objeto de tipo IConexion que proporciona la conexión a la base de datos.
     */
    public RetiroDAO(IConexion conexion) {
        this.conexionBD = conexion;
    }

    /**
     * Método para consultar un retiro sin cuenta basado en el folio proporcionado.
     *
     * @param retiroSinCuenta Objeto RetiroSinCuenta con el folio a consultar.
     * @return RetiroSinCuenta consultado o null si no se encuentra.
     * @throws PersistenciaException Si ocurre un error al acceder a la base de datos.
     */
    @Override
    public RetiroSinCuenta consultarRetiro(RetiroSinCuenta retiroSinCuenta) throws PersistenciaException {
        RetiroSinCuenta retiro = null;
        String sentenciaSQL = """
                              SELECT r.* , o.fechaHora, o.monto
                              FROM retirosincuentas r
                              INNER JOIN operaciones o on r.idOperacion = o.idOperacion
                              WHERE folio = ?;
                              """;

        try (Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL)) {

            comando.setString(1, retiroSinCuenta.getFolio());

            try (ResultSet resultados = comando.executeQuery()) {
                while (resultados.next()) {
                    // Obtener datos del resultado
                    int idOperacion = resultados.getInt("idOperacion");
                    Timestamp timeStamp = resultados.getTimestamp("fechaHora");
                    LocalDateTime fechaHora = timeStamp.toLocalDateTime();
                    String estado = resultados.getString("estado");
                    float monto = resultados.getFloat("monto");
                    String folio = resultados.getString("folio");
                    String contraseñaRetiro = resultados.getString("contraseñaRetiro");
                    String idCuenta = resultados.getString("idCuenta");

                    // Desencriptación de la contraseña
                    char[] contraseñaArray = contraseñaRetiro.toCharArray();
                    for (int i = 0; i < contraseñaArray.length; i++) {
                        contraseñaArray[i] -= 5;
                    }

                    // Crea objeto RetiroSinCuenta
                    retiro = new RetiroSinCuenta(idOperacion, fechaHora, monto, folio, contraseñaRetiro, idCuenta, estado);
                }
            }
            logger.log(Level.INFO, "Se consultó 1 retiro");
            return retiro;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo acceder al retiro", ex);
            throw new PersistenciaException("No se pudo acceder al retiro", ex);
        }
    }

    /**
     * Método para solicitar un retiro sin cuenta.
     *
     * @param retiro Objeto RetiroSinCuentaDTO con los datos del retiro a solicitar.
     * @param cuentaDAO Objeto de tipo ICuentaDAO para obtener información de la cuenta asociada al retiro.
     * @return RetiroSinCuenta solicitado.
     * @throws PersistenciaException Si ocurre un error al solicitar el retiro en la base de datos.
     */
    @Override
    public RetiroSinCuenta solicitarRetiro(RetiroSinCuentaDTO retiro, ICuentaDAO cuentaDAO) throws PersistenciaException {
        String sentenciaSQL = "{ CALL SolicitarRetiro(?, ?, ?, ?, ?, ?, ?) }";
        try (
                Connection conexion = this.conexionBD.obtenerConexion(); CallableStatement comando = conexion.prepareCall(sentenciaSQL);) {
            // Establece los parámetros de entrada
            comando.setTimestamp(1, Timestamp.valueOf(retiro.getFechaHora()));
            comando.setFloat(2, cuentaDAO.obtenerCuenta(retiro.getIdCuenta()).getSaldo());
            comando.setString(3, retiro.getIdCuenta());
            comando.setFloat(4, retiro.getMonto());
            comando.setString(5, retiro.getFolio());
            comando.setString(6, retiro.getContraseñaRetiro());

            Timestamp fechaHora = Timestamp.valueOf(retiro.getFechaHora());
            LocalDateTime retiroDateTime = fechaHora.toLocalDateTime();
            // Registra el parámetro de salida
            comando.registerOutParameter(7, Types.INTEGER);

            // Ejecuta el procedimiento almacenado
            comando.execute();

            // Obtiene el ID de operación del parámetro de salida
            int idOperacion = comando.getInt(7);

            // Crear y retornar el objeto RetiroSinCuenta con los datos necesarios
            RetiroSinCuenta retiroSin = new RetiroSinCuenta(idOperacion, retiroDateTime, retiro.getMonto(), retiro.getFolio(), retiro.getContraseñaRetiro(), retiro.getIdCuenta(), retiro.getEstado());
            return retiroSin;
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "No se pudo solicitar el retiro", ex);
            throw new PersistenciaException("No se pudo solicitar el retiro", ex);
        }
    }

    /**
     * Método para procesar un retiro sin cuenta, actualizando su estado y realizando operaciones en la base de datos.
     *
     * @param retiro Objeto RetiroSinCuenta a procesar.
     * @return true si el retiro fue procesado con éxito, false si no se pudo procesar.
     * @throws PersistenciaException Si ocurre un error al procesar el retiro en la base de datos.
     */
    @Override
    public boolean procesarRetiro(RetiroSinCuenta retiro) throws PersistenciaException {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(retiro.getFechaHora(), now);
        long minutos = duration.toMinutes();

        System.out.println("Minutos pasados:" + minutos);
        // Verifica si han pasado más de 10 minutos desde la solicitud del retiro
        if (minutos < 10) {
            String sentenciaSQLNoCobrado = """
        UPDATE retirosincuentas SET estado = "No Cobrado" WHERE idOperacion = ?;
                                       """;
            try (
                    Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQLNoCobrado, Statement.RETURN_GENERATED_KEYS);) {
                comando.setInt(1, retiro.getIdOperacion());
                int numeroRegistrosInsertados = comando.executeUpdate();
                logger.log(Level.INFO, "Se modificaron {?} estados de operación", numeroRegistrosInsertados);
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "No se pudo cambiar el estado del retiro", ex);
                throw new PersistenciaException("No se pudo cambiar el estado del retiro", ex);
            }
            return false;
        } else {
            // Si no han pasado más de 10 minutos, realizar el retiro
            String sentenciaSQLCobrado = """
            CALL RealizarRetiro (?, ?, ?);
                                       """;
            try (
                    Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQLCobrado, Statement.RETURN_GENERATED_KEYS);) {
                comando.setFloat(1, retiro.getMonto());
                comando.setString(2, retiro.getIdCuenta());
                comando.setInt(3, retiro.getIdOperacion());
                int numeroRegistrosInsertados = comando.executeUpdate();
                logger.log(Level.INFO, "Se hicieron {?} retiros", numeroRegistrosInsertados);
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "No se pudo realizar el retiro", ex);
                throw new PersistenciaException("No se pudo realizar el retiro", ex);
            }
            return true;
        }
    }
}
