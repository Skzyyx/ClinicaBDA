/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IMedicoDAO;
import DAO.MedicoDAO;
import DTO.CitaViejoDTO;
import DTO.ConsultaViejoDTO;
import DTO.HorarioViejoDTO;
import DTO.MedicoViejoDTO;
import Exception.NegocioException;
import Mapper.CitaMapper;
import Mapper.ConsultaMapper;
import Mapper.HorarioMapper;
import Mapper.MedicoMapper;
import conexion.IConexion;
import entidades.Cita;
import entidades.Consulta;
import entidades.Horario;
import entidades.Medico;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase MedicoBO.
 * Representa el BO para la clase Medico.
 * 
 * @author 00000207653 Jesus Octavio Amarillas Amaya 
 * @author 00000252574 Jose Luis Islas Molina 
 * @author 00000253301 Isabel Valenzuela Rocha  
 */
public class MedicoBO {
    
    private static final Logger logger = Logger.getLogger(MedicoBO.class.getName());
    
    private final IMedicoDAO medicoDAO;
    
    private final MedicoMapper medicoMapper = new MedicoMapper();
    private final HorarioMapper horarioMapper = new HorarioMapper();
    private final CitaMapper citaMapper = new CitaMapper();
    private final ConsultaMapper consultaMapper = new ConsultaMapper();
    
    /**
     * Constructor de la clase.
     * Inicializa la conexión a utilizar.
     * @param conexion Conexión a base de datos.
     */
    public MedicoBO(IConexion conexion) {
        this.medicoDAO = new MedicoDAO(conexion);
    }
    
    /**
     * Da de baja temporal a un médico específico.
     * @param cedula Cédula profesional del médico.
     * @return True si se dio de baja, false en caso contrario.
     * @throws NegocioException Si hubo un error al intentar dar de baja.
     */
    public boolean darseDeBaja(String cedula) throws NegocioException {
        // Validar que la cedula no sea null
        if (cedula == null) {
            throw new NegocioException("La cédula profesional no puede ser nula.");
        }

        // Intentar dar de baja al médico
        try {
            return medicoDAO.cambiarEstadoMedico(cedula, "INACTIVO");
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Hubo un error al consultar cantidad de citas activas.");
        }
    }

    public boolean validarBaja(String cedula) throws NegocioException {
        // Validar que la cedula no sea null
        if (cedula == null) {
            throw new NegocioException("La cédula profesional no puede ser nula.");
        }
        
        // Validar dar de baja
        try {
            // Si el médico existe
            if (medicoExiste(cedula)) {
                // Si no tiene citas activas
                if (!tieneCitasActivas(cedula))
                    // Si hay más médicos activos
                    if (cantidadMedicosActivos() > 1) {
                        return true;
                    // Si es el único médico activo
                    } else {
                        throw new NegocioException("Es necesario al menos un médico activo más.");
                    }
                // Si tiene citas activas
                else {
                    throw new NegocioException("Cuentas con citas próximas en tu agenda, no es posible darse de baja.");
                }
            // Si el médico no existe
            } else {
                throw new NegocioException("No existe médico con cédula profesional " + cedula + ".");
            }
        } catch (NegocioException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException(ex.getMessage());
        }
    }
    
    /**
     * Da de alta a un médico en específico.
     * @param cedula Cédula profesional del médico.
     * @return True si se dio de alta, false en caso contrario.
     * @throws NegocioException Si hubo un error al darse de alta.
     */
    public boolean darseDeAlta(String cedula) throws NegocioException {
        // Validar que la cedula no sea null
        if (cedula == null) {
            throw new NegocioException("La cédula profesional no puede ser nula.");
        }
        
        // Intentar dar de alta al médico
        try {
            // Si el médico existe
            if (medicoExiste(cedula)) {
                return cambiarEstadoMedico(cedula, "ACTIVO");
            // Si el médico no existe
            } else {
                throw new NegocioException("No existe médico con cédula profesional " + cedula + ".");
            }
        } catch (NegocioException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Hubo un error al intentar dar de alta.");
        }
    }
    
    
    /**
     * Obtiene los registros de todos los médicos activos.
     * @return Lista con medicos activos.
     * @throws NegocioException Si hubo un error al consultar los médicos.
     */
    public List<MedicoViejoDTO> obtenerMedicos() throws NegocioException {
        try {
            List<Medico> medicos = medicoDAO.obtenerMedicos();
            
            return medicoMapper.toViejoDTOList(medicos);
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo obtener los médicos");
        }
    }
    
    /**
     * Obtiene todas las especialidades registradas.
     * @return Lista con nombre de especialidades.
     * @throws NegocioException Si hubo un error al consultar especialidades.
     */
    public List<String> obtenerEspecialidades() throws NegocioException {
        try {
            return medicoDAO.obtenerEspecialidades();
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo obtener las especialidades");
        }
    }
    
    /**
     * Obtiene los horarios de un médico específico mediante su id.
     * @param id Id del médico.
     * @return Lista con horarios.
     * @throws NegocioException Si hubo un error al consultar horarios.
     */
    public List<HorarioViejoDTO> obtenerHorariosMedico(String id) throws NegocioException {
        try {
            List<Horario> horarios = medicoDAO.obtenerHorariosMedicoPorID(Integer.parseInt(id));
            
            List<HorarioViejoDTO> horariosViejoDTO = horarioMapper.toViejoDTO(horarios);
            
            return horariosViejoDTO;
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo obtener los horarios.");
        }
    }
    
