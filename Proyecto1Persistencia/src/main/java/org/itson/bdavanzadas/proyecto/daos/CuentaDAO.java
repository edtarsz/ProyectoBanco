/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.proyecto.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.proyecto.conexion.Conexion;
import org.itson.bdavanzadas.proyecto.conexion.IConexion;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cuenta;

/**
 * CuentaDAO es una clase que proporciona métodos para interactuar con la base de datos relacionados con la entidad Cuenta.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class CuentaDAO implements ICuentaDAO {

    /**
     * La conexión a la base de datos utilizada por la instancia de CuentaDAO.
     */
    final IConexion conexionBD;

    /**
     * El objeto Logger utilizado para realizar registros en la aplicación.
     */
    static final Logger logger = Logger.getLogger(Conexion.class.getName());

    /**
     * Constructor de la clase CuentaDAO.
     *
     * @param conexion Objeto que implementa la interfaz IConexion para establecer la conexión a la base de datos.
     */
    public CuentaDAO(IConexion conexion) {
        this.conexionBD = conexion;
    }

    /**
     * Obtiene la información de una cuenta mediante su número de cuenta.
     *
     * @param numCuenta Número de cuenta de la cuenta que se desea obtener.
     * @return Objeto Cuenta con la información de la cuenta obtenida.
     * @throws PersistenciaException Si ocurre un error al interactuar con la base de datos.
     */
    @Override
    public Cuenta obtenerCuenta(String numCuenta) throws PersistenciaException {
        String sentenciaSQL = "SELECT * FROM cuentas WHERE numCuenta = ?";
        Cuenta cuenta = null;

        try (
                Connection conexion = this.conexionBD.obtenerConexion(); PreparedStatement comando = conexion.prepareStatement(sentenciaSQL);) {
            comando.setString(1, numCuenta);

            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                cuenta = new Cuenta(
                        resultado.getString("numCuenta"),
                        resultado.getInt("idCliente"),
                        resultado.getFloat("saldo"),
                        resultado.getDate("fechaApertura"),
                        resultado.getString("estado")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CuentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al obtener información de la cuenta", ex);
        }

        return cuenta;
    }
}
