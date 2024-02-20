/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.bdavanzadas.GUI;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import org.itson.bdavanzadas.proyecto.dtos.CuentaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyecto.excepciones.ValidacionDTOException;
import org.itson.bdavanzadas.proyectodominio.Cliente;
import org.itson.bdavanzadas.proyectodominio.Cuenta;

/**
 * Clase que representa la interfaz gráfica del menú de cuenta. Permite realizar operaciones relacionadas con las cuentas de un cliente.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class MenuCuentaFrame extends javax.swing.JFrame {

    private Cuenta cuentaMostrada;
    private Cliente cliente;
    private boolean estadoDesplegado = false;

    /**
     * Constructor por defecto de la clase MenuCuentaFrame. Inicializa los componentes de la interfaz gráfica.
     */
    public MenuCuentaFrame() {
        initComponents();
    }

    /**
     * Constructor que recibe un cliente como parámetro. Inicializa los componentes de la interfaz gráfica y muestra las cuentas asociadas al cliente.
     *
     * @param cliente Cliente para el cual se mostrarán las cuentas.
     * @throws PersistenciaException Si hay un error al consultar las cuentas del cliente.
     */
    public MenuCuentaFrame(Cliente cliente) throws PersistenciaException {
        initComponents();

        this.cliente = cliente;
        nombreUsuario.setText(cliente.getNombre());

        if (this.cliente != null && !Banco.clienteDao.consultarCuentas(this.cliente).isEmpty()) {
            mostrarCuentas();
        }
    }

    /**
     * Método para agregar una nueva cuenta al cliente. Crea una nueva cuenta, la valida y la agrega al cliente.
     */
    private void agregarCuenta() {
        // Se crea una nueva instancia de CuentaDTO
        CuentaDTO cuentaNueva = new CuentaDTO();

        // Se asigna un número de cuenta automáticamente
        cuentaNueva.setNumCuenta();

        // Se establece el ID del cliente asociado a la cuenta
        cuentaNueva.setIdCliente(cliente.getIdCliente());

        // Se establece un saldo inicial para la cuenta
        cuentaNueva.setSaldo((float) 10000.00);

        // Se establece el estado inicial de la cuenta como "Activa"
        cuentaNueva.setEstado("Activa");

        // Se obtiene la fecha actual y se establece como la fecha de apertura de la cuenta
        Date fechaActual = new Date();
        cuentaNueva.setFechaApertura(fechaActual);

        // Se imprime en consola información sobre la nueva cuenta
        System.out.println("Cuenta nueva: " + cuentaNueva);

        try {
            // Se valida la cuenta antes de agregarla al cliente
            if (cuentaNueva.esValido()) {
                // Se agrega la cuenta al cliente a través del DAO de cliente
                cuentaMostrada = Banco.clienteDao.agregarCuenta(cuentaNueva);

                // Se muestra un mensaje de notificación al usuario
                JOptionPane.showMessageDialog(this, "Se registra la cuenta", "Notificación", JOptionPane.INFORMATION_MESSAGE);

                // Se actualiza la visualización de las cuentas del cliente
                mostrarCuentas();
            }
        } catch (ValidacionDTOException ex) {
            // Si hay errores de validación, se muestra un mensaje de error
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        } catch (PersistenciaException ex) {
            // Si hay errores al agregar la cuenta al cliente, se muestra un mensaje de error
            JOptionPane.showMessageDialog(this, "No fue posible agregar al cliente", "Error de almacenamiento", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método para cancelar una cuenta seleccionada por el cliente. Solicita confirmación antes de cancelar la cuenta.
     */
    public void cancelarCuenta() {
        // Se obtiene la cuenta seleccionada del JComboBox
        String cuentaSelect = (String) cmbCuentas.getSelectedItem();

        // Se verifica que la cuenta seleccionada no sea nula y sea una instancia de String
        if (cuentaSelect != null && cuentaSelect instanceof String) {
            // Se divide la cadena para obtener el número de cuenta
            String[] partes = cuentaSelect.split(" ");
            String numeroCuenta = partes[0].substring(0);

            // Se muestra un cuadro de diálogo de confirmación al usuario
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de cancelar la cuenta?", "Confirmación", JOptionPane.YES_NO_OPTION);

            // Si el usuario confirma la cancelación
            if (opcion == JOptionPane.YES_OPTION) {
                try {
                    // Se utiliza el DAO de cliente para cambiar el estado de la cuenta a "Cancelada"
                    Banco.clienteDao.cambiarEstadoCuenta(numeroCuenta, "Cancelada");

                    // Se muestra un mensaje de notificación al usuario
                    JOptionPane.showMessageDialog(this, "Se canceló la cuenta", "Notificación", JOptionPane.INFORMATION_MESSAGE);

                    // Se actualiza la visualización de las cuentas del cliente
                    mostrarCuentas();
                } catch (PersistenciaException ex) {
                    // Si hay errores al cancelar la cuenta, se muestra un mensaje de error
                    JOptionPane.showMessageDialog(this, "No fue posible cancelar la cuenta", "Error de almacenamiento", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // Si no hay cuenta seleccionada, se muestra un mensaje de advertencia
            JOptionPane.showMessageDialog(this, "No hay cuenta seleccionada para cancelar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Método para mostrar las cuentas asociadas al cliente en el ComboBox.
     *
     * @throws PersistenciaException Si hay un error al consultar las cuentas del cliente.
     */
    private void mostrarCuentas() {
        try {
            // Se obtiene la lista de cuentas asociadas al cliente
            List<Cuenta> listaCuentas = Banco.clienteDao.consultarCuentas(this.cliente);

            // Se crea un nuevo modelo para el ComboBox
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

            // Se recorre la lista de cuentas
            for (Cuenta cuenta : listaCuentas) {
                // Solo se agregan las cuentas activas al modelo
                if ("Activa".equals(cuenta.getEstado())) {
                    // Se añade cada cuenta al modelo del ComboBox con un formato específico
                    model.addElement(String.valueOf(cuenta.getNumCuenta()) + "      Saldo:" + cuenta.getSaldo());
                }
            }
            // Se establece el nuevo modelo en el ComboBox
            cmbCuentas.setModel(model);
        } catch (PersistenciaException ex) {
            // Si hay errores al consultar las cuentas, se registra el error
            Logger.getLogger(TransferenciaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnHistorial = new javax.swing.JButton();
        btnUsuario = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nombreUsuario = new javax.swing.JTextField();
        agregarCuentabtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        transferenciaBtn = new javax.swing.JButton();
        retiroBtn = new javax.swing.JButton();
        txtEditarPerfil = new javax.swing.JButton();
        txtCerrarSesion = new javax.swing.JButton();
        cmbCuentas = new javax.swing.JComboBox<>();
        agregarCuentabtn1 = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/vbankchico.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        btnHistorial.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHistorial.setForeground(new java.awt.Color(42, 98, 143));
        btnHistorial.setText("Historial");
        btnHistorial.setBorderPainted(false);
        btnHistorial.setBackground(null);
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        btnUsuario.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnUsuario.setForeground(new java.awt.Color(42, 98, 143));
        btnUsuario.setText("Usuario");
        btnUsuario.setBorderPainted(false);
        btnUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuario.setBackground(null);
        btnUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUsuarioMouseClicked(evt);
            }
        });
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuarioIcono.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/historialIcono.png"))); // NOI18N

        jLabel5.setBackground(new java.awt.Color(102, 102, 102));
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("_____________________________________________________________________________________________________________________________________________");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(42, 98, 143));
        jLabel6.setText("Cuentas");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel7.setText("Bienvenido nuevamente,");

        nombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        nombreUsuario.setText("(nombre)");
        nombreUsuario.setBorder(null);
        nombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreUsuarioActionPerformed(evt);
            }
        });

        agregarCuentabtn.setBackground(new java.awt.Color(42, 98, 143));
        agregarCuentabtn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        agregarCuentabtn.setForeground(new java.awt.Color(255, 255, 255));
        agregarCuentabtn.setText("Agregar");
        agregarCuentabtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregarCuentabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarCuentabtnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(42, 98, 143));
        jLabel8.setText("Operaciones");

        transferenciaBtn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        transferenciaBtn.setForeground(new java.awt.Color(42, 98, 143));
        transferenciaBtn.setText("Realizar una transferencia");
        transferenciaBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 98, 143), 4));
        transferenciaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transferenciaBtn.setBackground(null);
        transferenciaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferenciaBtnActionPerformed(evt);
            }
        });

        retiroBtn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        retiroBtn.setForeground(new java.awt.Color(42, 98, 143));
        retiroBtn.setText("Generar retiro sin tarjeta");
        retiroBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 98, 143), 4));
        retiroBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        retiroBtn.setBackground(null);
        retiroBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retiroBtnActionPerformed(evt);
            }
        });

        txtEditarPerfil.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtEditarPerfil.setForeground(new java.awt.Color(42, 98, 143));
        txtEditarPerfil.setText("   ");
        txtEditarPerfil.setBorder(null);
        txtEditarPerfil.setBorderPainted(false);
        txtEditarPerfil.setBackground(null);
        txtEditarPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtEditarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEditarPerfilActionPerformed(evt);
            }
        });

        txtCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCerrarSesion.setForeground(new java.awt.Color(42, 98, 143));
        txtCerrarSesion.setText("   ");
        txtCerrarSesion.setBorder(null);
        txtCerrarSesion.setBorderPainted(false);
        txtCerrarSesion.setBackground(null);
        txtCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCerrarSesionActionPerformed(evt);
            }
        });

        cmbCuentas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        cmbCuentas.setForeground(new java.awt.Color(42, 98, 143));
        cmbCuentas.setBackground(Color.WHITE);
        cmbCuentas.setBorder(new EmptyBorder(15, 3, 5, 3));
        cmbCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCuentasActionPerformed(evt);
            }
        });

        agregarCuentabtn1.setBackground(new java.awt.Color(121, 23, 43));
        agregarCuentabtn1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        agregarCuentabtn1.setForeground(new java.awt.Color(255, 255, 255));
        agregarCuentabtn1.setText("Cancelar cuenta");
        agregarCuentabtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregarCuentabtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarCuentabtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHistorial)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUsuario))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEditarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(transferenciaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(retiroBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cmbCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(agregarCuentabtn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(agregarCuentabtn1)))
                                .addGap(0, 16, Short.MAX_VALUE)))))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuario)
                    .addComponent(btnHistorial)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregarCuentabtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agregarCuentabtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(transferenciaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(retiroBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(54, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEditarPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 812, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 4, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreUsuarioActionPerformed

    /**
     * Método invocado cuando se hace clic en el botón de usuario.
     *
     * @param evt El evento de acción asociado.
     */
    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed
        // Verifica si el área de opciones de usuario está desplegada o no
        if (!estadoDesplegado) {
            // Si no está desplegado, muestra los textos "* Editar perfil" y "* Cerrar sesión"
            txtEditarPerfil.setText("* Editar perfil");
            txtCerrarSesion.setText("* Cerrar sesión");
        } else {
            // Si ya está desplegado, oculta los textos
            txtEditarPerfil.setText("");
            txtCerrarSesion.setText("");
        }

        // Cambia el estado de despliegue para la próxima vez
        estadoDesplegado = !estadoDesplegado;
    }//GEN-LAST:event_btnUsuarioActionPerformed

    /**
     * Abre la ventana de historial de operaciones y cierra la ventana actual.
     *
     * @param evt Evento de acción del botón.
     */
    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        HistorialOperacionesFrame historialFrame = new HistorialOperacionesFrame(cliente);
        historialFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHistorialActionPerformed

    /**
     * Método asociado al clic del botón para agregar una cuenta. Invoca el método agregarCuenta() para agregar una nueva cuenta al cliente.
     *
     * @param evt Evento de acción del botón.
     */
    private void agregarCuentabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarCuentabtnActionPerformed
        agregarCuenta();
    }//GEN-LAST:event_agregarCuentabtnActionPerformed

    /**
     * Abre la ventana de transferencia y cierra la ventana actual.
     *
     * @param evt Evento de acción del botón.
     */
    private void transferenciaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transferenciaBtnActionPerformed
        TransferenciaFrame transferencia = new TransferenciaFrame(cliente);
        transferencia.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_transferenciaBtnActionPerformed

    /**
     * Abre la ventana de solicitud de retiro y cierra la ventana actual.
     *
     * @param evt Evento de acción del botón.
     */
    private void retiroBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retiroBtnActionPerformed
        SolicitarRetiroFrame retiroFrm = new SolicitarRetiroFrame(cliente);
        retiroFrm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_retiroBtnActionPerformed

    /**
     * Abre la ventana de actualización de usuario y cierra la ventana actual.
     *
     * @param evt Evento de acción del área de texto.
     */
    private void txtEditarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEditarPerfilActionPerformed
        ActualizarUsuarioFrame editarPerfil = new ActualizarUsuarioFrame(cliente);
        editarPerfil.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_txtEditarPerfilActionPerformed

    private void btnUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUsuarioMouseClicked

    /**
     * Muestra un mensaje de notificación y abre la ventana de inicio de sesión, cerrando la ventana actual.
     *
     * @param evt Evento de acción del área de texto.
     */
    private void txtCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCerrarSesionActionPerformed
        JOptionPane.showMessageDialog(this, "Se ha cerrado sesión correctamente.", "Notificación", JOptionPane.INFORMATION_MESSAGE);
        IniciarSesionFrame iniciarSesion = new IniciarSesionFrame();
        iniciarSesion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_txtCerrarSesionActionPerformed

    private void cmbCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCuentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCuentasActionPerformed

    /**
     * Método asociado al clic del botón para cancelar una cuenta. Invoca el método cancelarCuenta() para cancelar la cuenta seleccionada.
     *
     * @param evt Evento de acción del botón.
     */
    private void agregarCuentabtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarCuentabtn1ActionPerformed
        cancelarCuenta();
    }//GEN-LAST:event_agregarCuentabtn1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarCuentabtn;
    private javax.swing.JButton agregarCuentabtn1;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JComboBox<String> cmbCuentas;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombreUsuario;
    private javax.swing.JButton retiroBtn;
    private javax.swing.JButton transferenciaBtn;
    private javax.swing.JButton txtCerrarSesion;
    private javax.swing.JButton txtEditarPerfil;
    // End of variables declaration//GEN-END:variables
}
