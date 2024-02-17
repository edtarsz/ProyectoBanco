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

/**
 *
 * @author Ramosz
 */
public class Banco {

    static final Logger logger = Logger.getLogger(Banco.class.getName());

    public static void main(String[] args) throws Exception {
        String cadenaConexion = "jdbc:mysql://localhost/banco";
        String usuario = "root";
        String contrasenia = "18509Tal";

        IConexion conexion = new Conexion(cadenaConexion, usuario, contrasenia);

        IClienteDAO clienteDao = new ClienteDAO(conexion);
        ICuentaDAO cuentaDao = new CuentaDAO(conexion);

        IndiceFrame indiceFrame = new IndiceFrame(clienteDao);
        RegistrarUsuarioFrame registrarUsuario = new RegistrarUsuarioFrame(clienteDao);
        IniciarSesionFrame iniciarSesion = new IniciarSesionFrame(clienteDao);
        MenuCuentaFrame menuCuenta = new MenuCuentaFrame(clienteDao);
        TransferenciaFrame transferencia = new TransferenciaFrame(clienteDao);

        indiceFrame.setVisible(true);
    }
}
