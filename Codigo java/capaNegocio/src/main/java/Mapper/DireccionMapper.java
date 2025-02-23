/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.DireccionNuevoDTO;
import DTO.DireccionViejoDTO;
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
    
    public Direccion toEntity(DireccionViejoDTO direccionViejo) {
        if (direccionViejo == null) return null;
        
        return new Direccion(
                Integer.parseInt(direccionViejo.getIdDireccion()),
                direccionViejo.getCalle(),
                direccionViejo.getNumero(),
                direccionViejo.getColonia(), 
                direccionViejo.getCodigoPostal()
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
    
    public DireccionViejoDTO toViejoTO(Direccion direccion) {
        if (direccion == null) return null;
        
        return new DireccionViejoDTO(
                String.valueOf(direccion.getIdDireccion()),
                direccion.getCalle(), 
                direccion.getNumero(),
                direccion.getColonia(),
                direccion.getCodigoPostal()
        );
    }
}
