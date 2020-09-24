package impl;

import java.io.Serializable;
import java.util.List;

import dao.PersistenciaDacException;
import dao.ProjetoDAO;
import daoImplDataBase.ProjetoInDatabaseDAO;
import entities.Projeto;
import filters.ProjetoFilter;
import service.ProjetoService;
import service.ServiceDacException;

public class ProjetoServiceImpl implements Serializable, ProjetoService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;
	private ProjetoDAO projetoDAO = new ProjetoInDatabaseDAO();

	@Override
	public void save(Projeto projeto) throws ServiceDacException {
		try {
			projetoDAO.save(projeto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public void update(Projeto projeto) throws ServiceDacException {
		try {

			projetoDAO.update(projeto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(Projeto projeto) throws ServiceDacException {
		try {
			projetoDAO.delete(projeto);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public Projeto getByID(int projetoId) throws ServiceDacException {
		try {
			return projetoDAO.getByID(projetoId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Projeto> getAll() throws ServiceDacException {
		try {
			return projetoDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	@Override
	public List<Projeto> findBy(ProjetoFilter filter) throws ServiceDacException {
		try {
			filter.validate();
			return projetoDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

}
