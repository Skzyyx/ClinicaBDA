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
 * @author 00000207653 Jesus Octavio Amarillas Amaya
 * @author 00000252574 Jose Luis Islas Molina
 * @author 00000253301 Isabel Valenzuela Rocha
 */
public class DireccionMapper {

    /**
     * Convierte un DireccionNuevoDTO a una entidad Direccion.
     *
     * @param direccionNuevo DTO de tipo DireccionNuevoDTO que contiene los
     * datos de una nueva dirección.
     * @return Una entidad Direccion que representa la dirección nueva.
     */
    public Direccion toEntity(DireccionNuevoDTO direccionNuevo) {
        if (direccionNuevo == null) {
            return null;
        }

        return new Direccion(
                direccionNuevo.getCalle(),
                direccionNuevo.getNumero(),
                direccionNuevo.getColonia(),
                direccionNuevo.getCodigoPostal()
        );
    }

    /**
     * Convierte un DireccionViejoDTO a una entidad Direccion.
     *
     * @param direccionViejo DTO de tipo DireccionViejoDTO que contiene los
     * datos de una dirección existente.
     * @return Una entidad Direccion que representa la dirección vieja.
     */
    public Direccion toEntity(DireccionViejoDTO direccionViejo) {
        if (direccionViejo == null) {
            return null;
        }

        return new Direccion(
                Integer.parseInt(direccionViejo.getIdDireccion()), // Conversión de String a Integer para el ID
                direccionViejo.getCalle(),
                direccionViejo.getNumero(),
                direccionViejo.getColonia(),
                direccionViejo.getCodigoPostal()
        );
    }

    /**
     * Convierte una entidad Direccion a un DireccionNuevoDTO.
     *
     * @param direccion Entidad Direccion que representa una dirección.
     * @return Un DTO DireccionNuevoDTO que contiene los datos de la nueva
     * dirección.
     */
    public DireccionNuevoDTO toNuevoTO(Direccion direccion) {
        if (direccion == null) {
            return null;
        }

        return new DireccionNuevoDTO(
                direccion.getCalle(),
                direccion.getNumero(),
                direccion.getColonia(),
                direccion.getCodigoPostal()
        );
    }

    /**
     * Convierte una entidad Direccion a un DireccionViejoDTO.
     *
     * @param direccion Entidad Direccion que representa una dirección.
     * @return Un DTO DireccionViejoDTO que contiene los datos de la dirección.
     */
    public DireccionViejoDTO toViejoTO(Direccion direccion) {
        if (direccion == null) {
            return null;
        }

        return new DireccionViejoDTO(
                String.valueOf(direccion.getIdDireccion()), // Conversión de Integer a String para el ID
                direccion.getCalle(),
                direccion.getNumero(),
                direccion.getColonia(),
                direccion.getCodigoPostal()
        );
    }
}
