/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.UsuarioNuevoDTO;
import DTO.UsuarioViejoDTO;
import entidades.Usuario;

/**
 *
 * @author Isabel
 */
public class UsuarioMapper {
    
    public Usuario toEntity(UsuarioNuevoDTO usuarioNuevo) {
        if (usuarioNuevo == null) return null;
        
        return new Usuario(
                usuarioNuevo.getUsuario(),
                usuarioNuevo.getContrasenia(),
                usuarioNuevo.getRol()
        );
    }
    
    public UsuarioNuevoDTO toNuevoDTO(Usuario usuario) {
        if (usuario == null) return null;
      
        return new UsuarioNuevoDTO(
                usuario.getUsuario(),
                usuario.getContrasenia(),
                usuario.getRol()
        );
    }
    
    public UsuarioViejoDTO toViejoDTO(Usuario usuario) {
        if (usuario == null) return null;

        return new UsuarioViejoDTO(
                usuario.getIdUsuario(),
                usuario.getUsuario(),
                usuario.getContrasenia(),
                usuario.getRol()
        );
    }
}
