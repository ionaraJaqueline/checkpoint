package service;

import java.util.List;

import entities.TipoDeProjeto;

import filters.TipoDeProjetoFilter;

public interface TipoDeProjetoService {
	void save(TipoDeProjeto tipoDeProjeto) throws ServiceDacException;

	void update(TipoDeProjeto tipoDeProjeto) throws ServiceDacException;

	void delete(TipoDeProjeto tipoDeProjeto) throws ServiceDacException;

	TipoDeProjeto getByID(int tipoDeProjetoId) throws ServiceDacException;

	List<TipoDeProjeto> getAll() throws ServiceDacException;

	List<TipoDeProjeto> findBy(TipoDeProjetoFilter tipoDeProjetoFilter) throws ServiceDacException;

}
