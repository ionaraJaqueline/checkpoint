package daoImplDataBase;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import dao.PersistenciaDacException;
import dao.TipoDeProjetoDAO;

import entities.TipoDeProjeto;

import filters.TipoDeProjetoFilter;

public class TipoDeProjetoInDataBaseDAO extends InDatabaseDAO implements TipoDeProjetoDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public void save(TipoDeProjeto obj) throws PersistenciaDacException {
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
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar a Tipo de Projeto.", pe);
		} finally {
			em.close();
		}

	}

	@Override
	public TipoDeProjeto update(TipoDeProjeto obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		TipoDeProjeto resultado = obj;
		try {
			resultado = em.merge(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o tipo de Projeto.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	@Override
	public void delete(TipoDeProjeto obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			obj = em.find(TipoDeProjeto.class, obj.getId());
			em.remove(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o tipo de projeto.", pe);
		} finally {

		}

	}

	@Override
	public TipoDeProjeto getByID(Integer objId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		TipoDeProjeto resultado = null;
		try {
			resultado = em.find(TipoDeProjeto.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException(
					"Ocorreu algum erro ao tentar recuperar o tipo de projeto com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	@Override
	public List<TipoDeProjeto> getAll() throws PersistenciaDacException {
		return findBy(new TipoDeProjetoFilter());
	}

	@Override
	public List<TipoDeProjeto> findBy(TipoDeProjetoFilter tipoDeProjetofilter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<TipoDeProjeto> resultado = new ArrayList<>();
		try {

			String jpql = "SELECT m FROM TipoDeProjeto m WHERE 1 = 1 ";

			// Descricao
			if (notEmpty(tipoDeProjetofilter.getDescricao())) {
				jpql += "AND m.descricao LIKE :descricao ";
			}
			// Numero da Mesa

			if (notEmpty(tipoDeProjetofilter.getTipo())) {
				jpql += "AND m.tipo = :tipo ";
			}

			TypedQuery<TipoDeProjeto> query = em.createQuery(jpql, TipoDeProjeto.class);

			// Descricao
			if (notEmpty(tipoDeProjetofilter.getDescricao())) {
				query.setParameter("descricao", "%" + tipoDeProjetofilter.getDescricao() + "%");
			}

			// tipo
			if (notEmpty(tipoDeProjetofilter.getTipo())) {
				query.setParameter("tipo", tipoDeProjetofilter.getTipo());
			}

			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os tipos de projetos.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}
}
