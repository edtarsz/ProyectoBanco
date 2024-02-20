/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.bdavanzadas.GUI;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.itson.bdavanzadas.proyecto.dtos.RetiroSinCuentaDTO;
import org.itson.bdavanzadas.proyecto.excepciones.PersistenciaException;
import org.itson.bdavanzadas.proyectodominio.Cliente;
import org.itson.bdavanzadas.proyectodominio.Cuenta;

/**
 * Clase SolicitarRetiroFrame que representa una ventana para solicitar retiros de fondos en la interfaz de usuario. Permite al usuario seleccionar una cuenta activa y realizar un retiro de fondos.
 *
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Angel Huerta Amparán | 00000245345
 */
public class SolicitarRetiroFrame extends javax.swing.JFrame {

    // Variable privada para almacenar información sobre el cliente asociado a la solicitud de retiro
    private Cliente cliente;

    /**
     * Constructor predeterminado de la clase SolicitarRetiroFrame. Inicializa la interfaz gráfica y sus componentes.
     */
    public SolicitarRetiroFrame() {
        initComponents();
    }

    /**
     * Constructor de la clase SolicitarRetiroFrame que recibe un objeto Cliente. Inicializa la interfaz gráfica, establece el cliente asociado y consulta y muestra las cuentas activas del cliente.
     *
     * @param cliente Objeto Cliente asociado a la solicitud de retiro.
     */
    public SolicitarRetiroFrame(Cliente cliente) {
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
            cuentaCmbBox.setModel(model);
        } catch (PersistenciaException ex) {
            Logger.getLogger(TransferenciaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para realizar la solicitud de retiro. Obtiene los datos de la interfaz gráfica, valida la entrada del usuario y realiza la solicitud de retiro.
     *
     * @return Objeto RetiroSinCuentaDTO representando la información de la solicitud de retiro.
     */
    public RetiroSinCuentaDTO realizarRetiro() {
        Cuenta cuenta = new Cuenta();
        String cuentaString = (String) cuentaCmbBox.getSelectedItem();
        List<Cuenta> listaCuentas;

        // Consultar cuentas asociadas al cliente y obtener la cuenta seleccionada
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

        try {
            // Validar la entrada del usuario y realizar la solicitud de retiro
            if (txtMonto.getText() == null || txtMonto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Coloca un monto válido", "Notificación", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int monto = Integer.parseInt(txtMonto.getText());
                LocalDateTime fechaHoraActual = LocalDateTime.now();

                // Crear un objeto RetiroSinCuentaDTO con los datos de la solicitud de retiro
                RetiroSinCuentaDTO retiro = new RetiroSinCuentaDTO();
                retiro.setIdCuenta(cuenta.getNumCuenta());
                retiro.setFolio();
                retiro.setContraseñaEcriptada();

                // Desencriptar la contraseña
                String contraDesencriptada = retiro.getContraseñaRetiro();
                char[] charArray = contraDesencriptada.toCharArray();

                for (int i = 0; i < charArray.length; i++) {
                    charArray[i] -= 5;
                }

                contraDesencriptada = new String(charArray);

                retiro.setMonto(monto);
                retiro.setFechaHora(fechaHoraActual);

                // Realizar la solicitud de retiro y mostrar información al usuario
                Banco.retiroDao.solicitarRetiro(retiro, Banco.cuentaDao);
                JOptionPane.showMessageDialog(this, "El folio es: \n" + retiro.getFolio() + "\nContraseña: \n" + contraDesencriptada, "Notificación", JOptionPane.INFORMATION_MESSAGE);
                dispose();// Cerrar la ventana después de la solicitud de retiro

                return retiro;
            }
        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this, "No fue posible realizar la transferencia", "Error de almacenamiento", JOptionPane.ERROR_MESSAGE);
        }
        return null;
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
        jLabel7 = new javax.swing.JLabel();
        cuentaCmbBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
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
        jLabel4.setText("Retiro sin cuenta");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(42, 98, 143));
        jLabel7.setText("Cuenta");

        cuentaCmbBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        cuentaCmbBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cuentaCmbBox.setKeySelectionManager(null);
        cuentaCmbBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuentaCmbBoxActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(42, 98, 143));
        jLabel10.setText("Monto");

        txtMonto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
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
        jLabel11.setText("____________________________________________________________________________________________");

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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(119, 119, 119))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(119, 119, 119))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(190, 190, 190)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cuentaCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(184, 184, 184))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(confirmarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuentaCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(confirmarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 738, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel1.getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cuentaCmbBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuentaCmbBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cuentaCmbBoxActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    /**
     * Método invocado al hacer clic en el botón de confirmación. Realiza un retiro sin cuenta asociada, obteniendo la cuenta seleccionada, el monto y la fecha y hora actual. Luego, genera un objeto RetiroSinCuentaDTO con un folio y una contraseña encriptada. Muestra el folio y la contraseña desencriptada al cliente.
     */
    private void confirmarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarBtnActionPerformed
        realizarRetiro();
    }//GEN-LAST:event_confirmarBtnActionPerformed

    /**
     * Evento de clic en la etiqueta (jLabel) para volver al menú de cuenta. Cierra la ventana actual y abre el menú de cuenta asociado al cliente.
     *
     * @param evt Evento de clic del mouse.
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
     * Evento de clic en la etiqueta (jLabel) para volver al menú de cuenta. Cierra la ventana actual y abre el menú de cuenta asociado al cliente.
     *
     * @param evt Evento de clic del mouse.
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
    private javax.swing.JComboBox<String> cuentaCmbBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables

}
