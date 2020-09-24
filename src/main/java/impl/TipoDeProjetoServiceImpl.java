package impl;

import java.io.Serializable;
import java.util.List;

import dao.PersistenciaDacException;
import dao.TipoDeProjetoDAO;
import daoImplDataBase.TipoDeProjetoInDataBaseDAO;
import entities.TipoDeProjeto;
import filters.TipoDeProjetoFilter;
import service.ServiceDacException;
import service.TipoDeProjetoService;

public class TipoDeProjetoServiceImpl implements Serializable, TipoDeProjetoService {

	private static final long serialVersionUID = 1L;

	private TipoDeProjetoDAO tipoDeProjetoDao = new TipoDeProjetoInDataBaseDAO();

	@Override
	public void save(TipoDeProjeto tipoDeProjeto) throws ServiceDacException {
		try {
			tipoDeProjetoDao.save(tipoDeProjeto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public void update(TipoDeProjeto tipoDeProjeto) throws ServiceDacException {
		try {
			tipoDeProjetoDao.update(tipoDeProjeto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

	}

	@Override
	public void delete(TipoDeProjeto tipoDeProjeto) throws ServiceDacException {
		try {
			tipoDeProjetoDao.delete(tipoDeProjeto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public TipoDeProjeto getByID(int tipoDeProjetoId) throws ServiceDacException {
		try {
			return tipoDeProjetoDao.getByID(tipoDeProjetoId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<TipoDeProjeto> getAll() throws ServiceDacException {
		try {
			return tipoDeProjetoDao.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<TipoDeProjeto> findBy(TipoDeProjetoFilter tipoDeProjetoFilter) throws ServiceDacException {
		try {
			return tipoDeProjetoDao.findBy(tipoDeProjetoFilter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
