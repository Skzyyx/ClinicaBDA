/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IMedicoDAO;
import DAO.MedicoDAO;
import DTO.MedicoViejoDTO;
import Exception.NegocioException;
import Mapper.MedicoMapper;
import conexion.IConexion;
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
    
}
