package dao;

import java.util.List;

import entities.Usuario;
import filters.UsuarioFilter;



public interface UsuarioDAO {
	void save(Usuario obj) throws PersistenciaDacException;

	Usuario update(Usuario obj) throws PersistenciaDacException;

	void delete(Usuario obj) throws PersistenciaDacException;

	Usuario getByID(Integer objId) throws PersistenciaDacException;

	List<Usuario> getAll() throws PersistenciaDacException;

	List<Usuario> findBy(UsuarioFilter filter) throws PersistenciaDacException;

	boolean existeUsuarioComLogin(Usuario usuario);
}
