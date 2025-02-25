/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.CitaBO;
import BO.ConsultaBO;
import BO.MedicoBO;
import BO.PacienteBO;
import DTO.ConsultaViejoDTO;
import Exception.NegocioException;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import configuracion.DependencyInjector;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sesion.SessionManager;

/**
 * Frame VerHistorialPaciente. Representa la presentación para ver el historial
 * de consultas del paciente.
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class VerHistorialPaciente extends javax.swing.JFrame {

    private final PacienteBO pacienteBO = DependencyInjector.crearPacienteBO();
    private static VerHistorialPaciente instance;
    private MedicoBO medicoBO = DependencyInjector.crearMedicoBO();
    private CitaBO citaBO = DependencyInjector.crearCitaBO();
    private ConsultaBO consultaBO = DependencyInjector.crearConsultaBO();
    // Formateador de fecha
    private DateTimeFormatter formatoFecha;
    private PrincipalPaciente principalPacienteFrame;

    /**
     * Creates new form RegistrarCita
     */
    public VerHistorialPaciente() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Paciente - Historial de consultas");
        chEspecialidad.addItem("Ninguno");
        formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        cargarListener();
        cargarEspecialidades();
        cargarHistorial();
    }

    /**
     * Obtiene la instancia estática de la clase. Se utiliza para poder cambiar
     * entre ventanas con una única instancia.
     *
     * @return Intancia estática de la clase.
     */
    public static VerHistorialPaciente getInstance() {
        if (instance == null) {
            instance = new VerHistorialPaciente();
        }
        return instance;
    }

    /**
     * Obtiene la ventana de menú principal del paciente.
     *
     * @return Ventana de menú principal del paciente.
     */
    public PrincipalPaciente getPrincipalPacienteFrame() {
        return principalPacienteFrame;
    }

    /**
     * Asigna el valor de la ventana de menú principal del paciente al valor de
     * su parámetro.
     *
     * @param principalPacienteFrame Valor a asignar a la ventana de menú
     * principal del paciente.
     */
    public void setPrincipalPacienteFrame(PrincipalPaciente principalPacienteFrame) {
        this.principalPacienteFrame = principalPacienteFrame;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        chEspecialidad = new java.awt.Choice();
        jLabel3 = new javax.swing.JLabel();
        dpFin = new com.github.lgooddatepicker.components.DatePicker();
        jLabel4 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        dpInicio = new com.github.lgooddatepicker.components.DatePicker();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(211, 222, 231));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha y hora", "Tipo de cita", "Folio", "Estado", "Medico", "Especialidad", "Diagnóstico", "Tratamiento", "Notas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setFont(new java.awt.Font("Segoe UI Semilight", 0, 10)); // NOI18N
        tabla.setRowHeight(40);
        jScrollPane1.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(55);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(55);
            tabla.getColumnModel().getColumn(2).setMinWidth(10);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(25);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(35);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(25);
            tabla.getColumnModel().getColumn(5).setMinWidth(0);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
            tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Periodo");
        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N

        chEspecialidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chEspecialidadItemStateChanged(evt);
            }
        });

        jLabel3.setText("Fin:");
        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N

        dpFin.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel4.setText("Especialidad:");
        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N

        btnVolver.setText("Volver");
        btnVolver.setBackground(new java.awt.Color(44, 45, 45));
        btnVolver.setBorderPainted(false);
        btnVolver.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabel1.setText("Historial de consultas");
        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N

        dpInicio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel5.setText("Inicio:");
        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(246, 246, 246))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dpInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dpFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dpFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(dpInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        volver();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void chEspecialidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chEspecialidadItemStateChanged
        tabla.clearSelection();
        tabla.clearSelection();
        cargarHistorial();
    }//GEN-LAST:event_chEspecialidadItemStateChanged

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
            java.util.logging.Logger.getLogger(VerHistorialPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerHistorialPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerHistorialPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerHistorialPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerHistorialPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private java.awt.Choice chEspecialidad;
    private com.github.lgooddatepicker.components.DatePicker dpFin;
    private com.github.lgooddatepicker.components.DatePicker dpInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    /**
     * Carga las especialidades en el choice
     */
    private void cargarEspecialidades() {
        try {
            // Obtener la lista de activistas desde la capa de negocio (BO)
            List<ConsultaViejoDTO> consultas = pacienteBO.obtenerConsultasPaciente(SessionManager.getInstance().getUser());
            // Filtra las especialidades
            List<String> especialidades = consultaBO.especialidadesConsultas(consultas);

            for (String especialidad : especialidades) {
                chEspecialidad.addItem(especialidad);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(VerHistorialPaciente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al cargar especialidades: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carga el historial de consultas.
     */
    public void cargarHistorial() {
        // Obtener el modelo de la tabla y limpiar cualquier dato previo
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); // Limpiar todas las filas existentes en la tabla

        try {
            // Obtener la lista de activistas desde la capa de negocio (BO)
            List<ConsultaViejoDTO> consultas = pacienteBO.obtenerConsultasPaciente(SessionManager.getInstance().getUser());

            String filtro = chEspecialidad.getSelectedItem(); // Obtiene la especialidad seleccionada

            // Recorrer la lista de activistas y agregarlos como filas en la tabla
            for (ConsultaViejoDTO consulta : consultas) {
                // Si el filtro es "Ninguno" (mostrar todos) o la especialidad coincide, agregar al modelo
                if ("Ninguno".equals(filtro) || consulta.getCita().getMedico().getEspecialidad().equals(filtro)) {
                    modelo.addRow(new Object[]{
                        consulta.getCita().getFechaHoraInicio().format(formatoFecha),
                        consulta.getCita().getTipo(),
                        consulta.getCita().getFolio(),
                        consulta.getEstado(),
                        consulta.getCita().getMedico().getCedula(),
                        consulta.getCita().getMedico().getEspecialidad(),
                        consulta.getDiagnostico(),
                        consulta.getTratamiento(),
                        consulta.getNotas(),});
                }

            }
        } catch (NegocioException ex) {
            // Manejo de errores en caso de que falle la obtención de datos
            JOptionPane.showMessageDialog(this, "Error al cargar consulta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carga los listeners de los componentes.
     */
    private void cargarListener() {
        DateChangeListener listenerPeriodo = new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent dce) {
                // Obtener la fecha seleccionada
                LocalDate fechaInicio = dpInicio.getDate();
                LocalDate fechaFin = dpFin.getDate();
                if (fechaInicio == null || fechaFin == null) {
                    return;
                }
                // Si la fecha seleccionada es anterior a la fecha actual
                if (fechaInicio.isAfter(fechaFin) || fechaFin.isBefore(fechaInicio)) {
                    dpInicio.closePopup();
                    dpFin.closePopup();
                    JOptionPane.showMessageDialog(VerHistorialPaciente.this, "Por favor, selecciona un orden correcto de fechas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Obtener el modelo de la tabla y limpiar cualquier dato previo
                DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                modelo.setRowCount(0); // Limpiar todas las filas existentes en la tabla

                try {
                    List<ConsultaViejoDTO> consultas = pacienteBO.obtenerConsultasPaciente(SessionManager.getInstance().getUser());
                    List<ConsultaViejoDTO> consultasFiltradas = consultaBO.filtrarConsultasPeriodo(consultas, fechaInicio, fechaFin);

                    // Agregar las consultas filtradas a la tabla
                    for (ConsultaViejoDTO consulta : consultasFiltradas) {
                        modelo.addRow(new Object[]{
                            consulta.getCita().getFechaHoraInicio().format(formatoFecha),
                            consulta.getCita().getTipo(),
                            consulta.getCita().getFolio(),
                            consulta.getEstado(),
                            consulta.getCita().getMedico().getCedula(),
                            consulta.getCita().getMedico().getEspecialidad(),
                            consulta.getDiagnostico(),
                            consulta.getTratamiento(),
                            consulta.getNotas(),});
                    }
                } catch (NegocioException ex) {
                    Logger.getLogger(VerHistorialPaciente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        dpInicio.addDateChangeListener(listenerPeriodo);
        dpFin.addDateChangeListener(listenerPeriodo);
    }

    /**
     * Envía al menú principal de paciente
     */
    private void volver() {
        try {
            PrincipalPaciente principalPaciente = PrincipalPaciente.getInstance();
            principalPaciente.setHistorialPacienteFrame(this);
            principalPaciente.setVisible(true);
            this.setVisible(false);
        } catch (NegocioException ex) {
            Logger.getLogger(VerHistorialPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
