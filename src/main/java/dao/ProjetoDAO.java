package dao;

import java.util.List;

import entities.Projeto;
import filters.ProjetoFilter;

public interface ProjetoDAO {
	void save(Projeto obj) throws PersistenciaDacException;

	Projeto update(Projeto obj) throws PersistenciaDacException;

	void delete(Projeto obj) throws PersistenciaDacException;

	Projeto getByID(int objId) throws PersistenciaDacException;

	List<Projeto> getAll() throws PersistenciaDacException;

	List<Projeto> findBy(ProjetoFilter filter) throws PersistenciaDacException;

}
