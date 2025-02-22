/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IMedicoDAO;
import DAO.MedicoDAO;
import DTO.HorarioViejoDTO;
import DTO.MedicoViejoDTO;
import Exception.NegocioException;
import Mapper.HorarioMapper;
import Mapper.MedicoMapper;
import conexion.IConexion;
import entidades.Horario;
import entidades.Medico;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author skyro
 */
public class MedicoBO {
    
    private static final Logger logger = Logger.getLogger(MedicoBO.class.getName());
    
    private final IMedicoDAO medicoDAO;
    
    private final MedicoMapper medicoMapper = new MedicoMapper();
    private final HorarioMapper horarioMapper = new HorarioMapper();

    public MedicoBO(IConexion conexion) {
        this.medicoDAO = new MedicoDAO(conexion);
    }
    
    public List<MedicoViejoDTO> obtenerMedicos() throws NegocioException {
        
        try {
            List<Medico> medicos = medicoDAO.obtenerMedicos();
            
            return medicoMapper.toViejoDTOList(medicos);
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo obtener los médicos");
        }
    }
    
    public List<String> obtenerEspecialidades() throws NegocioException {
        
        try {
            return medicoDAO.obtenerEspecialidades();
        } catch (PersistenciaException ex) {
            Logger.getLogger(MedicoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo obtener las especialidades");
        }
    }
    
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
}
