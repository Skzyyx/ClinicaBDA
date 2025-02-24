/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.MedicoBO;
import DTO.MedicoViejoDTO;
import Exception.NegocioException;
import configuracion.DependencyInjector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import sesion.SessionManager;

/**
 *
 * @author j_ama
 */
public class VerPerfilMedico extends javax.swing.JFrame {
    private MedicoBO medicoBO = DependencyInjector.crearMedicoBO();
    private static VerPerfilMedico instance;
    private PrincipalMedico principalMedicoFrame;
    
    /**
     * Creates new form InicioDeSesion
     */
    public VerPerfilMedico() throws NegocioException {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Médico - Ver perfil");
        mostrarPerfil();
    }
    
    public static VerPerfilMedico getInstance() throws NegocioException {
         if (instance == null) {
            instance = new VerPerfilMedico();
        }
        return instance;
    }

    public PrincipalMedico getPrincipalMedicoFrame() {
        return principalMedicoFrame;
    }

    public void setPrincipalMedicoFrame(PrincipalMedico principalMedicoFrame) {
        this.principalMedicoFrame = principalMedicoFrame;
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
        btnVolver = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbPerfil = new javax.swing.JLabel();
        lbNombre2 = new javax.swing.JLabel();
        lbNombre3 = new javax.swing.JLabel();
        lbNombre4 = new javax.swing.JLabel();
        lbEspecialidad = new javax.swing.JLabel();
        lbNombre8 = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        lbCedula = new javax.swing.JLabel();
        lbEstado = new javax.swing.JLabel();
        lbFondo = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(210, 222, 230));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel2.setText("Mi perfil");

        btnVolver.setBackground(new java.awt.Color(0, 0, 0));
        btnVolver.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.setBorderPainted(false);
        btnVolver.setPreferredSize(new java.awt.Dimension(150, 30));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel8.setText("Ficha de datos");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 130, 20));

        jLabel1.setIcon(new ImageIcon(getClass().getResource("/iconPaciente.JPG")));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 100, 90));

        lbPerfil.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lbPerfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lbPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 100, 20));

        lbNombre2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lbNombre2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNombre2.setText("Especialidad");
        lbNombre2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel2.add(lbNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 70, 20));

        lbNombre3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lbNombre3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNombre3.setText("Estado");
        lbNombre3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel2.add(lbNombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 50, -1));

        lbNombre4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lbNombre4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNombre4.setText("Cédula profesional");
        lbNombre4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel2.add(lbNombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 100, 20));

        lbEspecialidad.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        lbEspecialidad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbEspecialidad.setVerifyInputWhenFocusTarget(false);
        jPanel2.add(lbEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 180, 30));

        lbNombre8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lbNombre8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNombre8.setText("Nombre completo");
        jPanel2.add(lbNombre8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 100, 20));

        lbNombre.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        lbNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNombre.setVerifyInputWhenFocusTarget(false);
        jPanel2.add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 200, 30));

        lbCedula.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        lbCedula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCedula.setVerifyInputWhenFocusTarget(false);
        jPanel2.add(lbCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 180, 30));

        lbEstado.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        lbEstado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbEstado.setVerifyInputWhenFocusTarget(false);
        jPanel2.add(lbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 70, 30));

        lbFondo.setIcon(new ImageIcon(getClass().getResource("/fondoFicha.JPG")));
        lbFondo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbFondo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel2.add(lbFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 320));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(337, 337, 337))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel2)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        btnVolver.setContentAreaFilled(false);
        btnVolver.setOpaque(false);
        btnVolver.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        try {
            volver();
        } catch (NegocioException ex) {
            Logger.getLogger(VerPerfilMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVolverActionPerformed

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
            java.util.logging.Logger.getLogger(VerPerfilMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerPerfilMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerPerfilMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerPerfilMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VerPerfilMedico().setVisible(true);
                } catch (NegocioException ex) {
                    Logger.getLogger(VerPerfilMedico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbCedula;
    private javax.swing.JLabel lbEspecialidad;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbFondo;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbNombre2;
    private javax.swing.JLabel lbNombre3;
    private javax.swing.JLabel lbNombre4;
    private javax.swing.JLabel lbNombre8;
    private javax.swing.JLabel lbPerfil;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Obtiene los datos de perfil del medico y los muestra.
     */
    public void mostrarPerfil() {
        try {
            // Intenta obtener el perfil
            MedicoViejoDTO medico = medicoBO.obtenerMedicoPorCedula(SessionManager.getInstance().getUser());
            
            // Si no se encontró perfil
            if (medico == null) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al mostrar perfil.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // Si se encontró perfil
            else {
                // Mostrar los datos 
                lbPerfil.setText(medico.getNombre());
                lbNombre.setText(medico.getNombre() + " " + medico.getApellidoPaterno() + " " + medico.getApellidoMaterno());
                lbEspecialidad.setText(medico.getEspecialidad());
                lbCedula.setText(medico.getCedula());
                lbEstado.setText(medico.getEstado());
            }
        } catch (NegocioException ex) {
            Logger.getLogger(VerPerfilPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Envía a pestaña de menú principal de Medico
     * @throws NegocioException Si hubo un error al cambiar de pestaña.
     */
    private void volver() throws NegocioException {
        PrincipalMedico principalMedico = PrincipalMedico.getInstance();
        principalMedico.setPerfilMedicoFrame(this);
        principalMedico.setVisible(true);
        this.setVisible(false);
    }
}