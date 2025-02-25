/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexion;
import entidades.Cita;
import entidades.Consulta;
import entidades.Horario;
import entidades.Medico;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase MedicoDAO.
 * Representa el DAO para la clase Medico.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha  
 */
public class MedicoDAO implements IMedicoDAO {
    /** Conexión a utiizar */
    private final IConexion conexion;

    /**
     * Constructor que recibe una conexión a la base de datos.
     * @param conexion Objeto de tipo IConexion para gestionar la conexión con la base de datos.
     */
    public MedicoDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Cambia el estado de un médico específico (da de baja o alta).
     * @param cedula Cédula profesional del médico.
     * @param nuevoEstado Nuevo estado para cambiar.
     * @return True si se cambió el estado, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al intentar cambiar el estado.
     */
    @Override
    public boolean cambiarEstadoMedico(String cedula, String nuevoEstado) throws PersistenciaException {
        // Sentencia SQL para llamar al procedimiento almacenado
        String sentenciaSQL = "CALL cambiarEstadoMedico(?, ?)";
        
        // Se abre la conexión y se prepara la sentencia
        try (Connection con = conexion.crearConexion();
                CallableStatement cs = con.prepareCall(sentenciaSQL)) {
            
            // Se establece la cedula y el estado nuevo
            cs.setString(1, cedula);
            cs.setString(2, nuevoEstado);
            
            // Se ejecuta la consulta
            cs.executeUpdate();
            
            // Si llega aquí, el médico fue dado de alta con éxito.
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se pudo dar de alta al médico.");
        }
    }