    /**
     * Obtiene un médico específico mediante su cédula profesional.
     * @param cedula Cédula profesional del médico.
     * @return Médico si se encontró.
     * @throws NegocioException Si hubo un error al consultar el médico.
     */
    public MedicoViejoDTO obtenerMedicoPorCedula(String cedula) throws NegocioException {
        // Validar que la cedula no sea null
        if (cedula == null) {
            throw new NegocioException("La cédula profesional no puede ser nula.");
        }
        
        try {
            // Validar si el medico existe
            Medico medicoExiste = medicoDAO.obtenerMedico(cedula);

            //Si el medico no existe
            if (medicoExiste == null) {
                throw new NegocioException("No existe médico con cédula profesional " + cedula + ".");
            }
            
            // Mappear el médico y regresarlo
            return medicoMapper.toViejoDTO(medicoExiste);
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo obtener el registro del médico.");
        }
    }
    
    /**
     * Verifica si un médico existe.
     * @param cedula Cedula profesional del médico.
     * @return True si el médico existe, false en contrario.
     * @throws NegocioException Si hubo un error al intentar consultar el médico.
     */
    private boolean medicoExiste(String cedula) throws NegocioException {
        // Validar que la cedula no sea null
        if (cedula == null) {
            throw new NegocioException("La cédula profesional no puede ser nula.");
        }
        
        // Verificar si el registro existe o no
        try {
            return medicoDAO.obtenerMedico(cedula) != null;
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Hubo un error al consultar existencia de médico.");
        }
    }
    
    /**
     * Verifica si un médico cuenta con citas activas.
     * @param cedula Cédula profesional del médico.
     * @return True si cuenta con citas activas, false en caso contrario.
     * @throws NegocioException Si hubo un error al consultar.
     */
    private boolean tieneCitasActivas(String cedula) throws NegocioException {
        // Validar que la cedula no sea null
        if (cedula == null) {
            throw new NegocioException("La cédula profesional no puede ser nula.");
        }
        
        try {
            if (medicoExiste(cedula)) {
                return medicoDAO.tieneCitasActivas(cedula);
            }
            
            throw new NegocioException("No existe médico con cédula profesional " + cedula + ".");
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Hubo un error al consultar cantidad de citas activas.");
        }
    }
    
    /**
     * Obtiene la cantidad de médicos activos.
     * @return número de médicos activos.
     * @throws NegocioException Si hubo un error al consultar médicos.
     */
    private int cantidadMedicosActivos() throws NegocioException {
        try {
            return medicoDAO.contarMedicosActivos();
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo contar a los médicos activos.");
        }
    }
    
    /**
     * Cambia el estado de un médico específico.
     * @param cedula Cédula profesional del médico.
     * @param nuevoEstado Nuevo estado para cambiar.
     * @return True si se cambió el estado, false en caso contrario.
     * @throws PersistenciaException Si hubo un error al intentar cambiar el estado.
     */
    private boolean cambiarEstadoMedico(String cedula, String nuevoEstado) throws NegocioException {
        // Validar que la cedula no sea null
        if (cedula == null) {
            throw new NegocioException("La cédula profesional no puede ser nula.");
        }
        
        // Validar el estado nuevo
        if (nuevoEstado == null || (!"ACTIVO".equals(nuevoEstado) && !"INACTIVO".equals(nuevoEstado))) {
            throw new NegocioException("El estado no es válido.");
        }
        
        try {
            return medicoDAO.cambiarEstadoMedico(cedula, nuevoEstado);
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo cambiar el estado del médico.");
        }
    }
    
    public List<CitaViejoDTO> obtenerCitasPorMedico(MedicoViejoDTO medicoViejo) throws NegocioException {
        List<CitaViejoDTO> citasViejo = new ArrayList<>();
        try {
            if (medicoViejo == null) {
                throw new NegocioException("El médico no puede ser nulo");
            }
            
            if (medicoViejo.getCedula() == null) {
                throw new NegocioException("La cedula no puede ser nula");
            }
            
            List<Cita> citas =  medicoDAO.obtenerCitasPorMedico(medicoMapper.toEntity(medicoViejo));
            
             citasViejo = citaMapper.toViejoDTOList(citas);
            
            return citasViejo;
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo obtener las citas del médico.", ex);
        }
    }
    
    public List<ConsultaViejoDTO> obtenerConsultasPorMedico(String cedula) throws NegocioException {
        if (cedula == null) {
            throw new NegocioException("La cédula profesional no puede ser nula.");
        }

        try {
            List<Consulta> consultas = medicoDAO.obtenerConsultasPorMedico(cedula);

            return consultaMapper.toViejoDTOList(consultas);
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo obtener las consultas del médico.");
        }
    }
    
    public MedicoViejoDTO obtenerPrimerMedicoDisponible() throws NegocioException {
        try {
            String cedula = medicoDAO.obtenerPrimerMedicoDisponible();

            if (cedula == null) {
                throw new NegocioException("No hay médicos disponibles para la cita.");
            } 
            
            return obtenerMedicoPorCedula(cedula);
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo obtener las consultas del médico.");
        }
    }
}