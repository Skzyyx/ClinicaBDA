/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.CitaBO;
import BO.MedicoBO;
import BO.PacienteBO;
import DTO.CitaNuevoDTO;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sesion.SessionManager;

/**
 * Frame AgendarCita. Representa la presentación para agendar una cita.
 *
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class AgendarCita extends javax.swing.JFrame {

    private static AgendarCita instance;
    private MedicoBO medicoBO = DependencyInjector.crearMedicoBO();
    private CitaBO citaBO = DependencyInjector.crearCitaBO();
    private PacienteBO pacienteBO = DependencyInjector.crearPacienteBO();
    private PrincipalPaciente principalPaciente;
    private AgendarCitaEmergencia citaEmergenciaFrame;

    /**
     * Creates new form RegistrarCita
     */
    public AgendarCita() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Paciente - Agendar cita");
        choice1.addItem("Ninguno");
        cargarListener();
        cargarMedicos();
        cargarEspecialidades();
    }

    /**
     * Obtiene la instancia estática de la clase. Se utiliza para poder cambiar
     * entre ventanas con una única instancia.
     *
     * @return Intancia estática de la clase.
     * @throws NegocioException Si hubo un error al recuperar la instancia.
     */
    public static AgendarCita getInstance() throws NegocioException {
        if (instance == null) {
            instance = new AgendarCita();
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
     * @param citaEmergenciaFrame Valor a asignar para la ventana de cita de
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        choice1 = new java.awt.Choice();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(211, 222, 231));

        jPanel2.setBackground(new java.awt.Color(211, 222, 231));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medico", "Especialidad", "idMedico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(2).setMinWidth(10);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);
        }

        jLabel2.setText("Seleccione un médico:");
        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N

        choice1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice1ItemStateChanged(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Día", "Hora Inicio", "Hora Fin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(jTable2);

        jLabel3.setText("Fecha:");
        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N

        datePicker1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel4.setText("Especialidad:");
        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N

        jButton1.setText("Volver");
        jButton1.setBackground(new java.awt.Color(44, 45, 45));
        jButton1.setBorderPainted(false);
        jButton1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        jButton3.setText("Cita de emergencia");
        jButton3.setBackground(new java.awt.Color(255, 102, 102));
        jButton3.setBorderPainted(false);
        jButton3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Agendar cita");
        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N

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
                        .addGap(111, 111, 111)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButton3)))))
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(317, 317, 317)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(false);
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
        jButton3.setContentAreaFilled(false);
        jButton3.setOpaque(false);
        jButton3.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
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
        registrarCita();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void choice1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice1ItemStateChanged
        jTable1.clearSelection();
        jTable2.clearSelection();
        cargarMedicos();
    }//GEN-LAST:event_choice1ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        citaDeEmergencia();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(AgendarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendarCita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendarCita().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

    /**
     * Carga las especialidades en el choice.
     */
    private void cargarEspecialidades() {
        try {
            // Obtiene las especialidades
            List<String> especialidades = medicoBO.obtenerEspecialidades();

            // Las agrega al choice
            for (String especialidad : especialidades) {
                choice1.addItem(especialidad);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(AgendarCita.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al cargar especialidades: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carga los médicos en la tabla.
     */
    private void cargarMedicos() {
        // Obtiene el módelo y borra registros previos
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        try {
            // Obtiene los médicos activos
            List<MedicoViejoDTO> medicos = medicoBO.obtenerMedicos();
            // Obtiene la especialidad seleccionada
            String filtro = choice1.getSelectedItem();

            // Agrega los médicos a la tabla
            for (MedicoViejoDTO medico : medicos) {
                // Si el filtro es "Ninguno" (mostrar todos) o la especialidad coincide, agregar al modelo
                if ("Ninguno".equals(filtro) || medico.getEspecialidad().equals(filtro)) {
                    modelo.addRow(new Object[]{
                        medico.getNombre(),
                        medico.getEspecialidad(),
                        medico.getIdMedico()
                    });
                }
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar médicos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carga los listeners a los elementos.
     */
    private void cargarListener() {
        // Listener para cuando seleccione la fecha
        datePicker1.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent dce) {
                int filaSeleccionada = jTable1.getSelectedRow();

                // Si seleccionó un médico
                if (filaSeleccionada != -1) {
                    // Obtener la fecha seleccionada
                    LocalDate fechaSeleccionada = datePicker1.getDate();
                    if (fechaSeleccionada != null) {
                        // Si la fecha seleccionada es anterior a la fecha actual
                        if (fechaSeleccionada.isBefore(LocalDate.now())) {
                            datePicker1.closePopup();
                            JOptionPane.showMessageDialog(AgendarCita.this, "Por favor, selecciona una fecha igual o mayor a la actual.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        // Convertir el valor de la tabla a Integer
                        String idMedicoStr = jTable1.getValueAt(filaSeleccionada, 2).toString();
                        try {
                            // Genera el horario de citas del médico
                            int idMedico = Integer.parseInt(idMedicoStr);
                            generarHorariosCitas(idMedico);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(AgendarCita.this, "Error: El ID del médico no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (PersistenciaException ex) {
                            Logger.getLogger(AgendarCita.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(AgendarCita.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    // Si no seleccionó un médico
                } else {
                    // Ocultar el panel del calendario si está abierto
                    datePicker1.closePopup();
                    // Mostrar el mensaje de advertencia
                    JOptionPane.showMessageDialog(AgendarCita.this, "Por favor, selecciona un médico.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Cada que el usuario seleccione una fila
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = jTable1.getSelectedRow();

                if (filaSeleccionada == -1) {
                    return;
                }

                // Aquí se maneja la selección de la fila
                String idMedicoStr = jTable1.getValueAt(filaSeleccionada, 2).toString();

                int idMedico = Integer.parseInt(idMedicoStr);
                try {
                    generarHorariosCitas(idMedico);
                } catch (PersistenciaException ex) {
                    Logger.getLogger(AgendarCita.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * Genera el horario de las citas a partir del horario del médico
     * seleccionado.
     *
     * @param id Identificador del médico seleccionado.
     * @throws PersistenciaException Si hubo un error al generar los horarios de
     * citas.
     */
    private void generarHorariosCitas(int id) throws PersistenciaException {
        DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
        modelo.setRowCount(0);

        try {

            LocalDate fechaSeleccionada = datePicker1.getDate();
            if (fechaSeleccionada == null) {
                return; // Si no hay fecha seleccionada, no hacer nada
            }
            // Obtener el día de la semana de la fecha seleccionada
            String diaSemanaSeleccionado = fechaSeleccionada.getDayOfWeek().toString();
            diaSemanaSeleccionado = diaSemanaSeleccionado.substring(0, 1) + diaSemanaSeleccionado.substring(1).toLowerCase();
            List<HorarioViejoDTO> horarios = medicoBO.obtenerHorariosMedico(String.valueOf(id));

            for (HorarioViejoDTO horario : horarios) {
                // Verificar si el horario corresponde al día seleccionado
                if (horario.getDiaSemana().equalsIgnoreCase(diaSemanaSeleccionado)) {
                    LocalTime horaInicio = horario.getHoraEntrada();
                    LocalTime horaFin = horario.getHoraSalida();

                    // Generar citas de 30 minutos dentro del horario del médico
                    while (horaInicio.isBefore(horaFin)) {
                        LocalTime horaCitaFin = horaInicio.plusMinutes(30);

                        // Verificar que la cita no exceda el horario de salida
                        if (horaCitaFin.isAfter(horaFin)) {
                            break;
                        }

                        // Crear un objeto CitaNuevoDTO para verificar si la cita está ocupada
                        LocalDateTime fechaHoraInicio = LocalDateTime.of(fechaSeleccionada, horaInicio);

                        // Verificar si la cita está ocupada
                        if (!citaBO.verificarCitaExiste(Timestamp.valueOf(fechaHoraInicio), String.valueOf(id))) {

                            if (fechaHoraInicio.isAfter(LocalDateTime.now())) {
                                // Si la cita no está ocupada, agregarla a la tabla
                                modelo.addRow(new Object[]{
                                    fechaSeleccionada,
                                    horaInicio,
                                    horaCitaFin
                                });
                            }
                        }

                        // Avanzar 30 minutos para la próxima cita
                        horaInicio = horaInicio.plusMinutes(30);
                    }
                }
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(AgendarCita.this, "Error al cargar medicos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Registra una nueva cita.
     */
    private void registrarCita() {
        // Validaciones de selección
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un doctor.", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (jTable2.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un horario.", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (datePicker1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar una fecha.", "Error", JOptionPane.WARNING_MESSAGE);
        }

        // Obtiene la fecha elegida
        LocalDate fechaSeleccionada = datePicker1.getDate();
        // Obtiene la hora de cita elegida
        LocalTime horaInicio = (LocalTime) jTable2.getValueAt(jTable2.getSelectedRow(), 1);
        // Obtiene el id del médicp
        String idMedico = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 2));

        // Crea los objetos
        MedicoViejoDTO medico = new MedicoViejoDTO(idMedico);
        PacienteNuevoDTO paciente = new PacienteNuevoDTO();
        paciente.setEmail(SessionManager.getInstance().getUser());
        LocalDateTime fechaHoraInicio = LocalDateTime.of(fechaSeleccionada, horaInicio);
        CitaNuevoDTO cita = new CitaNuevoDTO();
        cita.setFechaHoraInicio(fechaHoraInicio);
        cita.setMedico(medico);
        cita.setPaciente(paciente);

        // Intenta programar la cita
        try {
            boolean resultado = citaBO.registrarCitaProgramada(cita);
            // Si se programa la cita
            if (resultado) {
                JOptionPane.showMessageDialog(this, "Tu cita ha sido registrada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                // Actualizar la lista de citas en la interfaz de cancelar citas
                CancelarCita cancelarCita = CancelarCita.getInstance();
                cancelarCita.cargarCitas();
            }
        } catch (NegocioException ex) {
            Logger.getLogger(AgendarCita.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Registra una cita de emergencia. Envía a la ventana de agendar cita
     * emergencia.
     */
    private void citaDeEmergencia() {
        try {
            // Obtiene el primer médico disponible para la cita
            MedicoViejoDTO medico = medicoBO.obtenerPrimerMedicoDisponible();

            // Si hay médico disponible
            if (medico != null) {
                // Crea los objetos
                PacienteNuevoDTO paciente = new PacienteNuevoDTO();
                paciente.setEmail(SessionManager.getInstance().getUser());
                CitaNuevoDTO cita = new CitaNuevoDTO();
                cita.setPaciente(paciente);
                cita.setMedico(medico);

                // Genera la cita de emergencia
                String folio = citaBO.registrarCitaEmergencia(cita);
                // Si se generó la cita
                if (folio != null) {
                    JOptionPane.showMessageDialog(this, "Tu cita de emergencia ha sido generada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    // Envía a la ventana de cita de emergencia
                    AgendarCitaEmergencia citaEmergencia = AgendarCitaEmergencia.getInstance();
                    citaEmergencia.setAgendarCitaFrame(this);
                    citaEmergencia.setVisible(true);
                    citaEmergencia.cargarDatos(folio);
                    this.setVisible(false);
                    // Si no se generó la cita
                } else {
                    JOptionPane.showMessageDialog(this, "No se ha podido generar la cita de emergencia .", "Error", JOptionPane.WARNING_MESSAGE);
                }

            }
        } catch (NegocioException ex) {
            Logger.getLogger(AgendarCita.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Envía a la ventana de menú principal de paciente
     */
    private void volver() {
        try {
            PrincipalPaciente principalPacienteF = PrincipalPaciente.getInstance();
            principalPacienteF.setAgendarCita(this);
            principalPacienteF.setVisible(true);
            this.setVisible(false);
        } catch (NegocioException ex) {
            Logger.getLogger(AgendarCita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
