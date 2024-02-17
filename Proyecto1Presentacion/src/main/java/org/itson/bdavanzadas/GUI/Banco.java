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
import org.itson.bdavanzadas.proyecto.daos.RetiroDAO;

/**
 *
 * @author Ramosz
 */
public class Banco {

    static final Logger logger = Logger.getLogger(Banco.class.getName());
    public static IClienteDAO clienteDao;
    public static ICuentaDAO cuentaDao;
    public static IRetiroDAO retiroDao;
    
    
    public static void main(String[] args) throws Exception {
        String cadenaConexion = "jdbc:mysql://localhost/banco";
        String usuario = "root";
        String contrasenia = "CheemSITO357";

        IConexion conexion = new Conexion(cadenaConexion, usuario, contrasenia);

        clienteDao = new ClienteDAO(conexion);
        ICuentaDAO cuentaDao = new CuentaDAO(conexion);
        IRetiroDAO retiroDao = new RetiroDAO(conexion);

        IndiceFrame indiceFrame = new IndiceFrame(clienteDao, cuentaDao);
        RegistrarUsuarioFrame registrarUsuario = new RegistrarUsuarioFrame(clienteDao, cuentaDao);
        IniciarSesionFrame iniciarSesion = new IniciarSesionFrame(clienteDao, cuentaDao);
        MenuCuentaFrame menuCuenta = new MenuCuentaFrame(clienteDao);
        TransferenciaFrame transferencia = new TransferenciaFrame(clienteDao);
        RetiroFrame retiro = new RetiroFrame(clienteDao, cuentaDao);

        indiceFrame.setVisible(true);
    }
}
