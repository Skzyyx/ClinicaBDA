/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.CitaBO;
import BO.MedicoBO;
import BO.PacienteBO;
import DTO.CitaViejoDTO;
import Exception.NegocioException;
import configuracion.DependencyInjector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sesion.SessionManager;

/**
 * Frame CancelarCita. Representa la presentación para cancelar una cita.
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class CancelarCita extends javax.swing.JFrame {

    private static CancelarCita instance;
    private MedicoBO medicoBO = DependencyInjector.crearMedicoBO();
    private CitaBO citaBO = DependencyInjector.crearCitaBO();
    private PacienteBO pacienteBO = DependencyInjector.crearPacienteBO();
    private PrincipalPaciente principalPaciente;

    /**
     * Creates new form RegistrarCita
     */
    public CancelarCita() {
        initComponents();
        setLocationRelativeTo(null);
        cargarCitas();
    }

    /**
     * Obtiene la instancia estática de la clase. Se utiliza para poder cambiar
     * entre ventanas con una única instancia.
     *
     * @return Intancia estática de la clase.
     * @throws NegocioException Si hubo un error al recuperar la instancia.
     */
    public static CancelarCita getInstance() throws NegocioException {
        if (instance == null) {
            instance = new CancelarCita();
        }
        return instance;
    }

    /**
     * Obtiene la ventana de principal paciente.
     *
     * @return Ventana de principal paciente.
     */
    public PrincipalPaciente getPrincipalPaciente() {
        return principalPaciente;
    }

    /**
     * Asigna el valor de la ventana de principal paciente al valor de su
     * parámetro.
     *
     * @param principalPaciente Valor a asignar para la ventana de principal
     * paciente.
     */
    public void setPrincipalPaciente(PrincipalPaciente principalPaciente) {
        this.principalPaciente = principalPaciente;
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idCita", "Medico", "Especialidad", "Fecha", "Hora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
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
        jTable1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(2).setMinWidth(10);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);
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
        jButton2.setText("Cancelar cita");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel1.setText("Cancelar cita");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(279, 279, 279))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(jLabel1)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
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
        volver();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar una cita.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        cancelarCita();
        cargarCitas();
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
            java.util.logging.Logger.getLogger(CancelarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CancelarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CancelarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CancelarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CancelarCita().setVisible(true);
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

    /**
     * Carga las citas programadas activas.
     */
    public void cargarCitas() {
        // Obtiene el modelo
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        try {
            //Obtiene las citas activas del paciente
            List<CitaViejoDTO> citas = pacienteBO.obtenerCitasActivasPaciente(SessionManager.getInstance().getUser());

            // Agrega cada cita a la tabla
            for (CitaViejoDTO cita : citas) {
                if (!cita.getTipo().equalsIgnoreCase("EMERGENCIA")) {
                    modelo.addRow(new Object[]{
                        cita.getIdCita(),
                        cita.getMedico().getNombre() + " " + cita.getMedico().getApellidoPaterno(),
                        cita.getMedico().getEspecialidad(),
                        cita.getFechaHoraInicio().toLocalDate(),
                        cita.getFechaHoraInicio().toLocalTime()
                    });
                }
            }
        } catch (NegocioException ex) {
            Logger.getLogger(CancelarCita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cancela una cita elegida.
     */
    private void cancelarCita() {
        int resultado = JOptionPane.showConfirmDialog(this, "¿Estas seguro que deseas cancelar la cita?", "Cancelar cita", JOptionPane.YES_NO_OPTION);

        // Si confirmó la acción
        if (resultado == JOptionPane.YES_OPTION) {
            int filaSeleccionada = jTable1.getSelectedRow();

            // Convertir el valor de la tabla a String
            String valorFecha = jTable1.getValueAt(filaSeleccionada, 3).toString();
            String valorHora = jTable1.getValueAt(filaSeleccionada, 4).toString();

            // Combinar ambas cadenas en una única cadena en formato ISO
            String fechaHoraCombinada = valorFecha + "T" + valorHora;

            // Convertir a LocalDateTime usando el formateador ISO
            LocalDateTime fechaInicio = LocalDateTime.parse(fechaHoraCombinada, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            // Obtener las horas que faltan para que inicie la cita
            long duracionHoras = Duration.between(LocalDateTime.now(), fechaInicio).toHours();

            // Si son menos de 24 horas
            if (duracionHoras < 24) {
                JOptionPane.showMessageDialog(this, "Solo puedes cancelar citas con 24 horas de anticipación.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Intenta cancelar la cita
            try {
                CitaViejoDTO cita = new CitaViejoDTO();
                cita.setIdCita((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));

                boolean resultadoCita = citaBO.cancelarCita(cita);

                // Si se canceló la cita
                if (resultadoCita) {
                    JOptionPane.showMessageDialog(this, "La cita fue cancelada exitosamente");
                }
            } catch (NegocioException ex) {
                Logger.getLogger(CancelarCita.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error inesperado", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Envía a la pestaña de principal paciente.
     */
    private void volver() {
        try {
            PrincipalPaciente principalPaciente = PrincipalPaciente.getInstance();
            principalPaciente.setCancelarCita(this);
            principalPaciente.setVisible(true);
            this.setVisible(false);
        } catch (NegocioException ex) {
            Logger.getLogger(CancelarCita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
