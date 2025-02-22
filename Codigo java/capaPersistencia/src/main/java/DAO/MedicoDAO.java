/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexion;
import entidades.Horario;
import entidades.Medico;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación de la interfaz IMedicoDAO.
 * Gestiona el alta y baja de médicos en la base de datos.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha 
 */
public class MedicoDAO implements IMedicoDAO {
    
    private final IConexion conexion;

    /**
     * Constructor que recibe una conexión a la base de datos.
     * 
     * @param conexion Objeto de tipo IConexion para gestionar la conexión con la base de datos.
     */
    public MedicoDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Da de alta a un médico en la base de datos.
     * 
     * @param medico Objeto Medico con los datos del médico a registrar.
     * @return true si la operación se realizó con éxito, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public boolean darAltaMedico(Medico medico) throws PersistenciaException {
        
        // Sentencia SQL para llamar al procedimiento almacenado
        String sentenciaSQL = "CALL darAltaMedico(?)";
        
        // Se abre la conexión y se prepara la sentencia
        try (Connection con = conexion.crearConexion();
                CallableStatement cs = con.prepareCall(sentenciaSQL)) {
            
            // Se establece el ID del médico a dar de alta
            cs.setInt(1, medico.getIdMedico());
            
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
     * Da de baja a un médico en la base de datos.
     * 
     * @param medico Objeto Medico con los datos del médico a eliminar.
     * @return true si la operación se realizó con éxito, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public boolean darBajaMedico(Medico medico) throws PersistenciaException {
        
        // Sentencia SQL para llamar al procedimiento almacenado
        String sentenciaSQL = "CALL darBajaMedico(?)";
        
        // Se abre la conexión y se prepara la sentencia
        try (Connection con = conexion.crearConexion();
                CallableStatement cs = con.prepareCall(sentenciaSQL)) {
            
            // Se establece el ID del médico a dar de baja
            cs.setInt(1, medico.getIdMedico());
            
            // Se ejecuta la consulta
            cs.executeUpdate();
            
            // Si llega aquí, el médico fue dado de baja con éxito.
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("No se pudo dar de baja al médico.");
        }
    }
    
    /**
     * Obtiene una lista con todos los medicos activos
     * 
     * @return una lista con todos los medicos activos.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
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
                            "ACTIVO",
                            null));
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
     * Obtiene una lista con todas las especialidades médicas
     * 
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
    
    @Override
    public Medico obtenerMedico(String cedula) throws PersistenciaException {
        Medico medico = null;
        String sentenciaSQL = "CALL obtenerMedico(?)";
        
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
                    medico.setEspecialidad("especialidad");
                    medico.setCedula("cedula");
                    
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



    @Override
    public List<Horario> obtenerHorariosMedicoPorID(int id) throws PersistenciaException {
        List<Horario> horarios = new ArrayList<>();
        
        String sentenciaSQL = "CALL consultarHorariosMedicoPorID(?)";
        
        //Intenta la conexion
        try(Connection con =conexion.crearConexion();
                CallableStatement cb = con.prepareCall(sentenciaSQL)) {
            
            //se setea el valor del id a buscar
            cb.setInt(1, id);
            
            //Intenta la Busqueda
            try (ResultSet rs =cb.executeQuery()){
                
                while (rs.next()){
                    horarios.add(new Horario(
                        rs.getString("diaSemana"),
                        rs.getTime("horaEntrada").toLocalTime(),
                        rs.getTime("horaSalida").toLocalTime(),
                        null)
                    );     
                }
                
                return horarios;
            } catch (SQLException ex) {
                Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PersistenciaException("Error inesperado: " + ex.getMessage());
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al consultar el Horario "+ ex.getMessage());
        }
    }


}

