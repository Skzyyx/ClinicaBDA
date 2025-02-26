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

    /**
     * Convierte un objeto {@link UsuarioNuevoDTO} a una entidad
     * {@link Usuario}.
     *
     * @param usuarioNuevo el DTO del nuevo usuario a convertir.
     * @return una instancia de {@link Usuario} o {@code null} si el parámetro
     * es {@code null}.
     */
    public Usuario toEntity(UsuarioNuevoDTO usuarioNuevo) {
        if (usuarioNuevo == null) {
            return null;
        }

        return new Usuario(
                usuarioNuevo.getUsuario(),
                usuarioNuevo.getContrasenia(),
                usuarioNuevo.getRol()
        );
    }

    /**
     * Convierte un objeto {@link UsuarioViejoDTO} a una entidad
     * {@link Usuario}.
     *
     * @param usuarioViejo el DTO del usuario viejo a convertir.
     * @return una instancia de {@link Usuario} o {@code null} si el parámetro
     * es {@code null}.
     * @throws NumberFormatException si el ID del usuario no es un número
     * válido.
     */
    public Usuario toEntity(UsuarioViejoDTO usuarioViejo) {
        if (usuarioViejo == null) {
            return null;
        }

        return new Usuario(
                Integer.parseInt(usuarioViejo.getIdUsuario()),
                usuarioViejo.getUsuario(),
                usuarioViejo.getContrasenia(),
                usuarioViejo.getRol()
        );
    }

    /**
     * Convierte una entidad {@link Usuario} a un objeto
     * {@link UsuarioNuevoDTO}.
     *
     * @param usuario la entidad de usuario a convertir.
     * @return una instancia de {@link UsuarioNuevoDTO} o {@code null} si el
     * parámetro es {@code null}.
     */
    public UsuarioNuevoDTO toNuevoDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioNuevoDTO(
                usuario.getUsuario(),
                usuario.getContrasenia(),
                usuario.getRol()
        );
    }

    /**
     * Convierte una entidad {@link Usuario} a un objeto
     * {@link UsuarioViejoDTO}.
     *
     * @param usuario la entidad de usuario a convertir.
     * @return una instancia de {@link UsuarioViejoDTO} o {@code null} si el
     * parámetro es {@code null}.
     */
    public UsuarioViejoDTO toViejoDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioViejoDTO(
                String.valueOf(usuario.getIdUsuario()),
                usuario.getUsuario(),
                usuario.getContrasenia(),
                usuario.getRol()
        );
    }
}
