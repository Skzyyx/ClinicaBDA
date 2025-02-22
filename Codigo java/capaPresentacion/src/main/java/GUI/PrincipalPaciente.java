/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.PacienteBO;
import DTO.PacienteViejoDTO;
import Exception.NegocioException;
import configuracion.DependencyInjector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import sesion.SessionManager;

/**
 *
 * @author j_ama
 */
public class PrincipalPaciente extends javax.swing.JFrame {
    private PacienteBO pacienteBO = DependencyInjector.crearPacienteBO();
    
    /**
     * Creates new form InicioDeSesion
     */
    public PrincipalPaciente() throws NegocioException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Paciente - Menú principal");
        mostrarNombrePaciente();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnAgendarCita = new javax.swing.JButton();
        btnCancelarCita = new javax.swing.JButton();
        btnHistorial = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lbNombre = new javax.swing.JLabel();
        iconPaciente = new javax.swing.JLabel();
        btnVerPerfil = new javax.swing.JButton();
        fondoCredencial = new javax.swing.JLabel();
        btnVerHistorial = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(210, 222, 230));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 36)); // NOI18N
        jLabel2.setText("Menú principal");

        btnAgendarCita.setBackground(new java.awt.Color(0, 0, 0));
        btnAgendarCita.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        btnAgendarCita.setForeground(new java.awt.Color(255, 255, 255));
        btnAgendarCita.setText("Agendar cita");
        btnAgendarCita.setPreferredSize(new java.awt.Dimension(150, 30));
        btnAgendarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarCitaActionPerformed(evt);
            }
        });

        btnCancelarCita.setBackground(new java.awt.Color(0, 0, 0));
        btnCancelarCita.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        btnCancelarCita.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarCita.setText("Cancelar cita");
        btnCancelarCita.setPreferredSize(new java.awt.Dimension(150, 30));
        btnCancelarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCitaActionPerformed(evt);
            }
        });

        btnHistorial.setBackground(new java.awt.Color(0, 0, 0));
        btnHistorial.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        btnHistorial.setForeground(new java.awt.Color(255, 255, 255));
        btnHistorial.setText("Consultar historial");
        btnHistorial.setPreferredSize(new java.awt.Dimension(150, 30));
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbNombre.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        lbNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 220, 30));

        iconPaciente.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        iconPaciente.setIcon(new ImageIcon(getClass().getResource("/iconPaciente.JPG")));
        jPanel2.add(iconPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 90, 90));

        btnVerPerfil.setBackground(new java.awt.Color(0, 0, 0));
        btnVerPerfil.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        btnVerPerfil.setForeground(new java.awt.Color(255, 255, 255));
        btnVerPerfil.setText("Ver perfil");
        btnVerPerfil.setPreferredSize(new java.awt.Dimension(150, 30));
        btnVerPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPerfilActionPerformed(evt);
            }
        });
        jPanel2.add(btnVerPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 150, 58));

        fondoCredencial.setIcon(new ImageIcon(getClass().getResource("/fondoCredencial.JPG")));
        jPanel2.add(fondoCredencial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnVerHistorial.setBackground(new java.awt.Color(0, 0, 0));
        btnVerHistorial.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        btnVerHistorial.setForeground(new java.awt.Color(255, 255, 255));
        btnVerHistorial.setText("Ver historial");
        btnVerHistorial.setPreferredSize(new java.awt.Dimension(150, 30));
        btnVerHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerHistorialActionPerformed(evt);
            }
        });

        btnCerrarSesion.setBackground(new java.awt.Color(0, 0, 0));
        btnCerrarSesion.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.setPreferredSize(new java.awt.Dimension(150, 30));
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(253, 253, 253))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnVerHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                        .addComponent(btnCancelarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                    .addComponent(btnAgendarCita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel2)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(517, 517, 517)
                        .addComponent(btnHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnAgendarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarCitaActionPerformed

    private void btnAgendarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarCitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgendarCitaActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnVerPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerPerfilActionPerformed

    private void btnVerHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerHistorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerHistorialActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        cerrarSesion();
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PrincipalPaciente().setVisible(true);
                } catch (NegocioException ex) {
                    Logger.getLogger(PrincipalPaciente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgendarCita;
    private javax.swing.JButton btnCancelarCita;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnVerHistorial;
    private javax.swing.JButton btnVerPerfil;
    private javax.swing.JLabel fondoCredencial;
    private javax.swing.JLabel iconPaciente;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbNombre;
    // End of variables declaration//GEN-END:variables

    private void mostrarNombrePaciente() throws NegocioException {
        PacienteViejoDTO paciente = pacienteBO.obtenerPacientePorEmail(SessionManager.getInstance().getUser());
        
        if (paciente != null) {
            lbNombre.setText(paciente.getNombre());
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrió un error interno. Se ha cerrado la sesión.", "Error", JOptionPane.ERROR_MESSAGE);
            cerrarSesion();
        }
    }
    
    private void cerrarSesion() {
        SessionManager.getInstance().cerrarSesion();
        System.exit(0);
    }
}