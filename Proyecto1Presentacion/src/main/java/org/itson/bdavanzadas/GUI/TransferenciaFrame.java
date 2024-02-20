/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.bdavanzadas.GUI;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.itson.bdavanzadas.proyecto.dtos.TransferenciaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cliente;
import org.itson.bdavanzadas.proyectodominio.Cuenta;

/**
 * Clase que representa la ventana de transferencia entre cuentas. Permite realizar transferencias entre la cuenta origen del cliente y otra cuenta destino.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class TransferenciaFrame extends javax.swing.JFrame {

    // Variable privada para controlar el estado de despliegue de la ventana
    private boolean estadoDesplegado = false;
    private Cliente cliente;

    // Variable privada para almacenar información sobre el cliente asociado a la transferencia
    public TransferenciaFrame() {
        initComponents();
    }

    /**
     * Constructor de la clase TransferenciaFrame que recibe un objeto Cliente. Inicializa la interfaz gráfica, establece el cliente asociado y consulta y muestra las cuentas activas del cliente.
     *
     * @param cliente Objeto Cliente asociado a la transferencia.
     */
    public TransferenciaFrame(Cliente cliente) {
        initComponents();
        this.cliente = cliente;

        // Consultar y mostrar las cuentas activas del cliente en el ComboBox
        try {
            List<Cuenta> listaCuentas = Banco.clienteDao.consultarCuentas(this.cliente);

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

            for (Cuenta cuenta : listaCuentas) {
                if ("Activa".equals(cuenta.getEstado())) {
                    model.addElement(String.valueOf(cuenta.getNumCuenta()));
                }
            }

            cuentaOrigenCmbBox.setModel(model);
        } catch (PersistenciaException ex) {
            Logger.getLogger(TransferenciaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Establecer la fecha y hora actual en un campo de texto
        Date fechaActual = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String fechaString = formato.format(fechaActual);
        fechaHoratxt.setText(fechaString);
    }

    /**
     * Método para realizar la transferencia de fondos. Obtiene los datos de la interfaz gráfica, valida la entrada del usuario y realiza la transferencia.
     *
     * @throws PersistenciaException Si ocurre un error al realizar la transferencia en la base de datos.
     */
    public void realizarTransferencia() throws PersistenciaException {
        String cuentaString = (String) cuentaOrigenCmbBox.getSelectedItem();
        Cuenta cuenta = new Cuenta();
        List<Cuenta> listaCuentas;

        // Consultar cuentas asociadas al cliente y obtener la cuenta de origen seleccionada
        try {
            listaCuentas = Banco.clienteDao.consultarCuentas(this.cliente);
            for (Cuenta account : listaCuentas) {
                if (String.valueOf(account.getNumCuenta()).equals(cuentaString)) {
                    cuenta = account;
                }
            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(TransferenciaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Validar la entrada del usuario y realizar la transferencia
        if (String.valueOf(txtCuentaDestino.getText()).isEmpty() || String.valueOf(txtCuentaDestino.getText()) == null || String.valueOf(txtImporte.getText()).isEmpty() || String.valueOf(txtImporte.getText()) == null) {
            JOptionPane.showMessageDialog(this, "Rellene todos los campos", "Notificación", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int idCuentaDestino = Integer.parseInt(txtCuentaDestino.getText());
            int monto = Integer.parseInt(txtImporte.getText());
            LocalDateTime fechaActual = LocalDateTime.now();

            // Crear un objeto TransferenciaDTO con los datos de la transferencia
            TransferenciaDTO transferencia = new TransferenciaDTO();
            transferencia.setIdCuenta(Integer.parseInt(cuenta.getNumCuenta()));
            transferencia.setIdCuentaDestino(idCuentaDestino);
            transferencia.setFechaHora(fechaActual);
            transferencia.setMonto(monto);

            // Realizar la transferencia y mostrar la ventana de confirmación
            Banco.transferenciaDao.realizarTransferencia(transferencia, Banco.cuentaDao.obtenerCuenta(cuenta.getNumCuenta()));
            dispose();
            ConfirmarTransferenciaFrame confirmar = new ConfirmarTransferenciaFrame(transferencia, cliente);
            confirmar.setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fechaHoratxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cuentaOrigenCmbBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCuentaDestino = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        confirmarBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/vbankchico.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Transferencia");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(42, 98, 143));
        jLabel6.setText("Fecha y hora: ");

        fechaHoratxt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        fechaHoratxt.setForeground(new java.awt.Color(42, 98, 143));
        fechaHoratxt.setText("(fecha y hora)");
        fechaHoratxt.setBorder(null);
        fechaHoratxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaHoratxtActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(42, 98, 143));
        jLabel7.setText("Cuenta origen");

        cuentaOrigenCmbBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        cuentaOrigenCmbBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cuentaOrigenCmbBox.setKeySelectionManager(null);
        cuentaOrigenCmbBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuentaOrigenCmbBoxActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/transferencia.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(42, 98, 143));
        jLabel9.setText("Cuenta destino");

        txtCuentaDestino.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        txtCuentaDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCuentaDestinoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(42, 98, 143));
        jLabel10.setText("Importe");

        txtImporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        txtImporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtImporteMouseEntered(evt);
            }
        });
        txtImporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImporteActionPerformed(evt);
            }
        });

        confirmarBtn.setBackground(new java.awt.Color(42, 98, 143));
        confirmarBtn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        confirmarBtn.setForeground(new java.awt.Color(255, 255, 255));
        confirmarBtn.setText("Confirmar");
        confirmarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarBtnActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(102, 102, 102));
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText("__________________________________________________________________________________________________________________________________________");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flecha.JPG"))); // NOI18N
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel4)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaHoratxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(56, 56, 56))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(cuentaOrigenCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(61, 61, 61)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(txtCuentaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaHoratxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCuentaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cuentaOrigenCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(confirmarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fechaHoratxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaHoratxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaHoratxtActionPerformed

    private void cuentaOrigenCmbBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuentaOrigenCmbBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuentaOrigenCmbBoxActionPerformed

    private void txtCuentaDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCuentaDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuentaDestinoActionPerformed

    private void txtImporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteActionPerformed

    /**
     * Método que se ejecuta al hacer clic en el botón de confirmación (confirmarBtn) en la interfaz gráfica. Invoca el método realizarTransferencia() para llevar a cabo la transferencia de fondos. Captura y maneja cualquier excepción de PersistenciaException que pueda ocurrir durante la transferencia.
     *
     * @param evt Objeto ActionEvent que representa el evento de acción (clic) en el botón.
     */
    private void confirmarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarBtnActionPerformed
        try {
            realizarTransferencia();
        } catch (PersistenciaException ex) {
            // Capturar y registrar cualquier excepción de PersistenciaException
            Logger.getLogger(TransferenciaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_confirmarBtnActionPerformed

    private void txtImporteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtImporteMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteMouseEntered

    /**
     * Evento de clic en el JLabel para regresar al menú de cuenta. Cierra la ventana actual y muestra el menú de cuenta.
     */
    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        MenuCuentaFrame menuCuenta = null;
        try {
            menuCuenta = new MenuCuentaFrame(this.cliente);
        } catch (PersistenciaException ex) {
            Logger.getLogger(TransferenciaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        menuCuenta.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    /**
     * Evento de clic en el JLabel para regresar al menú de cuenta. Cierra la ventana actual y muestra el menú de cuenta.
     */
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        dispose();
        MenuCuentaFrame menuCuentaFrm;
        try {
            menuCuentaFrm = new MenuCuentaFrame(cliente);
            menuCuentaFrm.setVisible(true);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ConfirmarTransferenciaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmarBtn;
    private javax.swing.JComboBox<String> cuentaOrigenCmbBox;
    private javax.swing.JTextField fechaHoratxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCuentaDestino;
    private javax.swing.JTextField txtImporte;
    // End of variables declaration//GEN-END:variables
}
