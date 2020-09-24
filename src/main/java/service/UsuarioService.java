package service;

import java.util.List;


import entities.Usuario;
import filters.UsuarioFilter;

public interface UsuarioService {
	void save(Usuario usuario) throws ServiceDacException;

	Usuario update(Usuario usuario, boolean passwordChanged) throws ServiceDacException;

	void delete(Usuario usuario) throws ServiceDacException;

	Usuario getByID(int usuarioId) throws ServiceDacException;

	List<Usuario> getAll() throws ServiceDacException;

	List<Usuario> findBy(UsuarioFilter filter) throws ServiceDacException;

	boolean senhaConfere(Usuario usuario, String supostaSenha) throws ServiceDacException;

	void validarLogin(Usuario usuario) throws ServiceDacException;
}
