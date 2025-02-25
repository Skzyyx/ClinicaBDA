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
 * Frame VerHistorialMedico. Representa la presentación para ver el historial de
 * consultas realizadas por el médico.
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class VerHistorialMedico extends javax.swing.JFrame {

    private final PacienteBO pacienteBO = DependencyInjector.crearPacienteBO();
    private static VerHistorialMedico instance;
    private MedicoBO medicoBO = DependencyInjector.crearMedicoBO();
    private CitaBO citaBO = DependencyInjector.crearCitaBO();
    private ConsultaBO consultaBO = DependencyInjector.crearConsultaBO();
    // Formateador de fecha
    DateTimeFormatter formatoFecha;
    private PrincipalMedico principalMedicoFrame;

    /**
     * Creates new form RegistrarCita
     */
    public VerHistorialMedico() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Médico - Historial de consultas");
        chNombre.addItem("Ninguno");
        formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        cargarListener();
        cargarHistorial();
    }

    /**
     * Obtiene la instancia estática de la clase. Se utiliza para poder cambiar
     * entre ventanas con una única instancia.
     *
     * @return Intancia estática de la clase.
     */
    public static VerHistorialMedico getInstance() throws NegocioException {
        if (instance == null) {
            instance = new VerHistorialMedico();
        }
        return instance;
    }

    /**
     * Obtiene la ventana de menú principal del médico.
     *
     * @return Ventana de menú principal del médico.
     */
    public PrincipalMedico getPrincipalMedicoFrame() {
        return principalMedicoFrame;
    }

    /**
     * Asigna el valor de la ventana de menú principal del médico al valor de su
     * parámetro.
     *
     * @param principalMedicoFrame Valor a asignar a la ventana de menú principal del
     * médico.
     */
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        chNombre = new java.awt.Choice();
        jLabel4 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        dpFecha = new com.github.lgooddatepicker.components.DatePicker();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(211, 222, 231));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha y hora", "Tipo de cita", "Folio", "Paciente", "Estado", "Diagnóstico", "Tratamiento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false
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
            tabla.getColumnModel().getColumn(4).setResizable(false);
        }

        chNombre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chNombreItemStateChanged(evt);
            }
        });

        jLabel4.setText("Nombre del paciente:");
        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N

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

        dpFecha.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel5.setText("Fecha");
        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(245, 245, 245))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dpFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dpFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void chNombreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chNombreItemStateChanged
        tabla.clearSelection();
        tabla.clearSelection();
        cargarHistorial();
    }//GEN-LAST:event_chNombreItemStateChanged

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
            java.util.logging.Logger.getLogger(VerHistorialMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerHistorialMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerHistorialMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerHistorialMedico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new VerHistorialMedico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private java.awt.Choice chNombre;
    private com.github.lgooddatepicker.components.DatePicker dpFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    /**
     * Carga los nombres de los pacientes en el choice.
     */
    public void cargarNombres() {
        try {
            // Obtener la lista de activistas desde la capa de negocio (BO)
            List<ConsultaViejoDTO> consultas = medicoBO.obtenerConsultasPorMedico(SessionManager.getInstance().getUser());
            // Filtra los nombres
            List<String> nombres = consultaBO.nombresPacientesConsultas(consultas);

            for (String nombre : nombres) {

                chNombre.addItem(nombre);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(VerHistorialMedico.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al cargar nombres de pacientes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            volver();
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
            List<ConsultaViejoDTO> consultas = medicoBO.obtenerConsultasPorMedico(SessionManager.getInstance().getUser());

            String filtro = chNombre.getSelectedItem(); // Obtiene el nombre seleccionado

            // Recorrer la lista de activistas y agregarlos como filas en la tabla
            for (ConsultaViejoDTO consulta : consultas) {
                String apellidoMaterno = consulta.getCita().getPaciente().getApellidoMaterno();
                if (apellidoMaterno == null) {
                    apellidoMaterno = "";
                }

                String nombreCompleto = consulta.getCita().getPaciente().getNombre() + " " + consulta.getCita().getPaciente().getApellidoPaterno() + " " + apellidoMaterno;
                // Si el filtro es "Ninguno" (mostrar todos) o la especialidad coincide, agregar al modelo
                if ("Ninguno".equals(filtro) || nombreCompleto.equals(filtro)) {
                    modelo.addRow(new Object[]{
                        consulta.getCita().getFechaHoraInicio().format(formatoFecha),
                        consulta.getCita().getTipo(),
                        consulta.getCita().getFolio(),
                        nombreCompleto,
                        consulta.getEstado(),
                        consulta.getDiagnostico(),
                        consulta.getTratamiento()});
                }
            }
        } catch (NegocioException ex) {
            // Manejo de errores en caso de que falle la obtención de datos
            JOptionPane.showMessageDialog(this, "Error al cargar consulta: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            volver();
        }
    }

    /**
     * Carga los listeners de los componentes.
     */
    private void cargarListener() {
        DateChangeListener listenerFecha = new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent dce) {
                // Verificar si el DatePicker está correctamente inicializado
                if (dpFecha == null) {
                    Logger.getLogger(VerHistorialMedico.class.getName()).log(Level.SEVERE, "dpFecha no está inicializado");
                    return;
                }

                // Obtener la fecha seleccionada
                LocalDate fechaSeleccionada = dpFecha.getDate();

                if (fechaSeleccionada == null) {
                    return; // No hacer nada si la fecha es nula
                }

                // Obtener el modelo de la tabla y limpiar cualquier dato previo
                DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                modelo.setRowCount(0); // Limpiar todas las filas existentes en la tabla

                try {
                    // Verificar si medicoBO está inicializado
                    if (medicoBO == null) {
                        throw new IllegalStateException("medicoBO no ha sido inicializado.");
                    }

                    List<ConsultaViejoDTO> consultas = medicoBO.obtenerConsultasPorMedico(SessionManager.getInstance().getUser());

                    // Verificar si consultaBO está inicializado
                    if (consultaBO == null) {
                        throw new IllegalStateException("consultaBO no ha sido inicializado.");
                    }

                    List<ConsultaViejoDTO> consultasFiltradas = consultaBO.filtrarConsultasFecha(consultas, fechaSeleccionada);

                    // Agregar las consultas filtradas a la tabla
                    for (ConsultaViejoDTO consulta : consultasFiltradas) {
                        modelo.addRow(new Object[]{
                            consulta.getCita().getFechaHoraInicio().format(formatoFecha),
                            consulta.getCita().getTipo(),
                            consulta.getCita().getFolio(),
                            consulta.getCita().getPaciente().getNombre() + " "
                            + consulta.getCita().getPaciente().getApellidoPaterno() + " "
                            + consulta.getCita().getPaciente().getApellidoMaterno(),
                            consulta.getEstado(),
                            consulta.getDiagnostico(),
                            consulta.getTratamiento()
                        });
                    }
                } catch (NegocioException ex) {
                    Logger.getLogger(VerHistorialMedico.class.getName()).log(Level.SEVERE, "Error al filtrar consultas", ex);
                } catch (IllegalStateException e) {
                    Logger.getLogger(VerHistorialMedico.class.getName()).log(Level.SEVERE, e.getMessage(), e);
                }
            }
        };

        dpFecha.addDateChangeListener(listenerFecha);
    }

    /**
     * Envía al menú principal de medico
     */
    private void volver() {
        try {
            PrincipalMedico principalMedico = PrincipalMedico.getInstance();
            principalMedico.setHistorialMedicoFrame(this);
            principalMedico.setVisible(true);
            this.setVisible(false);
        } catch (NegocioException ex) {
            Logger.getLogger(VerHistorialMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
