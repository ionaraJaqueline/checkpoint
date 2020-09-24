package dao;

import java.util.List;

import entities.TipoDeProjeto;
import filters.TipoDeProjetoFilter;

public interface TipoDeProjetoDAO {

	void save(TipoDeProjeto obj) throws PersistenciaDacException;

	TipoDeProjeto update(TipoDeProjeto obj) throws PersistenciaDacException;

	void delete(TipoDeProjeto obj) throws PersistenciaDacException;

	TipoDeProjeto getByID(Integer objId) throws PersistenciaDacException;

	List<TipoDeProjeto> getAll() throws PersistenciaDacException;

	List<TipoDeProjeto> findBy(TipoDeProjetoFilter tipoDeProjetofilter) throws PersistenciaDacException;

}
