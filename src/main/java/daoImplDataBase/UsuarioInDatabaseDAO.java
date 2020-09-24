package daoImplDataBase;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;


import dao.PersistenciaDacException;
import dao.UsuarioDAO;
import entities.Usuario;
import filters.UsuarioFilter;

public class UsuarioInDatabaseDAO extends InDatabaseDAO implements UsuarioDAO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void save(Usuario obj) throws PersistenciaDacException {
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
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o usuário.", pe);
		} finally {
			em.close();
		}
	}

	@Override
	public Usuario update(Usuario obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Usuario resultado = obj;
		try {
			resultado = em.merge(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o usuário.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	@Override
	public void delete(Usuario obj) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			obj = em.find(Usuario.class, obj.getId());
			em.remove(obj);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o usuário.", pe);
		} finally {
			em.close();
		}
	}

	@Override
	public Usuario getByID(Integer objId) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Usuario resultado = null;
		try {
			resultado = em.find(Usuario.class, objId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o usuário com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	@Override
	public List<Usuario> getAll() throws PersistenciaDacException {
		return findBy(new UsuarioFilter());
	}

	@Override
	public List<Usuario> findBy(UsuarioFilter filter) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Usuario> resultado = new ArrayList<>();
		try {
			
			String jpql = "SELECT u FROM Usuario u WHERE 1 = 1 ";
			
			// Nome
			if (notEmpty(filter.getNome())) {
			jpql += "AND u.nome LIKE :nome ";
			}
			
			// Login
			if (notEmpty(filter.getLogin())) {
			jpql += "AND u.login = :login";
			}
			
			// Sobre Nome
			if (notEmpty(filter.getSobreNome())) {
			jpql += "AND u.sobreNome LIKE :sobreNome ";
			}
			
			// Data de Aniversário
			if (notEmpty(filter.getInicioDataDeAniversario())) {
				jpql += "AND u.dataDeAniversario >= :inicioDataDeAniversario ";
			}
			// Data de Aniversário
			if (notEmpty(filter.getFimDataDeAniversario())) {
			jpql += "AND u.dataDeAniversario <= :fimDataDeAniversario ";
			}
			
			// Group
			if (notEmpty(filter.getGroup())) {
			jpql += "AND u.group = :group ";
			}
			TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
			
			//Nome
			if (notEmpty(filter.getNome())) {
			query.setParameter("nome", "%" + filter.getNome() + "%");
			}
			
			//Login
			if (notEmpty(filter.getLogin())) {
			query.setParameter("login", filter.getLogin());
			}

			// Sobre Nome
			if (notEmpty(filter.getSobreNome())) {
			query.setParameter("sobreNome", "%" + filter.getSobreNome() + "%");
			}

			//Inicio Data de Aniversário
			if (notEmpty(filter.getInicioDataDeAniversario())) {
			query.setParameter("inicioDataDeAniversario", filter.getInicioDataDeAniversario());
			}

			// Fim Data de Aniversário
			if (notEmpty(filter.getFimDataDeAniversario())) {
			query.setParameter("fimDataDeAniversario", filter.getFimDataDeAniversario());
			}

			// Group
			if (notEmpty(filter.getGroup())) {
			query.setParameter("group", filter.getGroup());
			}
						
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar os usuários.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}
	@Override
	public boolean existeUsuarioComLogin(Usuario usuario) {
		if (empty(usuario) || empty(usuario.getLogin())) {
			return false;
		}
		EntityManager em = getEntityManager();

		String jpql = "SELECT COUNT(*) FROM Usuario u WHERE u.login = :login";
		if (notEmpty(usuario.getId())) {
			jpql += " AND u != :usuario ";
		}

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);

		query.setParameter("login", usuario.getLogin());
		if (notEmpty(usuario.getId())) {
			query.setParameter("usuario", usuario);
		}
		
		Long count = query.getSingleResult();
		return count > 0;
	}
}
