package impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;


import dao.PersistenciaDacException;
import dao.UsuarioDAO;
import daoImplDataBase.UsuarioInDatabaseDAO;
import entities.Usuario;
import filters.UsuarioFilter;
import service.ServiceDacException;
import service.UsuarioService;

public class UsuarioServiceImpl implements Serializable, UsuarioService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7803325791425670859L;

	private UsuarioDAO usuarioDAO = new UsuarioInDatabaseDAO();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.mt.dac.services.UserService#save(br.edu.ifpb.mt.dac.entities.
	 * User)
	 */
	@Override
	public void save(Usuario usuario) throws ServiceDacException {
		try {
			// Verificar se login já existe
			validarLogin(usuario);
			// usuario.limparCamposEspecificos();
			calcularHashDaSenha(usuario);
			usuarioDAO.save(usuario);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.mt.dac.services.UserService#update(br.edu.ifpb.mt.dac.entities.
	 * User, boolean)
	 */
	@Override
	public Usuario update(Usuario usuario, boolean passwordChanged) throws ServiceDacException {

		try {
			// Verificar se login já existe
			validarLogin(usuario);
			// usuario.limparCamposEspecificos();
			if (passwordChanged) {
				calcularHashDaSenha(usuario);
			}
			return (Usuario) usuarioDAO.update(usuario);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.mt.dac.services.UserService#delete(br.edu.ifpb.mt.dac.entities.
	 * User)
	 */
	@Override
	public void delete(Usuario usuario) throws ServiceDacException {
		try {
			usuarioDAO.delete(usuario);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.mt.dac.services.UserService#getByID(int)
	 */
	@Override
	public Usuario getByID(int usuarioId) throws ServiceDacException {
		try {
			return usuarioDAO.getByID(usuarioId);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ifpb.mt.dac.services.UserService#getAll()
	 */
	@Override
	public List<Usuario> getAll() throws ServiceDacException {
		try {
			return usuarioDAO.getAll();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.mt.dac.services.UserService#findBy(br.edu.ifpb.mt.dac.filters.
	 * UserFilter)
	 */
	@Override
	public List<Usuario> findBy(UsuarioFilter filter) throws ServiceDacException {
		try {
			filter.validate();
			return usuarioDAO.findBy(filter);
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}
	}

	private String calcularHashDaSenha(Usuario usuario) throws ServiceDacException {
		usuario.setPassword(hash(usuario.getPassword()));
		return usuario.getPassword();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.edu.ifpb.mt.dac.services.UserService#senhaAtualConfere(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean senhaConfere(Usuario usuario, String supostaSenha) throws ServiceDacException {

		// Recuperar verdadeira senha atual (hash)
		String senhaHash = null;
		try {
			senhaHash = usuarioDAO.getByID(usuario.getId()).getPassword();
		} catch (PersistenciaDacException e) {
			throw new ServiceDacException(e.getMessage(), e);
		}

		// Programação defensiva contra NPE
		if (senhaHash == null && supostaSenha == null) {
			return true;
		}

		if (senhaHash == null || supostaSenha == null) {
			return false;
		}

		// Comparar hash da suposta senha com o verdadeiro hash da senha
		String supostaSenhaHash = hash(supostaSenha);

		return senhaHash.equals(supostaSenhaHash);
	}

	/**
	 * Método que calcula o hash de uma dada senha usando o algoritmo SHA-256.
	 * 
	 * @param password senha a ser calculada o hash
	 * @return hash da senha
	 * @throws ServiceDacException lançada caso ocorra algum erro durante o processo
	 */
	private String hash(String password) throws ServiceDacException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			String output = Base64.getEncoder().encodeToString(digest);
			return output;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new ServiceDacException("Could not calculate hash!", e);
		}
	}

	public void validarLogin(Usuario usuario) throws ServiceDacException {
		boolean jahExiste = usuarioDAO.existeUsuarioComLogin(usuario);
		if (jahExiste) {
			throw new ServiceDacException("Login already exists: " + usuario.getLogin());
		}
	}

}
