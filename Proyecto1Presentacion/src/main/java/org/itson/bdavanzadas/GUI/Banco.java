/*
 * Este archivo representa la clase principal de la aplicación del banco.
 * Proporciona la configuración inicial, establece la conexión a la base de datos,
 * y crea instancias de las clases necesarias para las diferentes funcionalidades del banco.
 */
package org.itson.bdavanzadas.GUI;

import java.util.logging.Logger;
import org.itson.bdavanzadas.proyecto.conexion.Conexion;
import org.itson.bdavanzadas.proyecto.conexion.IConexion;
import org.itson.bdavanzadas.proyecto.daos.ClienteDAO;
import org.itson.bdavanzadas.proyecto.daos.CuentaDAO;
import org.itson.bdavanzadas.proyecto.daos.IClienteDAO;
import org.itson.bdavanzadas.proyecto.daos.ICuentaDAO;
import org.itson.bdavanzadas.proyecto.daos.IRetiroDAO;
import org.itson.bdavanzadas.proyecto.daos.ITransferenciaDAO;
import org.itson.bdavanzadas.proyecto.daos.RetiroDAO;
import org.itson.bdavanzadas.proyecto.daos.TransferenciaDAO;

/**
 * Clase principal que inicia la aplicación del banco.
 */
public class Banco {

    // Logger para la clase
    static final Logger logger = Logger.getLogger(Banco.class.getName());

    // Interfaces para la conexión y operaciones en la base de datos
    public static IConexion conexion;
    public static IClienteDAO clienteDao;
    public static ICuentaDAO cuentaDao;
    public static IRetiroDAO retiroDao;
    public static ITransferenciaDAO transferenciaDao;

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     * @throws Exception Si hay un error durante la ejecución.
     */
    public static void main(String[] args) throws Exception {
        // Configuración de la conexión a la base de datos
        String cadenaConexion = "jdbc:mysql://localhost/bancoProyecto";
        String usuario = "root";
        String contrasenia = "18509Tal";

        // Crea una instancia de la clase de conexión
        conexion = new Conexion(cadenaConexion, usuario, contrasenia);

        // Crea instancias de las interfaces DAO para interactuar con la base de datos
        clienteDao = new ClienteDAO(conexion);
        cuentaDao = new CuentaDAO(conexion);
        retiroDao = new RetiroDAO(conexion);
        transferenciaDao = new TransferenciaDAO(conexion);

        // Crea instancias de las clases de las interfaces gráficas
        IndiceFrame indiceFrame = new IndiceFrame();
        RegistrarUsuarioFrame registrarUsuario = new RegistrarUsuarioFrame();
        IniciarSesionFrame iniciarSesion = new IniciarSesionFrame();
        MenuCuentaFrame menuCuenta = new MenuCuentaFrame();
        TransferenciaFrame transferencia = new TransferenciaFrame();
        SolicitarRetiroFrame retiro = new SolicitarRetiroFrame();

        // Muestra el índice al iniciar la aplicación
        indiceFrame.setVisible(true);
    }
}
