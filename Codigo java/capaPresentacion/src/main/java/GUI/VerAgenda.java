/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.CitaBO;
import BO.ConsultaBO;
import BO.MedicoBO;
import BO.PacienteBO;
import DTO.CitaViejoDTO;
import DTO.ConsultaViejoDTO;
import DTO.MedicoViejoDTO;
import Exception.NegocioException;
import configuracion.DependencyInjector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
public class VerAgenda extends javax.swing.JFrame {

    private static VerAgenda instance;

    private MedicoBO medicoBO = DependencyInjector.crearMedicoBO();
    private CitaBO citaBO = DependencyInjector.crearCitaBO();
    private PacienteBO pacienteBO = DependencyInjector.crearPacienteBO();
    private ConsultaBO consultaBO = DependencyInjector.crearConsultaBO();

    private PrincipalMedico principalMedico;
    private DescripcionConsulta descripcionConsulta;

    /**
     * Creates new form RegistrarCita
     */
    public VerAgenda() {
        initComponents();
        setLocationRelativeTo(null);
        cargarListeners();
        cargarCitas();
    }

    public static VerAgenda getInstance() {
        if (instance == null) {
            instance = new VerAgenda();
        }
        return instance;
    }

    public DescripcionConsulta getDescripcionConsulta() {
        return descripcionConsulta;
    }

    public void setDescripcionConsulta(DescripcionConsulta descripcionConsulta) {
        this.descripcionConsulta = descripcionConsulta;
    }

    public PrincipalMedico getPrincipalMedico() {
        return principalMedico;
    }

    public void setPrincipalMedico(PrincipalMedico principalMedico) {
        this.principalMedico = principalMedico;
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
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(211, 222, 231));

        jPanel2.setBackground(new java.awt.Color(211, 222, 231));

        jTable1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idCita", "EstadoConsulta", "Fecha", "Hora", "Nombre Paciente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(30);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
            jTable1.getColumnModel().getColumn(1).setMinWidth(30);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(30);
        }

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel2.setText("Seleccione una cita:");

        jButton1.setBackground(new java.awt.Color(44, 45, 45));
        jButton1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Volver");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(44, 45, 45));
        jButton2.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Iniciar cita");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel1.setText("Mi agenda");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(336, 336, 336))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(false);
        jButton1.setBorderPainted(false);
        jButton1.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
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
        jButton2.setContentAreaFilled(false);
        jButton2.setEnabled(false);
        jButton2.setBorderPainted(false);
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            PrincipalMedico principalMedico = PrincipalMedico.getInstance();
            principalMedico.setVerAgenda(this);
            principalMedico.setVisible(true);
            this.setVisible(false);
        } catch (NegocioException ex) {
            Logger.getLogger(VerAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jTable1.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar una cita.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        
        try {    
            String idCita = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            
            boolean citaIniciada;
            ConsultaViejoDTO consulta = consultaBO.obtenerConsultaPorIdCita(idCita);

            DescripcionConsulta descripcionConsulta = DescripcionConsulta.getInstance();
            
            descripcionConsulta.setConsulta(consulta);
            descripcionConsulta.llenarCampos();
            descripcionConsulta.setVerAgenda(this);
            descripcionConsulta.setVisible(true);
            this.setVisible(false);
        } catch (NegocioException ex) {
            Logger.getLogger(VerAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
            java.util.logging.Logger.getLogger(VerAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new VerAgenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    public void cargarCitas() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        try {
            MedicoViejoDTO medico = new MedicoViejoDTO();
            medico.setIdMedico("0");
            medico.setCedula(SessionManager.getInstance().getUser());
            List<CitaViejoDTO> citas = medicoBO.obtenerCitasPorMedico(medico);

            for (CitaViejoDTO cita : citas) {

                modelo.addRow(new Object[]{
                    cita.getIdCita(),
                    consultaBO.obtenerEstadoConsulta(cita.getIdCita()),
                    cita.getFechaHoraInicio().toLocalDate(),
                    cita.getFechaHoraInicio().toLocalTime(),
                    cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellidoPaterno()
                });
            }
        } catch (NegocioException ex) {
            Logger.getLogger(VerAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarListeners() {
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {

                int filaSeleccionada = jTable1.getSelectedRow();

                if (filaSeleccionada == -1) {
                    return;
                }

                // Convertir el valor de la tabla a String
                String valorFecha = jTable1.getValueAt(filaSeleccionada, 2).toString();
                String valorHora = jTable1.getValueAt(filaSeleccionada, 3).toString();

                // Combinar ambas cadenas en una única cadena en formato ISO
                String fechaHoraCombinada = valorFecha + "T" + valorHora;

                // Convertir a LocalDateTime usando el formateador ISO
                LocalDateTime fechaInicio = LocalDateTime.parse(fechaHoraCombinada, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                LocalDateTime ahora = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
                LocalDateTime fechaInicioMas30 = fechaInicio.plusMinutes(30);

                String idCita = jTable1.getValueAt(filaSeleccionada, 0).toString();
                String estadoConsulta = jTable1.getValueAt(filaSeleccionada, 1).toString();

                if (ahora.isAfter(fechaInicio) && ahora.isBefore(fechaInicioMas30) && estadoConsulta.equalsIgnoreCase("PENDIENTE")) {
                    // La fechaInicio está entre ahora y ahora + 30 minutos.
                    jButton2.setEnabled(true);
                    jButton2.setText("Iniciar");
                } else if (fechaInicio.isAfter(ahora) && fechaInicio.isBefore(fechaInicioMas30) && estadoConsulta.equalsIgnoreCase("ASISTIO")) {
                    jButton2.setEnabled(true);
                    jButton2.setText("Ver");
                }

            }
        });
    }
}
