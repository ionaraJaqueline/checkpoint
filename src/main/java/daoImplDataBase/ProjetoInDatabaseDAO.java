package daoImplDataBase;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import dao.PersistenciaDacException;
import dao.ProjetoDAO;

import entities.Projeto;

import filters.ProjetoFilter;

public class ProjetoInDatabaseDAO extends InDatabaseDAO implements ProjetoDAO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(Projeto obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.merge(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o projeto.", pe);
		} finally {
			em.close();
		}
	}

	@Override
	public Projeto update(Projeto obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Projeto resultado = obj;
		try {
			resultado = em.merge(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o projeto.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	@Override
	public void delete(Projeto obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			obj = em.find(Projeto.class, obj.getId());
			em.remove(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o projeto.", pe);
		} finally {
			em.close();
		}
	}

	@Override
	public Projeto getByID(int objId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Projeto resultado = null;
		try {
			resultado = em.find(Projeto.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o projeto com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	@Override
	public List<Projeto> getAll() throws PersistenciaDacException {
		return findBy(new ProjetoFilter());
	}

	@Override
	public List<Projeto> findBy(ProjetoFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Projeto> resultado = new ArrayList<>();
		try {

			String jpql = "SELECT p FROM Projeto p WHERE 1 = 1 ";

			// Nome
			if (notEmpty(filter.getNome())) {
				jpql += "AND p.nome LIKE :nome ";
			}

			// Descricao
			if (notEmpty(filter.getDescricao())) {
				jpql += "AND p.descricao LIKE :descricao ";
			}

			// Carga Horária
			if (notEmpty(filter.getCargaHoraria())) {
				jpql += "AND p.cargaHoraria = :cargaHoraria ";
			}
			// Data Inicial
			if (notEmpty(filter.getDataInicial())) {
				jpql += "AND p.dataInicial = :dataInicial ";
			}
			// Data Final
			if (notEmpty(filter.getDataFinal())) {
				jpql += "AND p.dataFinal = :dataFinal ";
			}

			TypedQuery<Projeto> query = em.createQuery(jpql, Projeto.class);

			// Nome
			if (notEmpty(filter.getNome())) {
				query.setParameter("nome", "%" + filter.getNome() + "%");
			}

			// Descricao
			if (notEmpty(filter.getDescricao())) {
				query.setParameter("descricao", "%" + filter.getDescricao() + "%");
			}

			// Carga Horária
			if (notEmpty(filter.getCargaHoraria())) {
				query.setParameter("cargaHoraria", filter.getCargaHoraria());
			}

			// Data Inicial
			if (notEmpty(filter.getDataInicial())) {
				query.setParameter("dataInicial", filter.getDataInicial());
			}
			// Data Final
			if (notEmpty(filter.getDataFinal())) {
				query.setParameter("dataFinal", filter.getDataFinal());
			}

			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os projetos.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

}
