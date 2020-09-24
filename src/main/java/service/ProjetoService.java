package service;

import java.util.List;

import entities.Projeto;
import filters.ProjetoFilter;


public interface ProjetoService {
	void save(Projeto projeto) throws ServiceDacException;

	void update(Projeto projeto) throws ServiceDacException;

	void delete(Projeto projeto) throws ServiceDacException;

	Projeto getByID(int projetoId) throws ServiceDacException;

	List<Projeto> getAll() throws ServiceDacException;

	List<Projeto> findBy(ProjetoFilter filter) throws ServiceDacException;
}
