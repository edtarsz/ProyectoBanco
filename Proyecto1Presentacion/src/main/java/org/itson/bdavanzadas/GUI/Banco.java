/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.bdavanzadas.GUI;

import java.util.logging.Logger;
import org.itson.bdavanzadas.proyecto.conexion.Conexion;
import org.itson.bdavanzadas.proyecto.conexion.IConexion;
import org.itson.bdavanzadas.proyecto.daos.BancoDAO;
import org.itson.bdavanzadas.proyecto.daos.IBancoDAO;

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
        IBancoDAO bancoDAO = new BancoDAO(conexion);
        IndiceFrame indiceFrame = new IndiceFrame(bancoDAO);
        RegistrarUsuarioFrame registrarUsuario = new RegistrarUsuarioFrame(bancoDAO);
        IniciarSesionFrame iniciarSesion = new IniciarSesionFrame(bancoDAO);
        MenuCuentaFrame menuCuenta = new MenuCuentaFrame(bancoDAO);

        indiceFrame.setVisible(true);
    }
}
