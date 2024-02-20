/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
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
 *
 * @author Ramosz
 */
public class Banco {

    static final Logger logger = Logger.getLogger(Banco.class.getName());
    public static IConexion conexion;
    public static IClienteDAO clienteDao;
    public static ICuentaDAO cuentaDao;
    public static IRetiroDAO retiroDao;
    public static ITransferenciaDAO transferenciaDao;

    public static void main(String[] args) throws Exception {
        String cadenaConexion = "jdbc:mysql://localhost/bancoProyecto";
        String usuario = "root";
        String contrasenia = "CheemSITO357";

        conexion = new Conexion(cadenaConexion, usuario, contrasenia);

        clienteDao = new ClienteDAO(conexion);
        cuentaDao = new CuentaDAO(conexion);
        retiroDao = new RetiroDAO(conexion);
        transferenciaDao = new TransferenciaDAO(conexion);

        IndiceFrame indiceFrame = new IndiceFrame();
        RegistrarUsuarioFrame registrarUsuario = new RegistrarUsuarioFrame();
        IniciarSesionFrame iniciarSesion = new IniciarSesionFrame();
        MenuCuentaFrame menuCuenta = new MenuCuentaFrame();
        TransferenciaFrame transferencia = new TransferenciaFrame();
        SolicitarRetiroFrame retiro = new SolicitarRetiroFrame();

        indiceFrame.setVisible(true);
    }
}
