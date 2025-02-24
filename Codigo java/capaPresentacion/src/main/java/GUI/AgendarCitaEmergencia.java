/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.CitaBO;
import BO.MedicoBO;
import DTO.CitaNuevoDTO;
import DTO.CitaViejoDTO;
import DTO.HorarioViejoDTO;
import DTO.MedicoViejoDTO;
import DTO.PacienteNuevoDTO;
import Exception.NegocioException;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import configuracion.DependencyInjector;
import excepciones.PersistenciaException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sesion.SessionManager;

/**
 *
 * @author skyro
 */
public class AgendarCitaEmergencia extends javax.swing.JFrame {
    
    private static AgendarCitaEmergencia instance;

    private MedicoBO medicoBO = DependencyInjector.crearMedicoBO();
    private CitaBO citaBO = DependencyInjector.crearCitaBO();
    
    private PrincipalPaciente principalPaciente;
    private AgendarCita agendarCitaFrame;
    private DateTimeFormatter formatoFecha;
    
    /**
     * Creates new form RegistrarCita
     */
    public AgendarCitaEmergencia() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Paciente - Cita de emergencia");
        formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }

    public static AgendarCitaEmergencia getInstance() throws NegocioException {
        if (instance == null) {
            instance = new AgendarCitaEmergencia();
        }
        return instance;
    }

    public PrincipalPaciente getPrincipalPaciente() {
        return principalPaciente;
    }

    public void setPrincipalPaciente(PrincipalPaciente principalPaciente) {
        this.principalPaciente = principalPaciente;
    }

    public AgendarCita getAgendarCitaFrame() {
        return agendarCitaFrame;
    }

    public void setAgendarCitaFrame(AgendarCita agendarCitaFrame) {
        this.agendarCitaFrame = agendarCitaFrame;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbNombre8 = new javax.swing.JLabel();
        lbNombre9 = new javax.swing.JLabel();
        lbNombre10 = new javax.swing.JLabel();
        lbNombre11 = new javax.swing.JLabel();
        lbFechaHora = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        lbCedula = new javax.swing.JLabel();
        lbFolio = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(211, 222, 231));

        jPanel2.setBackground(new java.awt.Color(247, 242, 250));
        jPanel2.setForeground(new java.awt.Color(51, 0, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(java.awt.SystemColor.controlLtHighlight);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Tu cita solo es válida durante los próximos 10 minutos desde su generación, por lo que debes de presentarte antes de que transcurra ese tiempo. De otra forma, perderás la cita.\n\nDebes presentar el folio de cita para ser atentido.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextArea1.setEnabled(false);
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, 180));

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Aviso");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 70, 20));

        jButton2.setText("Aceptar");
        jButton2.setBackground(new java.awt.Color(44, 45, 45));
        jButton2.setBorderPainted(false);
        jButton2.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Agendar cita emergencia");
        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(247, 242, 250));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbNombre8.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        lbNombre8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre8.setText("Folio");
        jPanel3.add(lbNombre8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 40, 20));

        lbNombre9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lbNombre9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNombre9.setText("Nombre del médico");
        jPanel3.add(lbNombre9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 110, 20));

        lbNombre10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lbNombre10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNombre10.setText("Cédula profesional");
        jPanel3.add(lbNombre10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 110, 20));

        lbNombre11.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lbNombre11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNombre11.setText("Fecha y hora asignada");
        jPanel3.add(lbNombre11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 120, 20));

        lbFechaHora.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        lbFechaHora.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbFechaHora.setVerifyInputWhenFocusTarget(false);
        jPanel3.add(lbFechaHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 180, 30));

        lbNombre.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        lbNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNombre.setVerifyInputWhenFocusTarget(false);
        jPanel3.add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 200, 30));

        lbCedula.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        lbCedula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCedula.setVerifyInputWhenFocusTarget(false);
        jPanel3.add(lbCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 200, 30));

        lbFolio.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        lbFolio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbFolio.setVerifyInputWhenFocusTarget(false);
        jPanel3.add(lbFolio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 100, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 204, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Cita agendada con éxito");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 290, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(jLabel1)
                .addContainerGap(222, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(361, 361, 361))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(102, 102, 102)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(382, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(108, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(130, 130, 130)))
        );

        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(false);
        jButton2.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        volver();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(AgendarCitaEmergencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendarCitaEmergencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendarCitaEmergencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendarCitaEmergencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendarCitaEmergencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbCedula;
    private javax.swing.JLabel lbFechaHora;
    private javax.swing.JLabel lbFolio;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbNombre10;
    private javax.swing.JLabel lbNombre11;
    private javax.swing.JLabel lbNombre8;
    private javax.swing.JLabel lbNombre9;
    // End of variables declaration//GEN-END:variables


    public void cargarDatos(String folio) {
        try {
            CitaViejoDTO cita = citaBO.obtenerCitaEmergencia(folio);
            
            if (cita != null) {
                lbFechaHora.setText(cita.getFechaHoraInicio().format(formatoFecha));
                lbNombre.setText(cita.getMedico().getNombre() + " " + cita.getMedico().getApellidoPaterno() + " " + cita.getMedico().getApellidoMaterno());
                lbCedula.setText(cita.getMedico().getCedula());
                lbFolio.setText(cita.getFolio());
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos de cita de emergencia: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void volver() {
         try {
            PrincipalPaciente principalPacienteF = PrincipalPaciente.getInstance();
            principalPacienteF.setCitaEmergenciaFrame(this);
            principalPacienteF.setVisible(true);
            this.setVisible(false);
        } catch (NegocioException ex) {
            Logger.getLogger(AgendarCitaEmergencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
