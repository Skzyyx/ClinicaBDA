/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.PacienteBO;
import DTO.CitaViejoDTO;
import DTO.PacienteViejoDTO;
import Exception.NegocioException;
import configuracion.DependencyInjector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import sesion.SessionManager;

/**
 * Frame PrincipalPaciente. Representa la presentación para el menú principal
 * del paciente.
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class PrincipalPaciente extends javax.swing.JFrame {

    private PacienteBO pacienteBO = DependencyInjector.crearPacienteBO();
    private static PrincipalPaciente instance;
    private VerPerfilPaciente verPerfilPacienteFrame;
    private InicioDeSesion iniciarSesionFrame;
    private AgendarCita agendarCita;
    private CancelarCita cancelarCita;
    private VerHistorialPaciente historialPacienteFrame;
    private AgendarCitaEmergencia citaEmergenciaFrame;

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
     * Obtiene la instancia estática de la clase. Se utiliza para poder cambiar
     * entre ventanas con una única instancia.
     *
     * @return Intancia estática de la clase.
     */
    public static PrincipalPaciente getInstance() throws NegocioException {
        if (instance == null) {
            instance = new PrincipalPaciente();
        }
        return instance;
    }

    /**
     * Obtiene la ventana de ver perfil de paciente.
     *
     * @return Ventana de ver perfil de paciente.
     */
    public VerPerfilPaciente getVerPerfilPacienteFrame() {
        return verPerfilPacienteFrame;
    }

    /**
     * Asigna el valor de la ventana de ver perfil de paciente al valor de su
     * parámetro.
     *
     * @param verPerfilPacienteFrame Valor a asignar a la ventana de ver perfil
     * de paciente.
     */
    public void setVerPerfilPacienteFrame(VerPerfilPaciente verPerfilPacienteFrame) {
        this.verPerfilPacienteFrame = verPerfilPacienteFrame;
    }

    /**
     * Obtiene la ventana de agendar cita.
     *
     * @return Ventana de agendar cita.
     */
    public AgendarCita getAgendarCita() {
        return agendarCita;
    }

    /**
     * Asigna el valor de la ventana de agendar cita al valor de su parámetro.
     *
     * @param agendarCita Valor a asignar a la ventana de agendar cita.
     */
    public void setAgendarCita(AgendarCita agendarCita) {
        this.agendarCita = agendarCita;
    }

    /**
     * Obtiene la ventana de iniciar sesión.
     *
     * @return Ventana de iniciar sesión.
     */
    public InicioDeSesion getIniciarSesionFrame() {
        return iniciarSesionFrame;
    }

    /**
     * Asigna el valor de la ventana de iniciar sesion al valor de su parámetro.
     *
     * @param iniciarSesionFrame Valor a asignar a la ventana de iniciar sesion.
     */
    public void setIniciarSesionFrame(InicioDeSesion iniciarSesionFrame) {
        this.iniciarSesionFrame = iniciarSesionFrame;
    }

    /**
     * Obtiene la ventana de ver historial de paciente.
     *
     * @return Ventana de ver historial de paciente.
     */
    public VerHistorialPaciente getHistorialPacienteFrame() {
        return historialPacienteFrame;
    }

    /**
     * Asigna el valor de la ventana de ver historial de paciente al valor de su
     * parámetro.
     *
     * @param historialPacienteFrame Valor a asignar a la ventana de ver
     * historial de paciente.
     */
    public void setHistorialPacienteFrame(VerHistorialPaciente historialPacienteFrame) {
        this.historialPacienteFrame = historialPacienteFrame;
    }

    /**
     * Obtiene la ventana de cancelar cita.
     *
     * @return Ventana de cancelar cita.
     */
    public CancelarCita getCancelarCita() {
        return cancelarCita;
    }

    /**
     * Asigna el valor de la ventana de cancelar cita al valor de su parámetro.
     *
     * @param cancelarCita Valor a asignar a la ventana de cancelar cita.
     */
    public void setCancelarCita(CancelarCita cancelarCita) {
        this.cancelarCita = cancelarCita;
    }

    /**
     * Obtiene la ventana de cita de emergencia.
     *
     * @return Ventana de cita de emergencia.
     */
    public AgendarCitaEmergencia getCitaEmergenciaFrame() {
        return citaEmergenciaFrame;
    }

    /**
     * Asigna el valor de la ventana de cita de emergencia al valor de su
     * parámetro.
     *
     * @param citaEmergenciaFrame Valor a asignar a la ventana de cita de
     * emergencia.
     */
    public void setCitaEmergenciaFrame(AgendarCitaEmergencia citaEmergenciaFrame) {
        this.citaEmergenciaFrame = citaEmergenciaFrame;
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

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel2.setText("Menú principal");

        btnAgendarCita.setBackground(new java.awt.Color(0, 0, 0));
        btnAgendarCita.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        btnAgendarCita.setForeground(new java.awt.Color(255, 255, 255));
        btnAgendarCita.setText("Agendar cita");
        btnAgendarCita.setBorderPainted(false);
        btnAgendarCita.setPreferredSize(new java.awt.Dimension(150, 30));
        btnAgendarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarCitaActionPerformed(evt);
            }
        });

        btnCancelarCita.setBackground(new java.awt.Color(0, 0, 0));
        btnCancelarCita.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        btnCancelarCita.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarCita.setText("Cancelar cita");
        btnCancelarCita.setBorderPainted(false);
        btnCancelarCita.setPreferredSize(new java.awt.Dimension(150, 30));
        btnCancelarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCitaActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbNombre.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        lbNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 220, 30));

        iconPaciente.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        iconPaciente.setIcon(new ImageIcon(getClass().getResource("/iconPaciente.JPG")));
        jPanel2.add(iconPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 90, 90));

        btnVerPerfil.setBackground(new java.awt.Color(0, 0, 0));
        btnVerPerfil.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        btnVerPerfil.setForeground(new java.awt.Color(255, 255, 255));
        btnVerPerfil.setText("Ver perfil");
        btnVerPerfil.setBorderPainted(false);
        btnVerPerfil.setPreferredSize(new java.awt.Dimension(150, 30));
        btnVerPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPerfilActionPerformed(evt);
            }
        });
        jPanel2.add(btnVerPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 150, 58));
        btnVerPerfil.setContentAreaFilled(false);
        btnVerPerfil.setOpaque(false);
        btnVerPerfil.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Pinta un fondo redondeado
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 50, 50);
                super.paint(g2, c);
                g2.dispose();
            }
        });

        fondoCredencial.setIcon(new ImageIcon(getClass().getResource("/fondoCredencial.JPG")));
        jPanel2.add(fondoCredencial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnVerHistorial.setBackground(new java.awt.Color(0, 0, 0));
        btnVerHistorial.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        btnVerHistorial.setForeground(new java.awt.Color(255, 255, 255));
        btnVerHistorial.setText("Ver historial");
        btnVerHistorial.setBorderPainted(false);
        btnVerHistorial.setPreferredSize(new java.awt.Dimension(150, 30));
        btnVerHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerHistorialActionPerformed(evt);
            }
        });

        btnCerrarSesion.setBackground(new java.awt.Color(0, 0, 0));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.setBorderPainted(false);
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnVerHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                        .addComponent(btnCancelarCita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                    .addComponent(btnAgendarCita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(271, 271, 271))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel2)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnAgendarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarCita, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(103, Short.MAX_VALUE))
        );

        btnAgendarCita.setContentAreaFilled(false);
        btnAgendarCita.setOpaque(false);
        btnAgendarCita.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Pinta un fondo redondeado
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 50, 50);
                super.paint(g2, c);
                g2.dispose();
            }
        });
        btnCancelarCita.setContentAreaFilled(false);
        btnCancelarCita.setOpaque(false);
        btnCancelarCita.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Pinta un fondo redondeado
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 50, 50);
                super.paint(g2, c);
                g2.dispose();
            }
        });
        btnVerHistorial.setContentAreaFilled(false);
        btnVerHistorial.setOpaque(false);
        btnVerHistorial.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Pinta un fondo redondeado
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 50, 50);
                super.paint(g2, c);
                g2.dispose();
            }
        });
        btnCerrarSesion.setContentAreaFilled(false);
        btnCerrarSesion.setOpaque(false);
        btnCerrarSesion.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Pinta un fondo redondeado
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 50, 50);
                super.paint(g2, c);
                g2.dispose();
            }
        });

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
        cancelarCita();
    }//GEN-LAST:event_btnCancelarCitaActionPerformed

    private void btnAgendarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarCitaActionPerformed
        agendarCita();
    }//GEN-LAST:event_btnAgendarCitaActionPerformed

    private void btnVerPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPerfilActionPerformed
        verPerfil();
    }//GEN-LAST:event_btnVerPerfilActionPerformed

    private void btnVerHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerHistorialActionPerformed
        verHistorial();
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

    /**
     * Muestra el nombre en la credencial de paciente.
     *
     * @throws NegocioException Si hubo un error al intentar mostrar el nombre.
     */
    private void mostrarNombrePaciente() throws NegocioException {
        PacienteViejoDTO paciente = pacienteBO.obtenerPacientePorEmail(SessionManager.getInstance().getUser());

        if (paciente != null) {
            lbNombre.setText(paciente.getNombre() + " " + paciente.getApellidoPaterno());
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrió un error interno. Se ha cerrado la sesión.", "Error", JOptionPane.ERROR_MESSAGE);
            cerrarSesion();
        }
    }

    /**
     * Envía a la pestaña de agendar cita
     */
    private void agendarCita() {
        try {
            AgendarCita agendarCitaF = AgendarCita.getInstance();
            agendarCitaF.setPrincipalPaciente(this);
            agendarCitaF.setVisible(true);
            this.setVisible(false);
        } catch (NegocioException ex) {
            Logger.getLogger(PrincipalPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Envía a la pestaña de calcelar cita
     */
    private void cancelarCita() {
        try {
            // Obtiene las citas activas del paciente
            List<CitaViejoDTO> citas = pacienteBO.obtenerCitasActivasPaciente(SessionManager.getInstance().getUser());

            // Si tiene citas activas
            if (citas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No cuentas con citas para cancelar.", "Error", JOptionPane.ERROR_MESSAGE);
                // Ir a ventana de editar datos
            } else {
                CancelarCita cancelarCitaF = CancelarCita.getInstance();
                cancelarCitaF.setPrincipalPaciente(this);
                cancelarCitaF.setVisible(true);
                this.setVisible(false);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(PrincipalPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Envía a la pestaña de ver historial de consultas
     */
    private void verHistorial() {
        try {
            // Verificar si tiene o no consultas registradas para mostrar
            boolean noTieneConsultas = pacienteBO.tieneConsultasRegistradas(SessionManager.getInstance().getUser());

            // Si tiene consultas
            if (noTieneConsultas) {
                JOptionPane.showMessageDialog(this, "No cuentas con consultas en tu historial.");
            } else {
                VerHistorialPaciente historialPaciente = VerHistorialPaciente.getInstance();
                historialPaciente.setPrincipalPacienteFrame(this);
                historialPaciente.setVisible(true);
                historialPaciente.cargarHistorial();
                this.setVisible(false);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(VerHistorialPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Envía a la pestaña perfil de paciente
     *
     * @throws NegocioException Si hubo un error al cambiar de pestaña.
     */
    private void verPerfil() {
        try {
            VerPerfilPaciente verPerfil = VerPerfilPaciente.getInstance();
            verPerfil.setPrincipalPacienteFrame(this);
            verPerfil.setVisible(true);
            this.setVisible(false);
        } catch (NegocioException ex) {
            Logger.getLogger(VerHistorialPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cierra la sesión del usuario y la aplicación.
     */
    private void cerrarSesion() {
        SessionManager.getInstance().cerrarSesion();
        System.exit(0);
    }
}
