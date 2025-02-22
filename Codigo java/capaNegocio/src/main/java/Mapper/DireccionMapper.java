/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.DireccionNuevoDTO;
import entidades.Direccion;

/**
 *
 * @author Isabel
 */
public class DireccionMapper {
    
    public Direccion toEntity(DireccionNuevoDTO direccionNuevo) {
        if (direccionNuevo == null) return null;
        
        return new Direccion(
                direccionNuevo.getCalle(),
                direccionNuevo.getNumero(),
                direccionNuevo.getColonia(), 
                direccionNuevo.getCodigoPostal()
        );
    }
    
    public DireccionNuevoDTO toNuevoTO(Direccion direccion) {
        if (direccion == null) return null;
        
        return new DireccionNuevoDTO(
                direccion.getCalle(), 
                direccion.getNumero(),
                direccion.getColonia(),
                direccion.getCodigoPostal()
        );
    }
}
