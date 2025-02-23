/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.ConsultaDAO;
import DAO.IConsultaDAO;
import Mapper.ConsultaMapper;
import conexion.IConexion;
import java.util.logging.Logger;

/**
 *
 * @author Isabel
 */
public class ConsultaBO {
    private static final Logger logger = Logger.getLogger(PacienteBO.class.getName());
    
    private final IConsultaDAO consultaDAO;
    
    private final ConsultaMapper mapper = new ConsultaMapper();
    
    public ConsultaBO(IConexion conexion) {
        this.consultaDAO = new ConsultaDAO(conexion);
    }
}