    /**
     * Obtiene los registros de todos los médicos.
     * @return Lista con los registros de los médicos encontrados.
     * @throws PersistenciaException Si hubo un error al consultar los médicos.
     */
    @Override
    public List<Medico> obtenerMedicos() throws PersistenciaException {
        // Se crea la lista a devolver
        List<Medico> medicos = new ArrayList<>();
        
        // Sentencia SQL a ejecutar
        String sentenciaSQL = "SELECT * FROM todosLosMedicosConEspecialidad";
        
        // Se crea la conexión y se prepara la sentencia
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(sentenciaSQL)) {
            
            // Se ejecuta la consulta
            ResultSet rs = ps.executeQuery();
            
            // Se llena la lista con los datos de la consulta
            while (rs.next()) {
                if (rs.getString("estado").equals("ACTIVO")) {
                    medicos.add(new Medico(
                            rs.getInt("idMedico"),
                        rs.getString("nombre"), 
                        rs.getString("apellidoPaterno"), 
                        rs.getString("apellidoMaterno"),
                        rs.getString("especialidad"), 
                        rs.getString("cedula"), 
                            "ACTIVO"));
                }
            }
            
            // Se devuelve la lista
            return medicos;
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se pudieron obtener los medicos");
        }
    }

    /**
     * Obtiene una lista con todas las especialidades médicas.
     * @return una lista con todos las especialidades médicas.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public List<String> obtenerEspecialidades() throws PersistenciaException {
        
        // Se crea la lista a devolver
        List<String> especialidades = new ArrayList<>();
        
        // Sentencia SQL a ejecutar
        String sentenciaSQL = "SELECT * FROM todasLasEspecialidades";
        
        // Se crea la conexion y se prepara la consulta
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(sentenciaSQL)) {
            
            // Se ejecuta la consulta
            ResultSet rs = ps.executeQuery();
            
            // Se llena la lista con las especialidades
            while (rs.next()){ 
                especialidades.add(rs.getString("especialidad"));
            }
            
            // Se devuelve la lista
            return especialidades;
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se pudieron obtener las especialidades");
        }
    }
    
    /**
     * Obtiene los registros de todos los médicos.
     * @return Lista con los registros de los médicos encontrados.
     * @throws PersistenciaException Si hubo un error al consultar los médicos.
     */
    @Override
    public Medico obtenerMedico(String cedula) throws PersistenciaException {
        Medico medico = null;
        String sentenciaSQL = "CALL consultarMedico(?)";
        
        // Intenta la conexión
        try (Connection con = conexion.crearConexion();
             CallableStatement cb = con.prepareCall(sentenciaSQL)) {
            
            // Se setea el valor del id a buscar
            cb.setString(1, cedula);
            
            // Intenta la búsqueda
            try (ResultSet rs = cb.executeQuery()) {
                // Si encontró coincidencia
                if (rs.next()) {
                    medico = new Medico();
                    medico.setIdMedico(rs.getInt("idMedico"));
                    medico.setNombre(rs.getString("nombre"));
                    medico.setApellidoPaterno(rs.getString("apellidoPaterno"));
                    medico.setApellidoMaterno(rs.getString("apellidoMaterno"));
                    medico.setEspecialidad(rs.getString("especialidad"));
                    medico.setCedula(rs.getString("cedula"));
                    medico.setEstado(rs.getString("estado"));
                    
                    Usuario usuario = new Usuario(
                            rs.getInt("idUsuario"),
                            rs.getString("usuario"),
                            rs.getString("contrasenia"),
                            rs.getString("rol")
                    );
                    medico.setUsuario(usuario);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Error inesperado: " + ex.getMessage());
            }

        // Si ocurre un error
        } catch (Exception e) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new PersistenciaException("Error al consultar médico: " + e.getMessage());
        }
        
        return medico;
    }

    /**
     * Obtiene los horarios asociados a un médico específico.
     * @param id Id del médico a consultar.
     * @return Lista con los horarios del médico.
     * @throws PersistenciaException Si hubo un error al consultar los horarios.
     */
    @Override
    public List<Horario> obtenerHorariosMedicoPorID(int id) throws PersistenciaException {
        List<Horario> horarios = new ArrayList<>();
        String sentenciaSQL = "CALL consultarHorariosMedicoPorID(?)";
        
        // Intenta la conexion
        try(Connection con =conexion.crearConexion();
                CallableStatement cb = con.prepareCall(sentenciaSQL)) {
            
            // Se setea el valor del id a buscar
            cb.setInt(1, id);
            
            // Intenta la búsqueda
            try (ResultSet rs =cb.executeQuery()){
                // Si encontró registro
                while (rs.next()){
                    // Agrega el horario a la lista
                    horarios.add(new Horario(
                        rs.getString("diaSemana"),
                        rs.getTime("horaEntrada").toLocalTime(),
                        rs.getTime("horaSalida").toLocalTime(),
                        null)
                    );     
                }
                
                return horarios;
            // Si falla la búsqueda
            } catch (SQLException ex) {
                Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Error inesperado: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al consultar el horario:"+ ex.getMessage());
        }
    }
    
    /**
     * Cuenta la cantidad de médicos que se encuentren activos.
     * @return Cantidad de médicos con estado "ACTIVO".
     * @throws excepciones.PersistenciaException Si hubo un error al contar médicos.
     */
    @Override
    public int contarMedicosActivos() throws PersistenciaException {
        String sentenciaSQL = "SELECT contarMedicosActivos()";
        int cantidadMedicosActivos = 0;
        
        // Intenta la conexión
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sentenciaSQL)) {
            
            // Intenta la búsqueda
            try (ResultSet rs = ps.executeQuery()) {
                // Si encontró coincidencia
                if (rs.next()) {
                    // Asignar el valor obtenido a la variable
                    cantidadMedicosActivos = rs.getInt(1);
                }
            }
            
        return cantidadMedicosActivos;
        // Si ocurre un error
        } catch (Exception e) {
            throw new PersistenciaException("Error al contar médicos activos: " + e.getMessage());
        }
    }
    
    /**
     * Verifica si un médico específico tiene citas activas.
     * @param cedula Cédula profesional del médico a consultar.
     * @return True si tiene citas activas, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al consultar.
     */
    @Override
    public boolean tieneCitasActivas(String cedula) throws PersistenciaException {
        String sentenciaSQL = "SELECT citasActivasMedico(?)";
        
        // Intenta la conexión
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sentenciaSQL)) {
            
            // Asignar el parámetro a la consulta
            ps.setString(1, cedula);
            
            // Intenta la búsqueda
            try (ResultSet rs = ps.executeQuery()) {
                // Si encontró coincidencia
                if (rs.next()) {
                    // Regresar si tiene más de 0 citas
                    return rs.getInt(1) > 0;
                }
            }
            return false;
        // Si ocurre un error
        } catch (SQLException e) {
            // Loguear el error detallado para ayudar en el diagnóstico de problemas
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, "Error al verificar las citas para el médico con cédula: " + cedula, e);
            // Lanzar una excepción de persistencia personalizada
            throw new PersistenciaException("Error al verificar las citas programadas para el médico con cédula: " + cedula, e);
        }
    }
    
    /**
     * Obtiene la lista de citas asociadas a un médico específico.
     *
     * @param medico Objeto Medico para el cual se desean obtener las citas.
     * @return Lista de objetos Cita correspondientes al médico.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public List<Cita> obtenerCitasPorMedico(Medico medico) throws PersistenciaException {
        List<Cita> citas = new ArrayList<>();
        Paciente paciente;
        String sql = "CALL obtenerCitasPorMedico(?)";
        
        try (Connection con = conexion.crearConexion();
                CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, medico.getCedula());
            
            try (ResultSet rs = cs.executeQuery()) {
                
                while (rs.next()) {
                    
                    paciente = new Paciente(
                            rs.getInt("p.idPaciente"),
                            rs.getString("p.nombre"), 
                            rs.getString("p.apellidoPaterno"),
                            rs.getString("p.apellidoMaterno"),
                            rs.getDate("p.fechaNacimiento").toLocalDate(), 
                            rs.getString("p.email"), 
                            rs.getString("p.telefono"),
                            null,
                            null);
                    
                    citas.add(new Cita(
                            rs.getInt("c.idCita"), 
                            rs.getTimestamp("c.fechaHoraInicio").toLocalDateTime(),
                            rs.getString("c.estado"),
                            rs.getString("c.folio"),
                            rs.getString("c.tipo"),
                            paciente, 
                            medico));
                }
            }
            return citas;
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al obtener las citas.");
        }
    }
    
    /**
     * Obtiene la lista de consultas realizadas por un médico específico, identificado por su cédula.
     *
     * @param cedula Cédula profesional del médico cuyas consultas se desean obtener.
     * @return Lista de objetos Consulta asociados al médico.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public List<Consulta> obtenerConsultasPorMedico(String cedula) throws PersistenciaException {
        String sentenciaSQL = "CALL obtenerConsultasPorMedico(?)";
        List<Consulta> consultas = new ArrayList<>();
     
        try (Connection con = conexion.crearConexion(); 
             CallableStatement cs = con.prepareCall(sentenciaSQL)) {

            cs.setString(1, cedula);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Paciente paciente = new Paciente();
                    paciente.setNombre(rs.getString(7));
                    paciente.setApellidoPaterno(rs.getString(8));
                    paciente.setApellidoMaterno(rs.getString(9));
                    
                    Cita cita = new Cita();
                    cita.setFechaHoraInicio(rs.getTimestamp(4).toLocalDateTime());
                    cita.setFolio(rs.getString(5));
                    cita.setTipo(rs.getString(6));
                    cita.setPaciente(paciente);
                    
                    Consulta consulta = new Consulta();
                    consulta.setEstado(rs.getString(1));
                    consulta.setDiagnostico(rs.getString(2));
                    consulta.setTratamiento(rs.getString(3));
                    consulta.setCita(cita);
                    
                    consultas.add(consulta);
                }
            }
            return consultas;
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al obtener las consultas.");
        }
    }
    
    /**
     * Obtiene el identificador del primer médico disponible.
     *
     * Este método invoca un procedimiento almacenado que devuelve, mediante parámetro de salida,
     * la cédula o identificador del primer médico disponible.
     *
     * @return Cadena con la cédula o identificador del primer médico disponible, o null si no se encuentra ninguno.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public String obtenerPrimerMedicoDisponible() throws PersistenciaException {
        String sentenciaSQL = "CALL primerMedicoDisponible(?)";

        try (Connection con = conexion.crearConexion(); 
             CallableStatement cs = con.prepareCall(sentenciaSQL)) {

            cs.registerOutParameter(1, Types.VARCHAR);
            cs.execute();

            return cs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al obtener las consultas.");
        }
    }
}